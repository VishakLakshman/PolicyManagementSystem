package com.cognizant.servlets;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cognizant.dao.ConnectionClass;
import com.cognizant.dao.QueryInterface;

@WebServlet("/view/user/GetPolicy")
public class GetPolicy extends HttpServlet implements QueryInterface{
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(GetPolicy.class);

	public GetPolicy() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendError(401);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PreparedStatement ps;
		ResultSet rs;
		String type = request.getParameter("getype");
		String uType=new Character(request.getSession().getAttribute("username").toString().charAt(0)).toString();
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		if(type==null)
			LOGGER.info("type is not defined");
		LOGGER.info("Retrieving details based on "+type);
		if (type.equals("year")) {
			try {
				String ptype = request.getParameter("policytype");
				ps = ConnectionClass
						.openConnection()
						.prepareStatement(
								get_policy_duration);
				ps.setString(1, ptype);
				ps.setString(2,uType);
				rs = ps.executeQuery();
				while (rs.next()) {
					jo = new JSONObject();
					jo.put("duration", Integer.toString(rs.getInt(1)));
					ja.put(jo);
				}
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			} catch (JSONException e) {
				LOGGER.error(e.getMessage());
			}

			jo = new JSONObject();
			try {
				jo.put("col", ja);
			} catch (JSONException e) {
				LOGGER.error(e.getMessage());
			}
			LOGGER.info("Records sent");
			response.getWriter().print(jo);
			ConnectionClass.closeConnection();
		} else if (type.equals("company")) {
			try {
				String ptype = request.getParameter("policytype");
				int duration = Integer.parseInt(request
						.getParameter("duration"));
				ps = ConnectionClass
						.openConnection()
						.prepareStatement(
								"select distinct(company) from policy_details where policy_type=(?) and duration=(?)");
				ps.setString(1, ptype);
				ps.setInt(2, duration);
				rs = ps.executeQuery();
				while (rs.next()) {
					jo = new JSONObject();
					jo.put("company", rs.getString(1));
					ja.put(jo);
				}

			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			} catch (JSONException e) {
				LOGGER.error(e.getMessage());
			}

			jo = new JSONObject();
			try {
				jo.put("col", ja);
			} catch (JSONException e) {
				LOGGER.error(e.getMessage());
			}

			response.getWriter().print(jo);
			ConnectionClass.closeConnection();

		} else if (type.equals("policy")) {
			try {
				String ptype = request.getParameter("policytype");
				int duration = Integer.parseInt(request.getParameter("duration"));
				String company = request.getParameter("company");
				String username=request.getSession().getAttribute("username").toString();
				ps = ConnectionClass
						.openConnection()
						.prepareStatement(
							"select policy_no,policy_name from policy_details where policy_type=(?) and duration=(?) and company=(?)");/* and policy_no not in (select policy_no from user_policy_details where user_name=?)");*/
				ps.setString(1, ptype);
				ps.setInt(2, duration);
				ps.setString(3, company);
				ps.setString(4,username);
				rs = ps.executeQuery();
				while (rs.next()) {
					jo = new JSONObject();
					jo.put("policyno", rs.getString(1));
					ja.put(jo);
					jo = new JSONObject();
					jo.put("policyname", rs.getString(2));
					ja.put(jo);
				}

			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			} catch (JSONException e) {
				LOGGER.error(e.getMessage());
			}

			jo = new JSONObject();
			try {
				jo.put("col", ja);
			} catch (JSONException e) {
				LOGGER.error(e.getMessage());
			}

			response.getWriter().print(jo);
			ConnectionClass.closeConnection();

		}

	}
}
