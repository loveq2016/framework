package org.apache.framework.service;

import org.apache.framework.model.RoleResources;
import org.apache.framework.model.example.RoleResourcesExample;
import org.apache.framework.service.BaseService;

public interface RoleResourcesService extends BaseService<RoleResources, RoleResourcesExample> {
	/**
	 * 保存    角色与资源 关系
	 * @param bigInteger
	 * @param resourcesIds
	 * @return
	 */
	public int saveRoleResources(Long roleId,
			Long[] resourcesIds);
}