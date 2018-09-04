package com.cognizant.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cognizant.bo.DataBaseClass;
import com.cognizant.bo.DataBaseClass;
import com.cognizant.bo.RegisterDataCheck;
import com.cognizant.dao.ConnectionClass;
import com.cognizant.to.RegisterTO;
import com.cognizant.to.UserTO;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendError(401);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("doPost() : ");
		RequestDispatcher disp = null;
		RegisterDataCheck dataCheck=new RegisterDataCheck();
		RegisterTO rBean = (RegisterTO) request.getAttribute("regbean");
		System.out.println(rBean.getContactNumber());
		UserTO uBean = new UserTO();
		String utype = dataCheck.usertypeGen(rBean);
		int status = new DataBaseClass().registerUser(rBean, utype);
		if (status > 0) {
			try {
				PreparedStatement ps = ConnectionClass.openConnection()
						.prepareStatement(
								"select max(user_id) from user_credentials where user_name like '"+utype+"%' ");
				LOGGER.info("select Query");
				ResultSet rs = ps.executeQuery();
				rs.next();
				int val = rs.getInt(1);
				uBean.setUserName(dataCheck.usernameGen(rBean, val + 1,
						utype));
				uBean.setPassword(dataCheck.passGenerator());
				ConnectionClass.closeConnection();
				if (new DataBaseClass().putUserData(uBean) > 0) {
					request.setAttribute("bean", uBean);
					request.setAttribute("page","user_register");
					request.setAttribute("message","The user is registered successfully");
					LOGGER.info("Registration successful");
					disp = request.getRequestDispatcher("success.jsp");
				}

				else {
					LOGGER.info("Registration failed");
					request.setAttribute("page","user_register");
					disp = request.getRequestDispatcher("error.jsp");
				}
			} catch (SQLException e) {
				LOGGER.info("Got Exception while Registering");
				e.printStackTrace();
			}
			disp.forward(request, response);
		}

	}
}
