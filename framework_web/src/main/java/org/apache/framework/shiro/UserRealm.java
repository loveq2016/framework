package org.apache.framework.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.framework.model.Project;
import org.apache.framework.model.Resources;
import org.apache.framework.model.Role;
import org.apache.framework.model.User;
import org.apache.framework.service.ProjectService;
import org.apache.framework.service.ResourcesService;
import org.apache.framework.service.RoleService;
import org.apache.framework.service.UserService;
import org.apache.framework.util.ListUtils;
import org.apache.framework.util.Md5Utils;
import org.apache.framework.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;

public class UserRealm extends AuthorizingRealm {

	/**
	 * 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();  
        Subject currentUser = SecurityUtils.getSubject();  
		Session session = currentUser.getSession();
		User user = (User)session.getAttribute("user");
		if (user != null) {
			authorizationInfo.setRoles(user.getRoles());
			authorizationInfo.setStringPermissions(user.getPermissions());
		}
        return authorizationInfo;  
	}

	/**
	 * 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authToken;
		String userName = token.getUsername();
		String password = new String(token.getPassword());
		LOG.info("用户登录，用户名===="  + userName);
		User selectUser = new User(); 
		selectUser.setUserName(userName); 
		User user = null; 
		try {
			user = userService.selectByModel(selectUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		} else {
			String md5Password = user.getPassword();
			if (!Md5Utils.getMD5(password).equals(md5Password)) {
				throw new IncorrectCredentialsException(); // 密码错误
			}
			if (Boolean.TRUE.equals("")) {
				throw new LockedAccountException(); // 帐号锁定
			}
			Subject currentUser = SecurityUtils.getSubject();  
			Session session = currentUser.getSession();
			
			String projectCode = (String)session.getAttribute("projectCode");
			if (StringUtils.isEmpty(projectCode)) {
				projectCode = user.getProjectCode();
				session.setAttribute("projectCode", projectCode);
			}
			List<Resources> resourcesList = null;  //用户拥有的资源
			List<Role> roles = null; //用户拥有的角色
			List<Project> projects = null; //用户拥有的项目
			if ("admin".equals(userName)) {
				roles = roleService.selectByProjectCode(projectCode);
				
				List<String> projectCodes = new ArrayList<String>();
				projectCodes.add(projectCode);
				projectCodes.add("framework");
				resourcesList = resourcesService.selectByProjectCode(projectCodes);
				
				projects = projectService.selectByExample(null);
			} else {
				roles = roleService.selectByUserId(user.getId());
				if (ListUtils.isNotEmpty(roles)) {
					List<Long> roleIds = new ArrayList<Long>();
					for (Role role : roles) {
						roleIds.add(role.getId());
					}
					resourcesList = resourcesService.selectByRoleIds(roleIds);
				}
				projects = projectService.selectByUserId(user.getId());
			}
			if (ListUtils.isNotEmpty(roles)) {
				Set<String> roleSet = new HashSet<String>();
				for (Role role : roles) {
					roleSet.add(role.getName());
					LOG.info("拥有角色===="  + role.getName());
				}
				user.setRoles(roleSet);
			}
			if (ListUtils.isNotEmpty(resourcesList)) {
				Set<String> permissionSet = new HashSet<String>();
				for (Resources resources : resourcesList) {
					permissionSet.add(resources.getCode());
					LOG.info("访问模块===="  + resources.getName());
				}
				user.setPermissions(permissionSet);
			}
			session.setAttribute("resources", resourcesList);
			user.setPassword(password);
			session.setAttribute("user", user);
			session.setAttribute("projects", projects);
			return new SimpleAuthenticationInfo(user.getUserName(),  md5Password, getName());
		}
	}

	/** 
     * 设定Password校验. 
     */  
    @PostConstruct
    public void initCredentialsMatcher() {  
        //该句作用是重写shiro的密码验证，让shiro用我自己的验证  
    	setCredentialsMatcher(new CustomCredentialsMatcher());  
    } 
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourcesService resourcesService;
	
	@Autowired
	private ProjectService projectService;
	
	
	@Autowired
	private CacheManager cacheManager;
	
	private final static Log LOG = LogFactory.getLog(UserRealm.class);
}
