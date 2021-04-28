package com.revature.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Reimbursement;
import com.revature.model.Status;
import com.revature.model.User;
import com.revature.service.ReimbursementService;

public class DenyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReimbursementService service = new ReimbursementService();

	public DenyServlet() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		// get the reimbursement by id
		List<Reimbursement> reims = service.viewReimbursements("reimbId", Integer.parseInt(id));
		if (reims != null && reims.size() > 0) {
			Reimbursement reim = reims.get(0);
			// get the finance user object from db
			String username = (String) request.getSession().getAttribute("Admin");

			User financePerson = service.getUserByUserName2(username);
			// update the reimbursement object
			reim.setStatus(new Status(3, "Denied"));
			// call update reimbursement in db
			service.resolveReimbursement(financePerson, reim);
		}
		// route to FinanceDecisionPage
		//request.getRequestDispatcher("/ReimbursementSystem/viewPending.ers").forward(request, response);
		request.getRequestDispatcher("/html/admin.html").forward(request, response);
	}
}


