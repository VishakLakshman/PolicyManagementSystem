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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cognizant.dao.*;

@WebServlet("/view/admin/GetPolicyName")
public class GetPolicyName extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetPolicyName() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PreparedStatement ps;
		ResultSet rs;
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		System.out.println(request.getSession().getAttribute("username"));
		if (request.getSession().getAttribute("username") == null) {
			try {
				jo.put("pname", "null");
			} catch (JSONException e) {

			}
			response.getWriter().print(jo);
		} else {
			try {
				ps = ConnectionClass.openConnection().prepareStatement("select policy_name from policy_details");
				rs = ps.executeQuery();
				while (rs.next()) {
					jo = new JSONObject();
					jo.put("pname", rs.getString(1));
					ja.put(jo);
				}

			} catch (SQLException e) {

			} catch (JSONException e) {

			}

			jo = new JSONObject();
			try {
				jo.put("col", ja);
			} catch (JSONException e1) {

			}

			response.getWriter().print(jo);
			ConnectionClass.closeConnection();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONObject jo = new JSONObject();
		PreparedStatement ps;
		ResultSet rs;
		String str1 = request.getParameter("policy");
		JSONArray ja1 = new JSONArray();
		try {
			ps = ConnectionClass.openConnection().prepareStatement(
					"select duration,term_amount,policy_type from policy_details where policy_name=?");
			ps.setString(1, str1);
			rs = ps.executeQuery();
			while (rs.next()) {
				try {
					jo = new JSONObject();
					jo.put("policyduration", rs.getInt(1));
					ja1.put(jo);
					jo = new JSONObject();
					jo.put("policytermamount", rs.getInt(2));
					ja1.put(jo);
					jo.put("policytype", rs.getString(3));
					ja1.put(jo);
				} catch (JSONException e) {

				}
			}

			try {
				jo = new JSONObject();
				jo.put("data", ja1);
			} catch (JSONException e) {

			}
		} catch (SQLException e) {

		}

		response.getWriter().print(jo);
		ConnectionClass.closeConnection();
	}

}
