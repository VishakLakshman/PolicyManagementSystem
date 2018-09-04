package com.cognizant.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cognizant.bo.DataBaseClass;
import com.cognizant.to.UserTO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOGGER.error("Un-Authorized Access - Session Id : "
				+ request.getSession().getId());
		response.sendError(401);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserTO uBean = new UserTO();
		 DataBaseClass dbFunction=new  DataBaseClass();
		uBean.setUserName(request.getParameter("UserName"));
		System.out.println(uBean.getUserName());
		uBean.setPassword(request.getParameter("Password"));
		System.out.println(uBean.getPassword());
		int status = dbFunction.checkUserData(uBean);
		HttpSession session = request.getSession();
		if (status == 1) {
			session.setAttribute("username", uBean.getUserName());
			session.setAttribute("type", "admin");
			session.setAttribute("title", "Admin");
			LOGGER.info("Admin " + uBean.getUserName()
					+ " has successfully logged in from device"+request.getRemoteAddr());
			response.sendRedirect("view/admin/adminpage.jsp");
		} else if (status == 2) {
			session.setAttribute("username", uBean.getUserName());
			session.setAttribute("type", "user");
			session.setAttribute("title", "User");
			LOGGER.info("User " + uBean.getUserName()
					+ " has successfully logged in from device"+request.getRemoteAddr());
			response.sendRedirect("view/user/userpage.jsp");
		} else {
			LOGGER.error("Error Occured while Login in - "
					+ uBean.getUserName()+" from device"+request.getRemoteAddr());
			response.sendRedirect("index.jsp");
		}
	}

}
