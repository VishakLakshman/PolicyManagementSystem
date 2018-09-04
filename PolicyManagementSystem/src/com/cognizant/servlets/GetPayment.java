package com.cognizant.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cognizant.bo.PaymentModifier;
import com.cognizant.dao.ConnectionClass;
import com.cognizant.dao.QueryInterface;

@WebServlet("/view/user/GetPayment")
public class GetPayment extends HttpServlet implements QueryInterface {
	private static final long serialVersionUID = 1L;
	private final Logger LOGGER = Logger.getLogger(GetPayment.class);

	public GetPayment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PreparedStatement ps;
		ResultSet rs;
		String type = request.getParameter("type");
		PaymentModifier payModify=new PaymentModifier();
		LOGGER.info(type);

		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		if (type.equals("id")) {
			LOGGER.info("Retriving Records based on : " + type);
			try {
				String name = request.getSession().getAttribute("username").toString();

				ps = ConnectionClass.openConnection().prepareStatement(get_policy_no);
				ps.setString(1, name);
				rs = ps.executeQuery();
				while (rs.next()) {
					jo = new JSONObject();
					jo.put("pno", rs.getString(1));
					ja.put(jo);
				}
			} catch (SQLException e) {
				LOGGER.error("Got into an Exception" + e.getMessage());
			} catch (JSONException e) {
				LOGGER.error("Got into an Exception" + e.getMessage());
			}

			jo = new JSONObject();
			try {
				LOGGER.info("Adding col..");
				jo.put("col", ja);
			} catch (JSONException e) {
				LOGGER.error("Got into an Exception" + e.getMessage());
			}
			response.getWriter().print(jo);
			ConnectionClass.closeConnection();
		} else {
			LOGGER.info("Retriving Records based on : " + type);
			try {
				ps = ConnectionClass.openConnection().prepareStatement(get_payment_details_byno);
				ps.setString(1, request.getParameter("policyno"));
				rs = ps.executeQuery();

				while (rs.next()) {
					Date sd = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					jo = new JSONObject();
					jo.put("sdate", sdf.format(sd));
					ja.put(jo);
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					jo = new JSONObject();
					String duedate = rs.getString(11);
					Date dd = sdf.parse(duedate);
					jo.put("duedate", new SimpleDateFormat("dd-MM-yyyy").format(dd));
					ja.put(jo);
					float fine = 0;
					if (payModify.dueDateEvaluator(sd, dd) > 1) {
						fine = payModify.fineCalculation(rs.getString(3), rs.getInt(8));
						System.out.println("fine"+fine);
						LOGGER.info("User " + request.getSession().getAttribute("username") + " has fine" + fine);
					} else
						fine = 0;
					jo = new JSONObject();
					jo.put("fine", fine);
					ja.put(jo);
					int years = 0;
					String startdate = rs.getString(4);
					String enddate = rs.getString(6);
					Date sdate = sdf.parse(startdate);
					Date edate = sdf.parse(enddate);
					years= payModify.calculateDurationInYears(sdate,edate);
					int terms = years * rs.getInt(12);
					int nof = rs.getInt(9);
					if (terms == nof) {
						float amount = rs.getFloat(7) + rs.getFloat(8);
						float pay = amount + fine;
						jo = new JSONObject();
						jo.put("pay", pay);
						ja.put(jo);
					} else {
						float amount = rs.getFloat(8);
						float pay = amount + fine;
						jo = new JSONObject();
						jo.put("pay", pay);
						ja.put(jo);
					}
				}

			} catch (SQLException e) {
				LOGGER.error("Got into an Exception" + e.getMessage());
			} catch (JSONException e) {
				LOGGER.error("Got into an Exception" + e.getMessage());
			} catch (ParseException e) {
				LOGGER.error("Got into an Exception" + e.getMessage());
			}
			jo = new JSONObject();
			try {
				jo.put("col", ja);
			} catch (JSONException e) {
				LOGGER.error("Got into an Exception" + e.getMessage());
			}
			response.getWriter().print(jo);
			ConnectionClass.closeConnection();
		}
	}
}
