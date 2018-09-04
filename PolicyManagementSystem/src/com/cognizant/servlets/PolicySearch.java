package com.cognizant.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cognizant.bo.DataBaseClass;
import com.cognizant.to.PolicyTO;

/**
 * Servlet implementation class PolicySearchServlet
 */
@WebServlet("/view/user/PolicySearch")
public class PolicySearch extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(PolicySearch.class);
	private static final long serialVersionUID = 1L;
    public PolicySearch() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PolicyTO p=new PolicyTO();
		 DataBaseClass dbFunction=new  DataBaseClass();
		RequestDispatcher rd=null;
		List<PolicyTO> lp=new ArrayList<PolicyTO>();
		p.setDurationInYears(Integer.parseInt(request.getParameter("Duration")));
		p.setCompany(request.getParameter("Company"));
		p.setPolicyType(request.getParameter("policytype"));
		lp=dbFunction.searchPolicy(p);
		if(lp.size()>0)
		{
			request.setAttribute("list", lp);
			request.setAttribute("page","search_policy");
			LOGGER.info("Policy search successful");
			rd=request.getRequestDispatcher("../../success.jsp");
		}
		else{
			request.setAttribute("page","search_policy");
			LOGGER.info("Policy search failed");
			rd=request.getRequestDispatcher("../../error.jsp");
		}
		rd.forward(request, response);
	}

}
