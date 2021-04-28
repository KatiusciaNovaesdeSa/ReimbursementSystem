package com.revature.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.model.Type;
import com.revature.model.User;
import com.revature.service.ReimbursementService;

public class AddReimburseController {
	static ReimbursementService service = new ReimbursementService();

	public static String post(HttpServletRequest req) {
		//text - amount - 
		//textarea - description
		//select - type
		String username = (String)req.getSession().getAttribute("User");
		String amount = req.getParameter("amount").trim();
        String description = req.getParameter("description").trim();
        String typeString = req.getParameter("type").trim();
        
        User author = service.getUserByUserName2(username);
        Type type = createTypeFromNumber(typeString);
        Status status = new Status(1, "Pending");
        double amountDouble = convertToDouble(amount);
        System.out.println(username);
        System.out.println(author == null ? "Author is null" : "Author not null");
        
        Reimbursement reim = new Reimbursement(0, amountDouble, new Timestamp(System.currentTimeMillis()), null, 
        		author, description, status, type);
		service.submit(reim);
        
		return "/html/user.html";
	}

	private static double convertToDouble(String amount) {
		try {
			return Double.parseDouble(amount);
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return 0;
	}

	private static Type createTypeFromNumber(String typeString) {
		try {
			int typeNumber = Integer.parseInt(typeString);
			switch(typeNumber) {
				case 1:	return new Type(1, "Lodging");
				
				case 2: return new Type(2, "Travel");
				
				case 3:	return new Type(3, "Food");
				
				case 4: return new Type(4, "Other");
				
						
			}
			
		}
		catch(NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return null;
	}

	public static String get(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
