package com.revature.service;


import org.mindrot.jbcrypt.BCrypt;

import com.revature.dao.ReimbDaoImpl;
import com.revature.model.User;

public class UserService {
	
	//private static Logger log = Logger.getLogger(UserService.class);
	
    private ReimbDaoImpl rbDao;
	
	public UserService() {
		
	}
	

	public UserService(ReimbDaoImpl rbDao) {
		super();
		this.rbDao = rbDao;
		
	}
	
		
	public User getUserVerify(String username, String password) {
		
		User user = rbDao.getUserByName(username);
		if(user != null && BCrypt.checkpw(password, user.getPassword())) {
			if(user.getPassword().equals(password)) {
			//	return user;
			}
		}
		
		return null;
	}
	

}
	
		

	
	