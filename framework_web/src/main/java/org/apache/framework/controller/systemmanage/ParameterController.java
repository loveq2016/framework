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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.framework.model.*;
import org.apache.framework.model.example.*;
import org.apache.framework.service.*;
import org.apache.framework.util.StringUtils;


/**
 * @author willenfoo
 */
@Controller
@RequestMapping("parameter/")
public class ParameterController extends BaseController {

	
	/**
	 *  查询数据
	 * @param role
	 * @return
	 */
	@RequiresPermissions("parameter_find")
	@RequestMapping(value = "find")
	@ResponseBody
	public Object find(Parameter parameter) {
		Map<String, Object> map = getSuccessResult();
		ParameterExample example = new ParameterExample();
		ParameterExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(parameter.getName())) {
			criteria.andNameLike("%"+StringUtils.trim(parameter.getName())+"%");
		}
		Pager pager = parameterService.selectByExample(example, getOffset(), getPageSize());
		map.put(TOTAL, pager.getTotal());
		map.put(ROWS, pager.getList());
		return map;
	}

	/**
	 * 跳转到查询页面
	 * @return
	 */
	@RequiresPermissions("parameter")
	@RequestMapping(value="toFind")
	public String toFind() {
		return VIEWS_PATH+"find";
	}
	
    /**
     * 跳转到新增页面
     * @param modelMap
     * @return
     */
    @RequiresPermissions("parameter_add")
	@RequestMapping(value="toAdd")
	public String toAdd(ModelMap modelMap) {
	    modelMap.put("parameter", new Parameter());
		return VIEWS_PATH+"edit";
	}
	
	/**
     * 跳转到更新页面
     * @param modelMap
     * @return
     */
    @RequiresPermissions("parameter_update")
	@RequestMapping(value="toUpdate")
	public String toUpdate(@RequestParam Long id,ModelMap modelMap) {
	    Parameter parameter = parameterService.selectById(id);
	     modelMap.put("parameter", parameter);
		return VIEWS_PATH+"edit";
	}
	
	/**
	 * 添加
	 * @param user
	 * @return
	 */
	@RequiresPermissions("parameter_add")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(Parameter parameter) {
		Map<String, Object> map = getSuccessResult();
		parameterService.insert(parameter);
		return map;
	}
	
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	@RequiresPermissions("parameter_update")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(Parameter user) {
		Map<String, Object> map = getSuccessResult();
		parameterService.updateById(user);
		return map;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequiresPermissions("parameter_delete")
	@RequestMapping(value="delete")
	@ResponseBody
	public Object delete(@RequestParam("id") Long id) {
		Map<String, Object> map = getSuccessResult();
		parameterService.deleteById(id);
		return map;
	} 
	  
	@Resource
	private ParameterService parameterService; //服务层

	private static String VIEWS_PATH = "systemmanage/parameter/";
	 
}