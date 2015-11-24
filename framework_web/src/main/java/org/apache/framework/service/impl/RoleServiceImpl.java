package org.apache.framework.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.framework.model.Role;
import org.apache.framework.model.UserRole;
import org.apache.framework.model.example.RoleExample;
import org.apache.framework.model.example.UserRoleExample;
import org.apache.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.framework.dao.RoleDao;
import org.apache.framework.service.RoleService;
import org.apache.framework.service.UserRoleService;
import org.apache.framework.util.ListUtils;

@Service(value="roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleExample>  implements RoleService  {
	
	private RoleDao roleDao;

	@Autowired
	private UserRoleService userRoleService;
	
    @Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		super.setBaseDao(roleDao);
	}

	@Override
	public List<Role> selectByUserId(Long userId) {
		List<UserRole> userRoles = userRoleService.selectByUserId(userId);
		if (ListUtils.isNotEmpty(userRoles)) {
			List<Long> roleIds = new ArrayList<Long>();
			for (UserRole userRole : userRoles) {
				roleIds.add(userRole.getRoleId());
			}
			RoleExample example = new RoleExample();
			RoleExample.Criteria criteria = example.createCriteria();
			criteria.andIdIn(roleIds);
			
			return selectByExample(example);
		}
		return null;
	}

	@Override
	public List<Role> selectByProjectCode(String projectCode) {
		setRoleDao(roleDao);
		RoleExample example = new RoleExample();
		RoleExample.Criteria criteria = example.createCriteria();
		criteria.andProjectCodeEqualTo(projectCode);
		return selectByExample(example);
	}
	 
}