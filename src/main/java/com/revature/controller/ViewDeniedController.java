package com.revature.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.dto.ReimDTO;
import com.revature.model.Reimbursement;
import com.revature.service.ReimbursementService;

public class ViewDeniedController {
	static ReimbursementService service = new ReimbursementService();

	public static String post(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String get(HttpServletRequest req) {
		String adminUser = (String)req.getSession().getAttribute("Admin");
		if(adminUser != null) {
			// load all the pending reimbursements from DB 
			List<Reimbursement> reims = service.getAllDeniedReimbursements();
			// put them into a List of ReimDTO objects
			List<ReimDTO> dtos = new ArrayList<>();
			for(Reimbursement reim : reims) {
				dtos.add(new ReimDTO(reim));
			}
			// set that list as a request attribute
			req.setAttribute("reimbursements", dtos);
			// forward to decision page
			return "/jsp/ViewUserReimbursements.jsp";
		}
		return "/html/nosucesslogin.html";
	}

}
