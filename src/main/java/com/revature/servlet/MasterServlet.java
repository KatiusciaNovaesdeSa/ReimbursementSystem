package com.revature.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		System.out.println("Received get request");
		req.getRequestDispatcher(GetRequestDispatcher.process(req)).forward(req,res);		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		System.out.println("Received post request");
		req.getRequestDispatcher(PostRequestDispatcher.process(req)).forward(req,res);
		
	}
}