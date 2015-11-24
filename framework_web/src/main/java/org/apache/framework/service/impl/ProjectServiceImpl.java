package org.apache.framework.service.impl;


import java.util.List;

import org.apache.framework.model.Project;
import org.apache.framework.model.example.ProjectExample;
import org.apache.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.framework.dao.ProjectDao;
import org.apache.framework.service.ProjectService;
import org.apache.framework.service.ProjectUserService;
import org.apache.framework.util.ListUtils;

@Service
public class ProjectServiceImpl extends BaseServiceImpl<Project, ProjectExample>  implements ProjectService  {
	
	private ProjectDao projectDao;

	@Autowired
	private ProjectUserService projectUserService;
	
    @Autowired
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		super.setBaseDao(projectDao);
	}

	@Override
	public List<Project> selectByUserId(Long userId) {
		List<String> projectCodes = projectUserService.selectByUserIdForProjectCodes(userId);
		if (ListUtils.isNotEmpty(projectCodes)) {
			ProjectExample example = new ProjectExample();
			ProjectExample.Criteria criteria = example.createCriteria();
			criteria.andCodeIn(projectCodes);
			return selectByExample(example);
		}
		return null;
	}
	 
}