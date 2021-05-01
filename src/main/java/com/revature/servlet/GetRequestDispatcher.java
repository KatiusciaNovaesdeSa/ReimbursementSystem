package com.revature.servlet;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.*;

public class GetRequestDispatcher {

	public static String process(HttpServletRequest req){
		switch(req.getRequestURI()){
			case "/ReimbursementSystem/addReim.ers":
				return  AddReimburseController.get(req);
			case "/ReimbursementSystem/viewReim.ers":
				return ViewReimController.get(req);
			case "/ReimbursementSystem/viewAllReim.ers":
				return ViewAllUserReimController.get(req);
			case "/ReimbursementSystem/viewPending.ers":
				return ViewPendingController.get(req);
			case "/ReimbursementSystem/viewApproved.ers":
				return ViewApprovedController.get(req);
			case "/ReimbursementSystem/viewDenied.ers":
				return ViewDeniedController.get(req);
			default:
				return "html/nosuccesslogin";
		}
	}
		
}
