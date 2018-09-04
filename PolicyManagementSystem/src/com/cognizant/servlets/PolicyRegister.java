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

@WebServlet("/view/admin/PolicyRegister")
public class PolicyRegister extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(PolicyRegister.class);
	private static final long serialVersionUID = 1L;
       
    public PolicyRegister() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(401);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			 DataBaseClass dbFunction=new  DataBaseClass();
		PolicyTO policyTO=new PolicyTO();
		PolicyGen pGenerator=new PolicyGen();
		RequestDispatcher disp = null;
		policyTO.setPolicyName(request.getParameter("policy_name"));
		policyTO.setStartDate(request.getParameter("policy_start_date"));
		policyTO.setDurationInYears(Integer.parseInt(request.getParameter("policy_duration")));
		policyTO.setCompany(request.getParameter("company"));
		policyTO.setIntialDeposit(Integer.parseInt(request.getParameter("policy_initial_deposit")));
		policyTO.setPolicyType(request.getParameter("policy_type"));
		policyTO.setUserType(request.getParameter("user_type"));
		policyTO.setTermPerYear(Integer.parseInt(request.getParameter("policy_terms")));
		policyTO.setTermAmount(Integer.parseInt(request.getParameter("policy_term_amount")));
		policyTO.setInterest(Float.parseFloat(request.getParameter("policy_interest")));
		policyTO.setPolicyNo(pGenerator.policyNumberGenerator(policyTO.getPolicyType(), policyTO.getStartDate()));
		policyTO.setMaturityAmount(pGenerator.maturityAmountGenerator(policyTO));
		policyTO.setEndDate(pGenerator.endDateGenerator(policyTO.getStartDate(),policyTO.getDurationInYears()));
		//int status = dbFunction.putPolicyData(policyTO);
		if(dbFunction.putPolicyData(policyTO)>0)
		{
			request.setAttribute("pbean",policyTO);
			request.setAttribute("page","policy_register");
			LOGGER.info("Policy Registration successful for policy : "+policyTO.getPolicyName());
			disp = request.getRequestDispatcher("../../success.jsp");
		}
		else
		{
			LOGGER.info("Policy Registration failed");
			request.setAttribute("page","policy_register");
			disp = request.getRequestDispatcher("../../error.jsp");
		}
		disp.forward(request, response);
		}catch(Exception e){
			
		}
	}

}
