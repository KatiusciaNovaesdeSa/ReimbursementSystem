package com.revature.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.ReimbDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.User;

public class UserService {
	
	private static Logger log = Logger.getLogger(UserService.class);
	//static ReimbDaoImpl ud = new ReimbDaoImpl();
	
    private ReimbDaoImpl rbDao;
	
	public UserService() {
		
	}
	

	public UserService(ReimbDaoImpl rbDao) {
		super();
		this.rbDao = rbDao;
		
	}
	
		
	public User getUserVerify(String username, String password) {
		
		User user = rbDao.getUserByName(username);
		if(user != null) {
			if(user.getPassword().equals(password)) {
			//	return user;
			}
		}
		
		return null;
	}
	

}
	
		
//	public List<User> getAllPendingUsers(){
//		User u = null;
//		List<User> users = new ArrayList<User>();
//		List<User> all = this.getAllPendingUsers();
//		Iterator<User> itr = all.iterator();
//		while (itr.hasNext()) {
//			u = itr.next();
//			if (u.getApproved() == 0) {
//				users.add(u);
//			}	
//		}
//		return users;
//	}
//
//	public User update(User u) {
//		log.debug(u);
//		ud.update(u);
//		return u;
//	}

	
	