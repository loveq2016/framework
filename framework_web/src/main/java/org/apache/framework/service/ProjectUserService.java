package org.apache.framework.service;

import java.util.List;

import org.apache.framework.model.ProjectUser;
import org.apache.framework.model.example.ProjectUserExample;
import org.apache.framework.service.BaseService;

public interface ProjectUserService extends BaseService<ProjectUser, ProjectUserExample> {

	public List<String> selectByUserIdForProjectCodes(Long userId);
}