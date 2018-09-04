package com.cognizant.bo;

import java.util.Date;
import java.util.Random;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cognizant.dao.ConnectionClass;
import com.cognizant.dao.QueryInterface;
import com.cognizant.to.RegisterTO;


public class RegisterDataCheck implements QueryInterface {
	 JSONArray ja = new JSONArray();
	 JSONObject jo = new JSONObject();
	 final Logger LOGGER = Logger.getLogger(RegisterDataCheck.class);

	public  boolean checkBean(RegisterTO registerTO) {
		LOGGER.info("checkBean()");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		Date date1 = null;
		long age;
		long age1 = 0;
		try {
			date1 = dateFormat.parse(registerTO.getDateOfBirth());
			dateFormat.format(date1);
			age = Math.abs(date.getTime() - date1.getTime());
			age1 = age / (24 * 60 * 60 * 1000);
			age1 = age1 / 365;
		} catch (ParseException e) {
			LOGGER.error(e.getMessage());
		}
		try {
			PreparedStatement ps = ConnectionClass.openConnection()
					.prepareStatement(validate_query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("contact_number").equals(registerTO.getContactNumber())) {
					return false;
				}
				if (rs.getString("email").equals(registerTO.getEmail())) {
					return false;
				}
				if (rs.getString("pan_number").equals(registerTO.getPanNumber())) {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (age1 < 18 || age1 > 60) {
			return false;
		}
		dateFormat = new SimpleDateFormat("MMM");
		String m = dateFormat.format(date1);
		String pan = registerTO.getPanNumber();
		if (pan.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
			if (pan.charAt(3) == registerTO.getFirstName().charAt(0)
					&& pan.charAt(4) == registerTO.getLastName().charAt(0)
					&& pan.charAt(9) == m.charAt(0)) {
			} else {
				return false;
			}
		}
		return true;
	}

	public  String usertypeGen(RegisterTO b) {
		int income = b.getSalary() * 12;
		String uType = "R";
		if (income >= 0 && income <= 500000)
			uType = "A";
		else if (income > 500000 && income <= 1000000)
			uType = "B";
		else if (income > 1000000 && income <= 2000000)
			uType = "C";
		else if (income > 2000000 && income <= 3000000)
			uType = "D";
		else if (income > 3000000)
			uType = "E";

		return uType;
	}

	public  String passGenerator() {
		Random r = new Random();
		StringBuffer pass = new StringBuffer();
		String r2 = "0123456789";
		String r1 = "#$_";
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		Date d = new Date();
		pass.append(sdf.format(d));
		sdf = new SimpleDateFormat("MMM");
		pass.append(Character.toLowerCase(sdf.format(d).charAt(0)));
		for (int i = 1; i < sdf.format(d).length(); i++)
			pass.append(sdf.format(d).charAt(i));
		pass.append(r1.charAt(r.nextInt(r1.length())));
		for (int i = 0; i < 3; i++)
			pass.append(r2.charAt(r.nextInt(r2.length())));
		return pass.toString();
	}

	public  String usernameGen(RegisterTO r, int count, String utype) {
		StringBuffer sb = new StringBuffer(utype);
		DecimalFormat df = new DecimalFormat("1000");

		sb.append("-");
		sb.append(df.format(count));
		return sb.toString();
	}

}
