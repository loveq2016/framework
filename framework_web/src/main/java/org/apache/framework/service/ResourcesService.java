package org.apache.framework.service;

import java.util.List;

import org.apache.framework.model.Resources;
import org.apache.framework.model.example.ResourcesExample;
import org.apache.framework.service.BaseService;

public interface ResourcesService extends BaseService<Resources, ResourcesExample> {

	/**
	 * 根据角色ID，查询角色可以访问的资源
	 * @param roleIds
	 * @return
	 */
	List<Resources> selectByRoleId(Long roleId);
	
	/**
	 * 根据角色ID集合，查询角色可以访问的资源
	 * @param roleIds
	 * @return
	 */
	List<Resources> selectByRoleIds(List<Long> roleIds);

	/**
	 * 根据项目code查询资源
	 * @param userId
	 * @return
	 */
	List<Resources> selectByProjectCode(List<String> projectCodes);
	
	
}