package org.apache.framework.controller.systemmanage;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.framework.controller.BaseController;
import org.apache.framework.domain.Pager;
import org.apache.framework.model.*;
import org.apache.framework.model.example.*;
import org.apache.framework.service.*;
import org.apache.framework.util.SessionUtils;
import org.apache.framework.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;


/**
 * 
 * @author willenfoo
 *
 */
@Controller
@RequestMapping("project/")
public class ProjectController extends BaseController {

	
	/**
	 *  查询数据
	 * @param role
	 * @return
	 */
	@RequiresPermissions("project_find")
	@RequestMapping(value = "find")
	@ResponseBody
	public Object find(Project project) {
		Map<String, Object> map = getSuccessResult();
		ProjectExample example = new ProjectExample();
		ProjectExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(project.getName())) {
			criteria.andNameLike("%"+project.getName().trim()+"%");
		}
		Pager pager = projectService.selectByExample(example, getOffset(), getPageSize());
		map.put(TOTAL, pager.getTotal());
		map.put(ROWS, pager.getList());
		return map;
	}

	/**
	 * 跳转到查询页面
	 * @return
	 */
	@RequiresPermissions("project")
	@RequestMapping(value="toFind")
	public String toFind() {
		return VIEWS_PATH+"find";
	}
	
    /**
     * 跳转到新增页面
     * @param modelMap
     * @return
     */
	@RequiresPermissions("project_add")
	@RequestMapping(value="toAdd")
	public String toAdd(ModelMap modelMap) {
	    modelMap.put("project", new Project());
		return VIEWS_PATH+"edit";
	}
	
	/**
     * 跳转到更新页面
     * @param modelMap
     * @return
     */
	@RequiresPermissions("project_update")
	@RequestMapping(value="toUpdate")
	public String toUpdate(@RequestParam Long id,ModelMap modelMap) {
	    Project project = projectService.selectById(id);
	     modelMap.put("project", project);
		return VIEWS_PATH+"edit";
	}
	
	/**
	 * 添加
	 * @param user
	 * @return
	 */
	@RequiresPermissions("project_add")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(Project project) {
		Map<String, Object> map = getSuccessResult();
		project.setCreateUserId(SessionUtils.getUserId());
		projectService.insert(project);
		return map;
	}
	
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	@RequiresPermissions("project_update")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(Project user) {
		Map<String, Object> map = getSuccessResult();
		projectService.updateById(user);
		return map;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequiresPermissions("project_delete")
	@RequestMapping(value="delete")
	@ResponseBody
	public Object delete(@RequestParam("id") Long id) {
		Map<String, Object> map = getSuccessResult();
		projectService.deleteById(id);
		return map;
	} 
	  
	@Resource
	private ProjectService projectService; //服务层

	private static String VIEWS_PATH = "systemmanage/project/";
	 
}