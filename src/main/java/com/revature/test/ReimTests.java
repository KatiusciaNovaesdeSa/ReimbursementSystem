package com.revature.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.service.ReimbursementService;


public class ReimTests {
	ReimbursementService rs;
	
	@Before
	public void setUp() throws Exception{
		rs = new ReimbursementService();
	}
	
	@After
	public void tearDown() throws Exception{
		rs = null;
	}

	@Test
	public void test() {
		//log.debug(ts.getRTypeById(2));
		assertEquals("Approved", rs.getAllApprovedReimbursements());
	}


}
