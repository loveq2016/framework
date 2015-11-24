package org.apache.framework.auth.client;

import org.apache.framework.model.User;
import org.apache.framework.shiro.ShiroSessionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-3-13
 * <p>
 * Version: 1.0
 */
public class ClientRealm extends AuthorizingRealm {

	private String appKey;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	private ShiroSessionService shiroSessionService;

	public ShiroSessionService getShiroSessionService() {
		return shiroSessionService;
	}

	public void setShiroSessionService(ShiroSessionService shiroSessionService) {
		this.shiroSessionService = shiroSessionService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();

		User user = (User) session.getAttribute("user");
		if (user != null) {
			authorizationInfo.setRoles(user.getRoles());
			authorizationInfo.setStringPermissions(user.getPermissions());
		}

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// 永远不会被调用
		throw new UnsupportedOperationException("永远不会被调用");
	}
}
