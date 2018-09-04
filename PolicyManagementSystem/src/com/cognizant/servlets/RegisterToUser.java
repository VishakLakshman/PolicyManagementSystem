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
import com.cognizant.bo.SettingsChanges;
import com.cognizant.to.PolicyTO;


@WebServlet("/view/user/RegisterToUser")
public class RegisterToUser extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(RegisterToUser.class);
	private static final long serialVersionUID = 1L;


	public RegisterToUser() {
		super();
	}
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DataBaseClass dbFunction=new  DataBaseClass();
		SettingsChanges changeFunction=new SettingsChanges();
		PolicyTO pb1=new PolicyTO();
		PolicyTO pb2=new PolicyTO();
		RequestDispatcher rd=null;
	    int lp=0;
        String name =request.getSession().getAttribute("username").toString();
        String policynumber=request.getParameter("policynum");
    	pb1=dbFunction.getPolicy(policynumber);
    	int regid=dbFunction.getUserId(name);
        pb2.setPolicyNo("policynumber");
        pb2.setStartDate(changeFunction.currendate(pb1));
        pb2.setMaturityAmount(pb1.getMaturityAmount());
        pb2.setEndDate(changeFunction.enddate(pb1));
        pb2.setIntialDeposit(pb1.getIntialDeposit());
        pb2.setTermAmount(pb1.getTermAmount());
        pb2.setNoOfTerms(changeFunction.calTerms(pb1));
        int  dueAmount=changeFunction.dueamont(pb1);
        String nextDue=changeFunction.nextduedate(pb1);
        pb2.setTermPerYear(pb1.getTermPerYear());
        lp=dbFunction.userPolicy(pb1,name,dueAmount,regid,nextDue);
		if(lp>0)
		{
			request.setAttribute("page","user_register_policy");
			LOGGER.info("user policy registeration successfully");
			rd = request.getRequestDispatcher("../../success.jsp");
			
		}
		else
		{
			request.setAttribute("page","user_register_policy");
			LOGGER.info("user policy registeration failed");
			rd = request.getRequestDispatcher("../../error.jsp");
		}
        
        rd.forward(request, response);
	}

}
