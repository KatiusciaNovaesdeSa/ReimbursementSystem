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
	
//	public List<Reimbursement> getReimbursementsByAuthor(int author){
//		List<Reimbursement> reimb = new ArrayList<Reimbursement>();
//		reimb= rd.findByAuthorId(author);
//		return reimb;
//	}
	
//	public String login() {
//		 try
//		    {
//		        String userValidate = loginDao.authenticateUser(loginBean);
//		 
//		       
//		    }
//		    catch (IOException e1)
//		    {
//		        e1.printStackTrace();
//		    }
//		    catch (Exception e2)
//		    {
//		        e2.printStackTrace();
//		    }
//	}
	
	public Reimbursement submit(Reimbursement r) {
		rd.addReimbursementRequest(r);
		return r;
	}
	
	public boolean resolveReimbursement(User financeperson, Reimbursement r) {
		return rd.updateReimbursementStatus(financeperson, r);
	}
//	
//	public Reimbursement update(Reimbursement r) {
//		rd.approveReimbursement(r);
//		return r;
//	}

//	public Reimbursement getReimbursementById(int id) {
//		Reimbursement r = rd.findById(id);
//		return r;
//	}
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
	
//
//
//	public List<Reimbursement> getPendingReimbursementsByAuthor(int author){
//		List<Reimbursement> all = rd.findByAuthorId(author);
//		List<Reimbursement> pending = new ArrayList<Reimbursement>();
//		Reimbursement r;
//		Iterator<Reimbursement> itr = all.iterator();
//		while(itr.hasNext()) {
//			r = itr.next();
//			if (r.getStatus() == 1) {
//				pending.add(r);
//			}
//		}		
//		return pending;
//	}
//	
//	public List<Reimbursement> getPastReimbursementsByAuthor(int author){
//		List<Reimbursement> all = rd.findByAuthorId(author);
//		List<Reimbursement> past = new ArrayList<Reimbursement>();
//		Reimbursement r;
//		Iterator<Reimbursement> itr = all.iterator();
//		while(itr.hasNext()) {
//			r = itr.next();
//			if (r.getStatus() == 2 || r.getStatus() == 3) {	// If approved or denied
//				past.add(r);
//			}
//		}
//		//log.debug(past);
//		return past;
//	}
//	



	
	    /**
	     * Checks amount to make sense, returns false if
	     *  amount is negative or 0
	     *  does not match the syntax x.xx, x.x, or x
	     * @param inputAmount
	     * @return if amount is correct syntax
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
