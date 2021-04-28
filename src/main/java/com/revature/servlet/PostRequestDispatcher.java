package com.revature.servlet;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.*;

public class PostRequestDispatcher {

	public static String process(HttpServletRequest req){
		switch(req.getRequestURI()){
			case "/ReimbursementSystem/addReim.ers":
				return AddReimburseController.post(req);
			default:
				return "html/nosuccesslogin";
		}
	}
}
