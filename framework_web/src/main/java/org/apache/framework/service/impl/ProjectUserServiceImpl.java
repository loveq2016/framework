package org.apache.framework.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.framework.model.ProjectUser;
import org.apache.framework.model.example.ProjectUserExample;
import org.apache.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.framework.dao.ProjectUserDao;
import org.apache.framework.service.ProjectUserService;
import org.apache.framework.util.ListUtils;

@Service
public class ProjectUserServiceImpl extends BaseServiceImpl<ProjectUser, ProjectUserExample>  implements ProjectUserService  {
	
	private ProjectUserDao projectUserDao;

    @Autowired
	public void setProjectUserDao(ProjectUserDao projectUserDao) {
		this.projectUserDao = projectUserDao;
		super.setBaseDao(projectUserDao);
	}

	@Override
	public List<String> selectByUserIdForProjectCodes(Long userId) {
		ProjectUserExample example = new ProjectUserExample();
		ProjectUserExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<ProjectUser> list = selectByExample(example);
		if (ListUtils.isNotEmpty(list)) {
			List<String> projectCodes = new ArrayList<String>();
			for (ProjectUser projectUser : list) {
				projectCodes.add(projectUser.getProjectCode());
			}
			return projectCodes;
		}
		return null;
	}
	 
}