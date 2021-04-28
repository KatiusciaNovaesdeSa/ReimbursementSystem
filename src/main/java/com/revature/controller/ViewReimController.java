package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.dto.ReimDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ReimbursementService;

public class ViewReimController {
	static ReimbursementService service = new ReimbursementService();

	public static String post(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String get(HttpServletRequest req) {
		String roleUser = (String)req.getSession().getAttribute("User");
		System.out.println("Logged in user: " + roleUser);
		if(roleUser != null) {
			// get the User object for username
			User user = service.getUserByUserName2(roleUser);
			
			// load all the User's reimbursements from DB 
			List<Reimbursement> reims = service.getAllReimbursements(user);
			
			// put them into a List of ReimDTO objects
			List<ReimDTO> dtos = new ArrayList<>();
			for(Reimbursement reim : reims) {
				dtos.add(new ReimDTO(reim));
			}
			System.out.println("Result size DTOs " + dtos.size());
			// set that list as a request attribute
			req.setAttribute("reimbursements", dtos);
			// forward to view page
			return "/jsp/ViewUserReimbursements.jsp";
		}
		return "/html/nosucesslogin.html";
	}

}
