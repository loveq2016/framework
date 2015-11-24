package org.apache.framework.service;

import java.util.List;

import org.apache.framework.model.Project;
import org.apache.framework.model.example.ProjectExample;
import org.apache.framework.service.BaseService;

public interface ProjectService extends BaseService<Project, ProjectExample> {

	List<Project> selectByUserId(Long userId);
	
}