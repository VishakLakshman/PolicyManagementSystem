package com.cognizant.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cognizant.bo.DataBaseClass;
import com.cognizant.bo.PaymentModifier;
import com.cognizant.dao.ConnectionClass;
import com.cognizant.to.PaymentTO;
import com.cognizant.to.PolicyTO;

@WebServlet("/view/user/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	static final Logger LOGGER = Logger.getLogger(PaymentServlet.class);
	private static final long serialVersionUID = 1L;

	public PaymentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendError(401);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DataBaseClass dbFunction = new DataBaseClass();
		PaymentTO pb = new PaymentTO();
		PreparedStatement ps;
		PaymentModifier payModify = new PaymentModifier();
		PolicyTO po = new PolicyTO();
		RequestDispatcher rd = null;
		int lp;
		PaymentModifier pay = new PaymentModifier();
		pb.setPolicyId(request.getParameter("PolicyNo"));
		po = dbFunction.getPolicy(pb, po);
		pb.setBillDate(request.getParameter("bill_date"));
		pb.setDueDate(request.getParameter("due_date"));
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
		Date sd = null;
		Date ed = null;
		try {
			sd = sf.parse(request.getParameter("bill_date"));
			ed = sf.parse(request.getParameter("due_date"));
		} catch (ParseException e) {

		}

		pb.setPaymentAmount(po.getTermAmount());
		int res = payModify.dueDateEvaluator(sd, ed);
		if (res == 1) {
			pb.setFine(pay.fineCalculation(pb, po));
			pb.setStatus("WF");
		} else {
			pb.setFine(0);
			pb.setStatus("ON");
		}
		pb.setBillid(pay.billIdGenerator(pb, po.getPolicyType()));
		po = payModify.calculateTerms(po);
		pb.setBalanceamount(pay.balanceAmountCalculator(pb, po));
		lp = dbFunction.payment(pb);
		if (lp > 0) {
			request.setAttribute("paymentbean", pb);
			request.setAttribute("page", "policy_payment");
			LOGGER.info("Payment successful");
			try {
				ps = ConnectionClass
						.openConnection()
						.prepareStatement(
								"update user_policy_details set due_amount=? where policy_no=?");
				ps.setString(2,  pb.getPolicyId());
				int bal=(int) pb.getBalanceamount()- po.getTermAmount();
				ps.setInt(1, bal);
				ps.executeUpdate();
				ps = ConnectionClass
						.openConnection()
						.prepareStatement(
								"update user_policy_details set no_of_terms=? where policy_no=?");
				ps.setString(2,  pb.getPolicyId());
				System.out.println("terms"+po.getNoOfTerms());
				int no=po.getNoOfTerms()-1;
				ps.setInt(1,no);
				ps.executeUpdate();
				ps = ConnectionClass
						.openConnection()
						.prepareStatement(
								"update user_policy_details set next_due_date=? where policy_no=?");
				ps.setString(2, pb.getPolicyId());
				ps.setString(1, payModify.nextDueDateGenerator(po, ed));
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error("Exception on doPost() : " + e.getStackTrace());
			}

			rd = request.getRequestDispatcher("../../success.jsp");
		} else {
			LOGGER.info("Payment failed");
			rd = request.getRequestDispatcher("userpage.jsp");
		}
		rd.forward(request, response);
	}

}
