package org.apache.framework.service;

import java.util.List;

import org.apache.framework.model.Role;
import org.apache.framework.model.example.RoleExample;
import org.apache.framework.service.BaseService;

public interface RoleService extends BaseService<Role, RoleExample> {

	/**
	 * 根据用户ID查询角色
	 * @param userId
	 * @return
	 */
	List<Role> selectByUserId(Long userId);
	
	/**
	 * 根据项目code查询角色
	 * @param userId
	 * @return
	 */
	List<Role> selectByProjectCode(String projectCode);
	
	
}