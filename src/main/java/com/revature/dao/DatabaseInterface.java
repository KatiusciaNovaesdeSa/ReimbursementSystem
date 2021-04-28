package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.model.User;

public interface DatabaseInterface {

	boolean denyReimbursement(Reimbursement request);
	
	boolean approveReimbursement(Reimbursement request);
	
	List<Reimbursement> viewAllReimbursementRequests(Status status);

    public List<Reimbursement> viewReimbursementRequests(User user);
   
    public boolean addReimbursementRequest(Reimbursement request);

    Reimbursement getReimbursementById(int reimbursementId);

    List<Status> getStatuses();


}
