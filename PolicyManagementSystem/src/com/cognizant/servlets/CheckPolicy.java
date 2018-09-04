package com.cognizant.servlets;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cognizant.dao.ConnectionClass;
import com.cognizant.dao.QueryInterface;

@WebServlet("/view/admin/CheckPolicy")
public class CheckPolicy extends HttpServlet implements QueryInterface {
	private static final Logger LOGGER = Logger.getLogger(CheckPolicy.class);
	private static final long serialVersionUID = 1L;

	public CheckPolicy() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String policy_name = request.getParameter("policy_name");
		PreparedStatement query;
		ResultSet result;
		String message;
		try {
			query = ConnectionClass.openConnection().prepareStatement(check_for_policy);
			LOGGER.info("Connection Opened : " + query.getConnection());
			query.setString(1, policy_name);
			result = query.executeQuery();
			if (result.next()) {
				message = "Policy Name already exists";
				LOGGER.error(policy_name + " already exists");
				response.getWriter().print(message);
			} else

				response.getWriter().print("no");
			ConnectionClass.closeConnection();
		} catch (SQLException e) {
			LOGGER.info("Exception on doGet() : " + e.getMessage());
			e.printStackTrace();
		}

	}

}