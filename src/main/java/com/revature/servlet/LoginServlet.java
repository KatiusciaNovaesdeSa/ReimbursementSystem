package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.model.Login;
import com.revature.dao.ReimbDaoImpl;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		Login loginBean = new Login();

		loginBean.setUserName(userName);
		loginBean.setPassword(password);

		ReimbDaoImpl loginDao = new ReimbDaoImpl();

		try {
			String userValidate = loginDao.authenticateUser(loginBean);

			if (userValidate.equals("Admin_Role")) {
				System.out.println("Admin's Home");

				HttpSession session = request.getSession(); // Creating a session
				session.setAttribute("Admin", userName); // setting session attribute
				request.setAttribute("userName", userName);

				request.getRequestDispatcher("/html/admin.html").forward(request, response);
			} else if (userValidate.equals("User_Role")) {
				System.out.println("User's Home");

				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(10 * 60);
				session.setAttribute("User", userName);
				request.setAttribute("userName", userName);

				request.getRequestDispatcher("/html/user.html").forward(request, response);
			} else {
				System.out.println("Error message = " + userValidate);
				request.setAttribute("errMessage", userValidate);

				request.getRequestDispatcher("/html/nosucesslogin.html").forward(request, response);
				
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	} // End of doPost()
}

//
//
//public class LoginController {
//	
//	public static String login(HttpServletRequest request){
//		if(request.getMethod().equals(FinalUtil.HTTP_Get)) return "index.html";
//
//		User loggedUser = UserService.getStaffService().login(
//				new User(
//						0, request.getParameter("username"),
//						request.getParameter("password"), null, null, null, null
//						));
//		if (loggedUser.getUsername().equals("")){
//			return "index.html";
//		} else{
//			//REturn another URI and create controller that handles that 
//			//return "/customer.do"; //The DAO method
//		}
//		request.getSession().setAttribute("loggedUser", loggedUser);
//		if(loggedUser.getRole() == 1){
//			return "manager.jsp";
//		}else if (loggedUser.getRole() == 2){
//			return "employee.jsp";
//		}
//		return "index.html";
//	}
//
//}