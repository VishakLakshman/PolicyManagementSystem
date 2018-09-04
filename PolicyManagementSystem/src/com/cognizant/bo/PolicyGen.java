package com.cognizant.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.cognizant.dao.ConnectionClass;
import com.cognizant.to.PolicyTO;

public class PolicyGen {
	private final  Logger LOGGER = Logger.getLogger(PolicyGen.class);
	public  String policyNumberGenerator(String ptype,String date)
	{
		LOGGER.info("policyNumberGenerator() : ");
		StringBuffer sb=new StringBuffer();
		int count=0;
		PreparedStatement ps;
		 try {
			 LOGGER.info("ptype : "+ptype+"date : "+date);
			ps = ConnectionClass.openConnection()
			.prepareStatement(
					"select substring_index(policy_no,'-',-1) from policy_details where policy_type like '"+ptype+"%' ");
			ResultSet rs = ps.executeQuery();
			rs.next();
			count = Integer.parseInt(rs.getString(1));
			count++;
		} catch (SQLException e) {
			LOGGER.error("Exception on policyNumberGenerator() : "+e.getLocalizedMessage());
		}

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date d=null;
		try {
			d=sdf.parse(date);
		} catch (ParseException e) {
			LOGGER.error("Exception on policyNumberGenerator() : "+e.getLocalizedMessage());
            sb.append("1994-01-01");
		}
		sdf=new SimpleDateFormat("yyyy");
		sb.append(ptype);
		sb.append("-");
		sb.append(sdf.format(d));
		sb.append("-");
		sb.append(Integer.toString(count));
		/*LOGGER.info("The ");*/
		return sb.toString();
	}
	public  double maturityAmountGenerator(PolicyTO p)
	{
		LOGGER.info("maturityAmountGenerator()");
		int init=p.getIntialDeposit();
		float interest=p.getInterest();
		int duration=p.getDurationInYears();
		int term_in_years=p.getTermPerYear();
		int term_amount=p.getTermAmount();
		double t=(double)(duration * term_in_years*term_amount);
		/*double matamount=0;
		matamount=(double)(init+t+(t*interest/100.00));
		return matamount;*/
		return (double)(init+t+(t*interest/100.00));
	}
	public  String endDateGenerator(String date,int duration)
	{
		LOGGER.info("endDateGenerator()");
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	  Date d=null;
	  try {
		d=sdf.parse(date);
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.YEAR, duration);
		d=cal.getTime();
	} catch (ParseException e) {
		LOGGER.error("Exception on endDategenerator() : "+e.getLocalizedMessage());
	}
	return sdf.format(d);
}
}
