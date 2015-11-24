package org.apache.framework.service.impl;


import org.apache.framework.model.ProjectUser;
import org.apache.framework.model.User;
import org.apache.framework.model.example.UserExample;
import org.apache.framework.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.framework.dao.UserDao;
import org.apache.framework.service.ProjectUserService;
import org.apache.framework.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, UserExample>  implements UserService  {
	
	private UserDao userDao;

	@Autowired
	private ProjectUserService projectUserService;
	
    @Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		super.setBaseDao(userDao);
	}

	@Override
	@Transactional
	public int insertConnect(User user) {
		insert(user);
		ProjectUser pu = new ProjectUser();
		pu.setProjectCode(user.getProjectCode());
		pu.setUserId(user.getId());
		return projectUserService.insert(pu);
	}
	 
}