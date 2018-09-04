package com.cognizant.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cognizant.bo.DataBaseClass;
import com.cognizant.bo.PolicyGen;
import com.cognizant.to.PolicyTO;


@WebServlet("/view/admin/PolicyEditServlet")
public class PolicyEditServlet extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(PolicyEditServlet.class);
	private static final long serialVersionUID = 1L;
       

    public PolicyEditServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("doPost() : "); 
		 DataBaseClass dbFunction=new  DataBaseClass();
		 PolicyGen pGenerator=new PolicyGen();
		PolicyTO p=new PolicyTO();
		p.setPolicyName(request.getParameter("edit_policy_name"));
		p.setPolicyType(request.getParameter("edit_policy_type"));
		p.setDurationInYears(Integer.parseInt(request.getParameter("edit_duration")));
		p.setTermAmount(Integer.parseInt(request.getParameter("edit_term_amount")));
		RequestDispatcher rd=null;
		LOGGER.info("PolicyType : "+p.getPolicyType());
		if(dbFunction.checkPolicy(p)>0)
		{
		  p=dbFunction.getPolicyId(p);
		  p.setPolicyNo(pGenerator.policyNumberGenerator(p.getPolicyType(), p.getStartDate()));
		  p.setMaturityAmount(pGenerator.maturityAmountGenerator(p));
		  if(dbFunction.editPolicy(p)>0)
		  {
			  request.setAttribute("page","edit_policy");
			  request.setAttribute("msg",p.getPolicyName());
			  rd=request.getRequestDispatcher("../../success.jsp");
		  }
		  else
		  {request.setAttribute("page","edit_policy");
			  rd=request.getRequestDispatcher("../../error.jsp");
		  }
		}
		else
		{
			LOGGER.info("Policy Edit failed");
			rd=request.getRequestDispatcher("../../error.jsp");
		}
			rd.forward(request, response);
		  
	}

}
