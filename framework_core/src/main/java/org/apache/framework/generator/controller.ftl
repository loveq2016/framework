package ${package};

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
import ${modelPackage};
import ${examplePackage};
import ${servicePackage};

<#assign  modelName="${domainObjectName}"/>
<#assign  modelNameVariable="${StringUtils.firstLetterToLowerCase('${domainObjectName}')!}"/>

/**
 * @author willenfoo
 */
@Controller
@RequestMapping("${modelNameVariable}/")
public class ${modelName}Controller extends BaseController {

	
	/**
	 *  查询数据
	 * @param role
	 * @return
	 */
	@RequiresPermissions("${modelNameVariable}_find")
	@RequestMapping(value = "find")
	@ResponseBody
	public Object find(${modelName} ${modelNameVariable}) {
		Map<String, Object> map = getSuccessResult();
		${modelName}Example example = new ${modelName}Example();
		${modelName}Example.Criteria criteria = example.createCriteria();
		Pager pager = ${modelNameVariable}Service.selectByExample(example, getOffset(), getPageSize());
		map.put(TOTAL, pager.getTotal());
		map.put(ROWS, pager.getList());
		return map;
	}

	/**
	 * 跳转到查询页面
	 * @return
	 */
	@RequiresPermissions("${modelNameVariable}")
	@RequestMapping(value="toFind")
	public String toFind() {
		return VIEWS_PATH+"find";
	}
	
    /**
     * 跳转到新增页面
     * @param modelMap
     * @return
     */
    @RequiresPermissions("${modelNameVariable}_add")
	@RequestMapping(value="toAdd")
	public String toAdd(ModelMap modelMap) {
	    modelMap.put("${modelNameVariable}", new ${modelName}());
		return VIEWS_PATH+"edit";
	}
	
	/**
     * 跳转到更新页面
     * @param modelMap
     * @return
     */
    @RequiresPermissions("${modelNameVariable}_update")
	@RequestMapping(value="toUpdate")
	public String toUpdate(@RequestParam Long id,ModelMap modelMap) {
	    ${modelName} ${modelNameVariable} = ${modelNameVariable}Service.selectById(id);
	     modelMap.put("${modelNameVariable}", ${modelNameVariable});
		return VIEWS_PATH+"edit";
	}
	
	/**
	 * 添加
	 * @param user
	 * @return
	 */
	@RequiresPermissions("${modelNameVariable}_add")
	@RequestMapping(value = "add")
	@ResponseBody
	public Object add(${modelName} ${modelNameVariable}) {
		Map<String, Object> map = getSuccessResult();
		${modelNameVariable}Service.insert(${modelNameVariable});
		return map;
	}
	
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	@RequiresPermissions("${modelNameVariable}_update")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(${modelName} ${modelNameVariable}) {
		Map<String, Object> map = getSuccessResult();
		${modelNameVariable}Service.updateById(${modelNameVariable});
		return map;
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequiresPermissions("${modelNameVariable}_delete")
	@RequestMapping(value="delete")
	@ResponseBody
	public Object delete(@RequestParam("id") Long id) {
		Map<String, Object> map = getSuccessResult();
		${modelNameVariable}Service.deleteById(id);
		return map;
	} 
	  
	@Resource
	private ${modelName}Service ${modelNameVariable}Service; //服务层

	private static String VIEWS_PATH = "${moduleName}/${modelNameVariable}/";
	 
}