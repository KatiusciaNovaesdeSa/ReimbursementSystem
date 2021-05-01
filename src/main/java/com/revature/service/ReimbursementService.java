package com.revature.service;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.dao.ReimbDaoImpl;
import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.model.User;

public class ReimbursementService {
	
	static ReimbDaoImpl rd = new ReimbDaoImpl();

	
	public Reimbursement submit(Reimbursement r) {
		rd.addReimbursementRequest(r);
		return r;
	}
	
	public boolean resolveReimbursement(User financeperson, Reimbursement r) {
		return rd.updateReimbursementStatus(financeperson, r);
	}

	public List<Reimbursement> getAllUsersReimbursements(){
		List<Reimbursement> reimb = new ArrayList<Reimbursement>();
	 	reimb = rd.viewAllUsersReimbursementRequests();	
		return reimb;
	}
	
	public List<Reimbursement> getAllReimbursements(User user){
		List<Reimbursement> reimb = new ArrayList<Reimbursement>();
	 	reimb = rd.viewReimbursementRequests(user);	
		return reimb;
	}
	
	public List<Reimbursement> getAllPendingReimbursements(){
		List<Reimbursement> reimb = new ArrayList<Reimbursement>();
		reimb = rd.viewAllReimbursementRequests(new Status(1, "Pending"));	
		return reimb;
	}
	
	public List<Reimbursement> getAllApprovedReimbursements(){
		List<Reimbursement> reimb = new ArrayList<Reimbursement>();
		reimb = rd.viewAllReimbursementRequests(new Status(2, "Approved"));	
		return reimb;
	}
	
	public List<Reimbursement> getAllDeniedReimbursements(){
		List<Reimbursement> reimb = new ArrayList<Reimbursement>();
		reimb = rd.viewAllReimbursementRequests(new Status(3, "Denied"));	
		return reimb;
	}
	
	public User getUserByUserName(String username) {
		return rd.getUserByName(username);
	}
	
	public User getUserByUserName2(String username) {
		
		return rd.getUserByUserName2(username);
	}
	

	
	    /**
	     * Checks amount to make sense, returns false if
	     *  amount is negative or 0
	     */
	public boolean validateAmount(double inputAmount) {
	        System.out.println("inputAmount is " + inputAmount);
	        if (inputAmount <= 0) {
	            System.out.println("less than 0, returning false");
	            return false;
	        }
	        // String to be scanned to find the pattern.
	        String amount = Double.toString(inputAmount);
	        return amount.matches("^[+-]?[0-9]{1,3}(?:,?[0-9]{3})*(?:\\.[0-9]{1}[0-9]?)?$");
	 }

    public List<Reimbursement> viewReimbursements(String condition, int id) {
			try {
				return rd.viewReimbursements(condition, id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}

	

		

		
	


}
