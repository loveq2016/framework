package org.apache.framework.service.impl;


import java.util.List;

import org.apache.framework.model.UserRole;
import org.apache.framework.model.example.UserRoleExample;
import org.apache.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.framework.dao.UserRoleDao;
import org.apache.framework.service.UserRoleService;

@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, UserRoleExample>  implements UserRoleService  {
	
	private UserRoleDao userRoleDao;

    @Autowired
	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
		super.setBaseDao(userRoleDao);
	}

	@Override
	public List<UserRole> selectByUserId(Long userId) {
		UserRoleExample example = new UserRoleExample();
		UserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return selectByExample(example);
	}
	 
	@Override
	public int addUserRole(Long userId, Long roleId) {
		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		return insert(userRole);
	}

	@Override
	public int deleteUserRole(Long userId, Long roleId) {
		UserRoleExample example = new UserRoleExample();
		UserRoleExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andRoleIdEqualTo(roleId);
		return deleteByExample(example);
	}
}