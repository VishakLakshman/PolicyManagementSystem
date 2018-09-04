package com.cognizant.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebServlet("/SessionValidate")
public class SessionValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger
			.getLogger(SessionValidate.class);

	public SessionValidate() {
		super();

	}
protected void doGet(HttpServletRequest request,
		HttpServletResponse response){
	try {
		response.sendError(401);
	} catch (IOException e) {
		LOGGER.error("Exception on doGet() : "+e.getMessage());
		e.printStackTrace();
	}
}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses = request.getSession();
		try {
			if (ses.getAttribute("username") != null) {
				if (ses.getAttribute("type").toString()
						.equalsIgnoreCase("admin"))
					response.getWriter().print(1);
				else if (ses.getAttribute("type").toString()
						.equalsIgnoreCase("user"))
					response.getWriter().print(2);
			} else {
				response.getWriter().print(0);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

}
