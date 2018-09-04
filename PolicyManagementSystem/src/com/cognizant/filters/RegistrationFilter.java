package com.cognizant.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cognizant.bo.RegisterDataCheck;
import com.cognizant.to.RegisterTO;

@WebFilter("/RegistrationServlet")
public class RegistrationFilter implements Filter {

	static final Logger LOGGER = Logger.getLogger(RegistrationFilter.class);
    public RegistrationFilter() {

    }


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		RegisterTO bean=new RegisterTO();
		RegisterDataCheck dataCheck=new RegisterDataCheck();
		bean.setAddress(request.getParameter("user_address"));
		bean.setFirstName(request.getParameter("user_firstname"));
		bean.setLastName(request.getParameter("user_lastname"));
		bean.setDateOfBirth(request.getParameter("user_date_of_birth"));
		bean.setContactNumber(request.getParameter("user_contact_no"));
		bean.setEmail(request.getParameter("user_email_address"));
		bean.setQualification(request.getParameter("qualification"));
		bean.setGender(request.getParameter("user_gender"));
		bean.setSalary(Integer.parseInt(request.getParameter("salary")));
		System.out.println(Integer.parseInt(request.getParameter("salary")));
		bean.setPanNumber(request.getParameter("pannumber"));
		bean.setEmployerType(request.getParameter("employer_type"));
		bean.setEmployerName(request.getParameter("employer"));
		bean.setSecurityQuestion(request.getParameter("hint_question"));
		bean.setSecurityAnswer(request.getParameter("answer"));
		RequestDispatcher disp = null;
		request.setAttribute("bean",bean);

		if(dataCheck.checkBean(bean))
		{	LOGGER.info("success - chaining to RegistrationServlet");
			
			chain.doFilter(request, response);
		}
		else{
			LOGGER.info("Error - redirecting");
			disp=request.getRequestDispatcher("register.jsp");
			request.setAttribute("status","fails");
			request.setAttribute("bean",bean);
			disp.forward(request,response);
			}
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}
