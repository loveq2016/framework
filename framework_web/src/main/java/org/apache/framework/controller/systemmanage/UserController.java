package org.apache.framework.controller.systemmanage;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.apache.framework.controller.BaseController;
import org.apache.framework.domain.Pager;
import org.apache.framework.model.*;
import org.apache.framework.model.example.*;
import org.apache.framework.service.*;
import org.apache.framework.util.Md5Utils;
import org.apache.framework.util.SessionUtils;
import org.apache.framework.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


/**
 * 
 * @author willenfoo
 *
 */
@Controller
@RequestMapping("user/")
@SessionAttributes(value="projectCode")
public class UserController extends BaseController {

	
	/**
	 * 查询数据
	 * @param role
	 * @return
	 */
	@RequiresPermissions("user_find")
	@RequestMapping(value = "find")
	@ResponseBody
	public Object find(User user) {
		Map<String, Object> map = getSuccessResult();
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(user.getUserName())) {
			criteria.andUserNameLike("%"+user.getUserName().trim()+"%");
		}
		Pager pager = userService.selectByExample(example, getOffset(), getPageSize());
		map.put(TOTAL, pager.getTotal());
		map.put(ROWS, pager.getList());
		return map;
	}

	/**
	 * 跳转到查询页面
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value="toFind")
	public String toFind() {
		return VIEWS_PATH+"find";
	}
	
    /**
     * 跳转到新增页面
     * @param modelMap
     * @return
     */
	@RequiresPermissions("user_add")
	@RequestMapping(value="toAdd")
	public String toAdd(ModelMap modelMap) {
	    modelMap.put("user", new User());
		return VIEWS_PATH+"edit";
	}
	
	/**
     * 跳转到更新页面
     * @param modelMap
     * @return
     */
	@RequiresPermissions("user_update")
	@RequestMapping(value="toUpdate")
	public String toUpdate(@RequestParam Long id,ModelMap modelMap) {
	    User user = userService.selectById(id);
	     modelMap.put("user", user);
		return VIEWS_PATH+"edit";
	}
	
	/**
	 * 添加
	 * @param user
	 * @return
	 */
	@RequiresPermissions("user_add")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(User user, @ModelAttribute("projectCode") String projectCode) {
		Map<String, Object> map = getSuccessResult();
		user.setCreateUserId(SessionUtils.getUserId());
		user.setPassword(Md5Utils.getMD5(user.getPassword()));
		user.setProjectCode(projectCode); 
		userService.insertConnect(user);
		return map;
	}
	
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	@RequiresPermissions("user_update")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(User user) {
		Map<String, Object> map = getSuccessResult();
		userService.updateById(user);
		return map;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user_delete")
	@RequestMapping(value="delete")
	@ResponseBody
	public Object delete(@RequestParam("id") Long id) {
		Map<String, Object> map = getSuccessResult();
		userService.deleteById(id);
		return map;
	} 
	  
	/**
	 *  判断用户名是否存在
	 * @param role
	 * @return
	 */
	@RequiresPermissions("user_add")
	@RequestMapping(value = "isExistByUserName")
	@ResponseBody
	public Object isExistByUserName(@RequestParam String userName) {
		Map<String, Object> map = getSuccessResult();
		User user = new User();
		user.setUserName(userName);
		user = userService.selectByModel(user);
		if (user != null) {
			map = getFailureResult("用户名已经存在!");
		}
		return map;
	}
	
	/**
	 * 切换系统
	 * @param projectCode
	 * @return
	 */
	@RequestMapping(value="switchingSystem")
	@ResponseBody
	public Object switchingSystem(@RequestParam String projectCode) {
		Map<String, Object> map = getSuccessResult();
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		User user = (User)session.getAttribute("user");
		if (user != null) {
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
			try {
				session.setAttribute("projectCode", projectCode);
				subject.login(token);
			} catch (AuthenticationException e) {
				e.printStackTrace();
				map = getFailureResult("密码被修改，请重新登录!");
			}
		}
		return map;
	}
	
	@Resource
	private UserService userService; //服务层

	private static String VIEWS_PATH = "systemmanage/user/";
	 
}