package org.apache.framework.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.framework.model.RoleResources;
import org.apache.framework.model.example.RoleResourcesExample;
import org.apache.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.framework.dao.RoleResourcesDao;
import org.apache.framework.service.RoleResourcesService;

@Service
public class RoleResourcesServiceImpl extends BaseServiceImpl<RoleResources, RoleResourcesExample>  implements RoleResourcesService  {
	
	private RoleResourcesDao roleResourcesDao;

    @Autowired
	public void setRoleResourcesDao(RoleResourcesDao roleResourcesDao) {
		this.roleResourcesDao = roleResourcesDao;
		super.setBaseDao(roleResourcesDao);
	}
	 
    @Override
	public int saveRoleResources(Long roleId, Long[] resourcesIds) {
    	deleteByRoleId(roleId);
    	List<RoleResources> list = new ArrayList<RoleResources>();
    	for (Long resourcesId : resourcesIds) {
			RoleResources rr = new RoleResources();
			rr.setRoleId(roleId);
			rr.setResourcesId(resourcesId);
			list.add(rr);
		}
		return batchInsert(list);
	}
    
    public int deleteByRoleId(Long roleId) {
    	RoleResourcesExample example = new RoleResourcesExample();
    	RoleResourcesExample.Criteria criteria = example.createCriteria();
    	criteria.andRoleIdEqualTo(roleId);
    	return deleteByExample(example);
    }
}