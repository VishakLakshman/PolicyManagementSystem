package com.cognizant.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.cognizant.bo.SettingsChanges;
import com.cognizant.to.UserTO;

@WebServlet("/view/settings/Settings")
public class Settings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Settings.class);

	public Settings() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendError(401);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("doPost() : ");
		UserTO ub = new UserTO();
		SettingsChanges changeFunction=new SettingsChanges();
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		ub.setUserName(session.getAttribute("username").toString());
		ub.setPassword(request.getParameter("current_password"));
		if (type.equals("pass")) {
			LOGGER.info("Trying to change password : ");
			String new_password = request.getParameter("new_password");
			int success = changeFunction.changePassword(ub, new_password);
			if (success > 0) {
				LOGGER.info("password change is a success : ");
				rd = request.getRequestDispatcher("../../success.jsp");
				request.setAttribute("page", "password_change");
				LOGGER.info(ub.getUserName() + " changed password");
			} else {
				request.setAttribute("page", "password_change");
				rd = request.getRequestDispatcher("../../error.jsp");
			}
		} else if (type.equals("mobile")) {
			LOGGER.info("Trying to change mobile number : ");
			String new_phone = request.getParameter("new_number");
			int success = changeFunction.changeMobile(ub, new_phone);
			if (success > 0) {
				LOGGER.info("mobile number change is a success : ");
				request.setAttribute("page", "number_change");
				rd = request.getRequestDispatcher("../../success.jsp");
			} else {
				request.setAttribute("page", "number_change");
				rd = request.getRequestDispatcher("../../error.jsp");
			}

		} else if (type.equals("email")) {
			LOGGER.info("Trying to change email id : ");
			String new_mail = request.getParameter("new_email");
			int success = changeFunction.changeMail(ub, new_mail);
			if (success > 0) {
				LOGGER.info("email id change is a success : ");
				request.setAttribute("page", "email_change");
				rd = request.getRequestDispatcher("../../success.jsp");
			} else {
				request.setAttribute("page", "email_change");
				rd = request.getRequestDispatcher("../../error.jsp");
			}

		}
		rd.forward(request, response);
	}

}
