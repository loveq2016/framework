package org.apache.framework.controller.systemmanage;

import java.util.List;
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
import org.apache.framework.util.SessionUtils;
import org.apache.framework.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * @author willenfoo
 */
@Controller
@RequestMapping("role/")
public class RoleController extends BaseController {

	
	/**
	 *  查询数据
	 * @param role
	 * @return
	 */
	@RequiresPermissions("role_find")
	@RequestMapping(value = "find")
	@ResponseBody
	public Object find(Role role,  String projectCode) {
		Map<String, Object> map = getSuccessResult();
		RoleExample example = new RoleExample();
		RoleExample.Criteria criteria = example.createCriteria();
		criteria.andProjectCodeEqualTo(projectCode);
		if (StringUtils.isNotEmpty(role.getName())) {
			criteria.andNameLike("%"+role.getName().trim()+"%");
		}
		Pager pager = roleService.selectByExample(example, getOffset(), getPageSize());
		map.put(TOTAL, pager.getTotal());
		map.put(ROWS, pager.getList());
		return map;
	}

	/**
	 * 跳转到查询页面
	 * @return
	 */
	@RequiresPermissions("role")
	@RequestMapping(value="toFind")
	public String toFind() {
		return VIEWS_PATH+"find";
	}
	
    /**
     * 跳转到新增页面
     * @param modelMap
     * @return
     */
	@RequiresPermissions("role_add")
	@RequestMapping(value="toAdd")
	public String toAdd(ModelMap modelMap) {
	    modelMap.put("role", new Role());
		return VIEWS_PATH+"edit";
	}
	
	/**
     * 跳转到更新页面
     * @param modelMap
     * @return
     */
	@RequiresPermissions("role_update")
	@RequestMapping(value="toUpdate")
	public String toUpdate(@RequestParam Long id,ModelMap modelMap) {
	    Role role = roleService.selectById(id);
	     modelMap.put("role", role);
		return VIEWS_PATH+"edit";
	}
	
	/**
	 * 添加
	 * @param user
	 * @return
	 */
	@RequiresPermissions("role_add")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(Role role, String projectCode) {
		Map<String, Object> map = getSuccessResult();
		role.setProjectCode(projectCode);
		role.setCreateUserId(SessionUtils.getUserId());
		roleService.insert(role);
		return map;
	}
	
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	@RequiresPermissions("role_update")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(Role user) {
		Map<String, Object> map = getSuccessResult();
		roleService.updateById(user);
		return map;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequiresPermissions("role_delete")
	@RequestMapping(value="delete")
	@ResponseBody
	public Object delete(@RequestParam("id") Long id) {
		Map<String, Object> map = getSuccessResult();
		roleService.deleteById(id);
		return map;
	} 
	  
	/**
	 * 跳转到查询页面
	 * @return
	 */
	@RequiresPermissions("user_assign_role")
	@RequestMapping(value="toAssignRole")
	public String toAssignRole() {
		return VIEWS_PATH+"assignRole";
	}
	
	/**
	 * 根据用户id查询该用户的角色
	 * @param userId
	 * @return
	 */
	@RequiresPermissions("user_assign_role")
	@RequestMapping(value = "findByUserId")
	@ResponseBody
	public Object findByUserId(@RequestParam("userId") Long userId) {
		Map<String, Object> map = getSuccessResult();
		try { 
			List<Role> list = roleService.selectByUserId(userId);
			map.put(ROWS, list);
		} catch (Exception e) {
		    e.printStackTrace();
		}  
		return map;
	}
	
	/**
	 * 根据角色id跟用户id删除  关连关系
	 * @param roleId
	 * @param userId
	 * @return
	 */
	@RequiresPermissions("user_assign_role")
	@RequestMapping(value = "deleteUserRole")
	@ResponseBody
	public Object deleteUserRole(@RequestParam("roleId") Long roleId,
			@RequestParam("userId") Long userId) {
		Map<String, Object> map = getSuccessResult();
		userRoleService.deleteUserRole(userId, roleId);
		return map;
	}
	
	/**
	 * 根据角色id跟用户id 添加 关连关系
	 * @param roleId
	 * @param userId
	 * @return
	 */
	@RequiresPermissions("user_assign_role")
	@RequestMapping(value = "addUserRole")
	@ResponseBody
	public Object addUserRole(@RequestParam("roleId") Long roleId,
			@RequestParam("userId") Long userId) {
		Map<String, Object> map = getSuccessResult();
		userRoleService.addUserRole(userId, roleId);
		return map;
	}
	
	@Resource
	private RoleService roleService; //服务层
	
	@Resource
	private UserRoleService userRoleService; //服务层

	private static String VIEWS_PATH = "systemmanage/role/";
	 
}