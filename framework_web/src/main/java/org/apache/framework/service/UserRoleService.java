package org.apache.framework.service;

import java.util.List;

import org.apache.framework.model.UserRole;
import org.apache.framework.model.example.UserRoleExample;
import org.apache.framework.service.BaseService;

public interface UserRoleService extends BaseService<UserRole, UserRoleExample> {
	
	public List<UserRole> selectByUserId(Long userId);
	
	public int addUserRole(Long userId, Long roleId);
    
    public int deleteUserRole(Long userId, Long roleId);
}