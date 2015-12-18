package org.apache.framework.controller.systemmanage;

import java.util.ArrayList;
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
import org.apache.framework.util.ListUtils;
import org.apache.framework.util.SessionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;



/**
 * 
 * @author willenfoo
 *
 */
@Controller
@RequestMapping("resources/")
public class ResourcesController extends BaseController {

	
	/**
	 *  查询数据
	 * @param role
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequiresPermissions("resources_find")
	@RequestMapping(value = "find")
	@ResponseBody
	public Object find(Resources resources,  String projectCode) {
		Map<String, Object> map = getSuccessResult();
		ResourcesExample example = new ResourcesExample();
		example.setOrderByClause(" sequence asc ");
		ResourcesExample.Criteria criteria = example.createCriteria();
		criteria.andProjectCodeEqualTo(projectCode);
		if (resources.getId() == null || resources.getId().equals(-1L)) {
			criteria.andParentIdEqualTo(-1L);
			Pager pager = resourcesService.selectByExample(example, getOffset(), getPageSize());
			map.put(TOTAL, pager.getTotal());
			map.put(ROWS, setData((List<Resources>)pager.getList()));
		} else {
			criteria.andParentIdEqualTo(resources.getId());
			return setData(resourcesService.selectByExample(example));
		}
		return map;
	}

	private List<Resources> setData(List<Resources> list) {
		if (ListUtils.isNotEmpty(list)) {
			for (Resources resources : list) {
				ResourcesExample example = new ResourcesExample();
				ResourcesExample.Criteria criteria = example.createCriteria();
				criteria.andParentIdEqualTo(resources.getId());
				int childCount = resourcesService.countByExample(example);
				if (childCount > 0) {
					resources.setState("closed");
				} else {
					resources.setState("open");
				}
			}
		}
		return list;
	}
	/**
	 * 跳转到查询页面
	 * @return
	 */
	@RequiresPermissions("resources_find")
	@RequestMapping(value="toFind")
	public String toFind() {
		return VIEWS_PATH+"find";
	}
	
    /**
     * 跳转到新增页面
     * @param modelMap
     * @return
     */
	@RequiresPermissions("resources_add")
	@RequestMapping(value="toAdd")
	public String toAdd(ModelMap modelMap) {
	    modelMap.put("resources", new Resources());
		return VIEWS_PATH+"edit";
	}
	
	/**
     * 跳转到更新页面
     * @param modelMap
     * @return
     */
	@RequiresPermissions("resources_update")
	@RequestMapping(value="toUpdate")
	public String toUpdate(@RequestParam Long id,ModelMap modelMap) {
	    Resources resources = resourcesService.selectById(id);
	     modelMap.put("resources", resources);
		return VIEWS_PATH+"edit";
	}
	
	/**
	 * 添加
	 * @param user
	 * @return
	 */
	@RequiresPermissions("resources_add")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(Resources resources, String projectCode) {
		Map<String, Object> map = getSuccessResult();
		try {
			resources.setProjectCode(projectCode);
			resources.setCreateUserId(SessionUtils.getUserId());
			resourcesService.insert(resources);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	@RequiresPermissions("resources_update")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(Resources user) {
		Map<String, Object> map = getSuccessResult();
		resourcesService.updateById(user);
		return map;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequiresPermissions("resources_delete")
	@RequestMapping(value="delete")
	@ResponseBody
	public Object delete(@RequestParam("id") Long id) {
		Map<String, Object> map = getSuccessResult();
		ResourcesExample example = new ResourcesExample();
		ResourcesExample.Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		int count = resourcesService.countByExample(example);
		if (count <= 0) {
			resourcesService.deleteById(id);
		} else {
			map = getFailureResult("有子菜单存在，不允许删除!");
		}
		return map;
	} 
	
	/**
	 * 查询资源，现成树形结构
	 * @param resources
	 * @return
	 */
	@RequiresPermissions("role_assign_resources")
	@RequestMapping(value = "findAll")
	@ResponseBody
	public Object findAll(String projectCode) {
		Map<String, Object> map = getSuccessResult();
		ResourcesExample example = new ResourcesExample();
		example.setOrderByClause(" sequence asc ");
		ResourcesExample.Criteria criteria = example.createCriteria();
		criteria.andProjectCodeEqualTo(projectCode);
		try {
			List<Resources> list = resourcesService.selectByExample(example);
			map.put(ROWS, setChildren(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 把list形成树形结构，设置子节点
	 * 
	 * @param list
	 * @return
	 */
	private List<Resources> setChildren(List<Resources> list) {
		List<Resources> oneLevel = new ArrayList<Resources>();
		if (list != null && !list.isEmpty()) {
			for (Resources resources : list) {
				if ("-1".equals(resources.getParentId() + "")) {
					List<Resources> twoLevel = getChildren(resources.getId(), list);
					for (Resources resources2 : twoLevel) {
						List<Resources> threeLevel = getChildren(resources2.getId(), list);
						resources2.setChildren(threeLevel);
						if (threeLevel != null && !threeLevel.isEmpty()) {
							for (Resources resources3 : threeLevel) {
								List<Resources> fourLevel = getChildren(resources3.getId(), list);
								resources3.setChildren(fourLevel);
								if (fourLevel != null && !fourLevel.isEmpty()) {
									for (Resources resources4 : fourLevel) {
										List<Resources> fiveLevel = getChildren(resources4.getId(), list);
										resources4.setChildren(fiveLevel);
									}
								}
							}
						}
					}
					resources.setChildren(twoLevel);
					oneLevel.add(resources);
				}
			}
		}
		return oneLevel;
	}
	
	/**
	 * 得到id中的子节点
	 * 
	 * @param id
	 * @param list
	 * @return
	 */
	private List<Resources> getChildren(Long id,
			List<Resources> list) {
		List<Resources> children = new ArrayList<Resources>();
		for (Resources resources : list) {
			if (id.equals(resources.getParentId())) {
				children.add(resources);
			}
		}
		return children;
	}
	
	/**
     * 跳转到 分配访问资源   页面
     * @param modelMap
     * @return
     */
	@RequiresPermissions("role_assign_resources")
	@RequestMapping(value="toAssignResources")
	public String toAssignResources() {
		return VIEWS_PATH+"assignResources";
	}
	
	/**
	 * 根据角色id，查询该角色可以访问的资源
	 * @param roleId
	 * @return
	 */
	@RequiresPermissions("role_assign_resources")
	@RequestMapping(value = "findByRoleId")
	@ResponseBody
	public Object findByRoleId(@RequestParam("roleId") Long roleId) {
		return resourcesService.selectByRoleId(roleId);
	}
	  
	/**
	 * 保存 角色与资源 关系
	 * 
	 * @param roleIds
	 * @param resourcesIds
	 * @return
	 */
	@RequiresPermissions("role_assign_resources")
	@RequestMapping(value = "saveRoleResources")
	@ResponseBody
	public Object saveRoleResources(
			@RequestParam("roleIds") Long[] roleIds,
			@RequestParam("resourcesIds") Long[] resourcesIds) {
		Map<String, Object> map = getSuccessResult();
		if (roleIds.length == resourcesIds.length) {
			try {
				roleResourcesService.saveRoleResources(roleIds[0], resourcesIds);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	@Resource
	private ResourcesService resourcesService; //服务层
	
	@Resource
	private RoleResourcesService roleResourcesService; //服务层

	private static String VIEWS_PATH = "systemmanage/resources/";
	 
}