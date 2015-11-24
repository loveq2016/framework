package org.apache.framework.controller.systemmanage;


import java.util.List;
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
import org.apache.framework.util.CodeUtils;
import org.apache.framework.util.SessionUtils;
import org.apache.framework.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@Controller
@RequestMapping(value = "code/")
public class CodeController extends BaseController {

    @RequestMapping(value = "findByGroupNo")
    @ResponseBody
    public Object find(@RequestParam String groupNo,
    		@RequestParam(required=false,value="selected") String selected) {
    	List<Code> list = CodeUtils.getCodes(groupNo);
    	if (list != null && !list.isEmpty()) {
    		for (Code code : list) {
    			if ("selectedAll".equals(selected) || code.getItemKey().equals(selected)) {
    				code.setSelected(true);
        		}
			}
    	}
    	return list;
    }
    
    /**
	 *  查询数据
	 * @param role
	 * @return
	 */
	@RequiresPermissions("code_find")
	@RequestMapping(value = "find")
	@ResponseBody
	public Object find(Code code) {
		Map<String, Object> map = getSuccessResult();
		CodeExample example = new CodeExample();
		example.setOrderByClause(" group_no desc , sequence asc ");
		CodeExample.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(code.getGroupNo())) {
			criteria.andGroupNoEqualTo(code.getGroupNo());
		}
		if (StringUtils.isNotEmpty(code.getGroupName())) {
			criteria.andGroupNameLike("%"+code.getGroupName().trim()+"%");
		}
		Pager pager = codeService.selectByExample(example, getOffset(), getPageSize());
		map.put(TOTAL, pager.getTotal());
		map.put(ROWS, pager.getList());
		return map;
	}

	/**
	 * 跳转到查询页面
	 * @return
	 */
	@RequiresPermissions("code")
	@RequestMapping(value="toFind")
	public String toFind() {
		return VIEWS_PATH+"find";
	}
	
    /**
     * 跳转到新增页面
     * @param modelMap
     * @return
     */
    @RequiresPermissions("code_add")
	@RequestMapping(value="toAdd")
	public String toAdd(ModelMap modelMap) {
	    modelMap.put("code", new Code());
		return VIEWS_PATH+"edit";
	}
	
	/**
     * 跳转到更新页面
     * @param modelMap
     * @return
     */
    @RequiresPermissions("code_update")
	@RequestMapping(value="toUpdate")
	public String toUpdate(@RequestParam Long id,ModelMap modelMap) {
	    Code code = codeService.selectById(id);
	     modelMap.put("code", code);
		return VIEWS_PATH+"edit";
	}
	
	/**
	 * 添加
	 * @param user
	 * @return
	 */
	@RequiresPermissions("code_add")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(Code code) {
		Map<String, Object> map = getSuccessResult();
		code.setCreateUserId(SessionUtils.getUserId());
		codeService.insert(code);
		return map;
	}
	
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	@RequiresPermissions("code_update")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(Code user) {
		Map<String, Object> map = getSuccessResult();
		codeService.updateById(user);
		return map;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequiresPermissions("code_delete")
	@RequestMapping(value="delete")
	@ResponseBody
	public Object delete(@RequestParam("id") Long id) {
		Map<String, Object> map = getSuccessResult();
		codeService.deleteById(id);
		return map;
	} 
	  
	@Resource
	private CodeService codeService; //服务层

	private static String VIEWS_PATH = "systemmanage/code/";
}
