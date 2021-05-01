package com.revature.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import com.revature.util.ReimConnection;

import com.revature.dao.ReimbDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.Type;
import com.revature.model.Status;
import com.revature.model.User;

public class ReimTests {
	private static final User John = null;
	private static final Status Pending = null;
	ReimbursementService rs;
	private ReimbDaoImpl dao;
	Reimbursement newReim;
	User newUser;
	UserService us;
	Status status;
	Type newType;

	
	@Before
	public void setUp() throws Exception{
		rs = new ReimbursementService();
		us = new UserService();
		dao = new ReimbDaoImpl();
		newUser = new User();
		newReim = new Reimbursement();
		newType = new Type();
		status = new Status();
	}
	
	@After
	public void tearDown() throws Exception{
		rs = null;
	}
	
//	@Test(timeout=1000)  // milliseconds
//    public void test() {
//	      while (true) {}
//	}
	
	
	@Test
	public void testConnection() {
		assertNotNull(ReimConnection.getConnection());
	}
	
	
	@Test
	public void findAUsers() {
		User u = dao.getUserByUserName2("kati");	
		assertNotNull(u);		
	}
	
	
	@Test
	public void findBUser() {	
		User u = dao.getUserByUserName2("minnie");	
		assertNotNull(u);
		
	}
	
	@Test
	public void findAdmUser() {	
		User u = dao.getUserByUserName2("admin");	
		assertNotNull(u);
		
	}
	
	@Test
	public void insertUser() {
		newUser.setId(10);
		newUser.setFirstName("John");
		newUser.setLastName("Pitt");
		newUser.setEmail("jp@me.com");
		newUser.setUsername("john");
		newUser.setPassword("password");
		equals(newUser);
	}
	
	@Test
	public void insertReimb() {
		newReim.setId(20);
		newReim.setAmount(100);
		newReim.setDescription("Pitt");
		newReim.setAuthor(John);
		equals(newReim);
	}
	
	@Test
	public void insertType() {
		newType.setId(20);
		newType.setType("Drinks");
		equals(newType);
	}
	

}
