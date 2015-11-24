package org.apache.framework.service.impl;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.framework.model.Resources;
import org.apache.framework.model.RoleResources;
import org.apache.framework.model.example.ResourcesExample;
import org.apache.framework.model.example.RoleResourcesExample;
import org.apache.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.framework.dao.ResourcesDao;
import org.apache.framework.service.ResourcesService;
import org.apache.framework.service.RoleResourcesService;
import org.apache.framework.util.ListUtils;

@Service
public class ResourcesServiceImpl extends BaseServiceImpl<Resources, ResourcesExample>  implements ResourcesService  {
	
	private ResourcesDao resourcesDao;

    @Autowired
	public void setResourcesDao(ResourcesDao resourcesDao) {
		this.resourcesDao = resourcesDao;
		super.setBaseDao(resourcesDao);
	}
    
    @Autowired
    private RoleResourcesService roleResourcesService;

	@Override
	public List<Resources> selectByRoleIds(List<Long> roleIds) {
		RoleResourcesExample rrExample = new RoleResourcesExample();
		RoleResourcesExample.Criteria rrCriteria = rrExample.createCriteria();
		rrCriteria.andRoleIdIn(roleIds);
		List<RoleResources> roleResources = roleResourcesService.selectByExample(rrExample);
		if (ListUtils.isNotEmpty(roleResources)) {
			Set<Long> resourceIds = new HashSet<Long>();
			for (RoleResources roleResource : roleResources) {
				resourceIds.add(roleResource.getResourcesId());
			}
			ResourcesExample rExample = new ResourcesExample();
			rExample.setOrderByClause(" sequence asc ");
			ResourcesExample.Criteria rCriteria = rExample.createCriteria();
			rCriteria.andIdIn(new ArrayList<Long>(resourceIds));
			
			return selectByExample(rExample);
		}
		return null;
	}

	@Override
	public List<Resources> selectByProjectCode(List<String> projectCodes) {
		ResourcesExample example = new ResourcesExample();
		example.setOrderByClause(" sequence asc ");
		ResourcesExample.Criteria criteria = example.createCriteria();
		criteria.andProjectCodeIn(projectCodes);
		return selectByExample(example);
	}

	@Override
	public List<Resources> selectByRoleId(Long roleId) {
		List<Long> roleIds = new ArrayList<Long>();
		roleIds.add(roleId);
		return selectByRoleIds(roleIds);
	}

}