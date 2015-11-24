package org.apache.framework.service;

import org.apache.framework.model.User;
import org.apache.framework.model.example.UserExample;
import org.apache.framework.service.BaseService;

public interface UserService extends BaseService<User, UserExample> {
	
	public int insertConnect(User user);
}