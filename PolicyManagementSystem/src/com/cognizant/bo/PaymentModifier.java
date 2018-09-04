package com.cognizant.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.cognizant.dao.ConnectionClass;
import com.cognizant.dao.QueryInterface;
import com.cognizant.to.PaymentTO;
import com.cognizant.to.PolicyTO;

public class PaymentModifier implements QueryInterface{
	private final Logger LOGGER = Logger.getLogger(PaymentModifier.class);

	public  int dueDateEvaluator(Date sd, Date ed) {

		long diffInMillisec = 0;
		int diffInDays = 0;
		Calendar firstDate = null;
		Calendar secondDate = null;
		try {
			
			firstDate = Calendar.getInstance();
			secondDate = Calendar.getInstance();

			
			firstDate.setTime(sd);
			secondDate.setTime(ed);

			
			diffInMillisec = firstDate.getTimeInMillis()
					- secondDate.getTimeInMillis();

			
			diffInDays = (int) (diffInMillisec / (24 * 60 * 60 * 1000));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return diffInDays;
	}

	
	public float fineCalculation(PaymentTO pb, PolicyTO po) {
		float fine = 0;
		if (po.getPolicyType().equals("VI")) {
			fine = (float) 0.05 * (pb.getPaymentAmount())
					+ pb.getPaymentAmount();
		} else if (po.getPolicyType().equals("TI")) {
			fine = (float) 0.045 * (pb.getPaymentAmount())
					+ pb.getPaymentAmount();
		} else if (po.getPolicyType().equals("LI")) {
			fine = (float) 0.04 * (pb.getPaymentAmount())
					+ pb.getPaymentAmount();
		} else if (po.getPolicyType().equals("HI")) {
			fine = (float) 0.04 * (pb.getPaymentAmount())
					+ pb.getPaymentAmount();
		} else if (po.getPolicyType().equals("CP")) {
			fine = (float) 0.0475 * (pb.getPaymentAmount())
					+ pb.getPaymentAmount();
		} else if (po.getPolicyType().equals("RP")) {
			fine = (float) 0.025 * (pb.getPaymentAmount())
					+ pb.getPaymentAmount();
		}

		return fine;

	}
	public float fineCalculation(String policyType , int termAmount) {
		float fine = 0;
		if (policyType.equals("VI")) {
			fine = (float) 0.05 * (termAmount)
					+ termAmount;
		} else if (policyType.equals("TI")) {
			fine = (float) 0.045 * (termAmount)
					+ termAmount;
		} else if (policyType.equals("LI")) {
			fine = (float) 0.04 * (termAmount)
					+ termAmount;
		} else if (policyType.equals("HI")) {
			fine = (float) 0.04 * (termAmount)
					+ termAmount;
		} else if (policyType.equals("CP")) {
			fine = (float) 0.0475 * (termAmount)
					+ termAmount;
		} else if (policyType.equals("RP")) {
			fine = (float) 0.025 * (termAmount)
					+ termAmount;
		}

		return fine;

	}

	public String billIdGenerator(PaymentTO pb, String ptype) {
		int counter=0;
		try {
			ResultSet rs=ConnectionClass.openConnection().prepareStatement(get_counter+"'"+ptype+"%'").executeQuery();
			if(rs.next())
			  if(rs.last())
				counter=Integer.parseInt(rs.getString("counter"));
			System.out.println(counter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer(pb.getPolicyId());
		sb.append("-");
		sb.append(pb.getStatus());
		sb.append("-");
		++counter;
		sb.append(new DecimalFormat("0000").format(counter).toString());
		System.out.println(sb);
		return sb.toString();

	}

	public  PolicyTO calculateTerms(PolicyTO po) {
		int duration = po.getDurationInYears();
		int terms = po.getTermPerYear();
		int noofterms = duration * terms;
		po.setNoOfTerms(noofterms);
		return po;

	}

	public float balanceAmountCalculator(PaymentTO pb, PolicyTO po) {
		float balance = (float) (po.getTermAmount() * po.getNoOfTerms())
				- (po.getTermAmount());
		return balance;

	}
	public String nextDueDateGenerator(PolicyTO po,Date ed)
	{
		Calendar cal=Calendar.getInstance();
		cal.setTime(ed);
		int months=12/po.getTermPerYear();
		cal.add(Calendar.MONTH, months);
		Date d=cal.getTime();
		return new SimpleDateFormat("dd-MM-yyyy").format(d);
	}
	public int calculateDurationInYears(Date sdate,Date edate)
	{
		int years;
		Calendar c = Calendar.getInstance();
		c.setTime(sdate);
		long d1 = c.getTimeInMillis();
		c.setTime(edate);
		long d2 = c.getTimeInMillis();
		long d3=d2-d1;
		int days = (int) (d3 / (1000*60*60*24));
		years = days / 365;
		return years;
	}
	
}
