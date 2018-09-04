package com.cognizant.bo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;

import com.cognizant.dao.ConnectionClass;
import com.cognizant.to.PolicyTO;
import com.cognizant.to.RegisterTO;
import com.cognizant.to.UserTO;

public class SettingsChanges {
	public  int changePassword(UserTO ub,String newp)
	{
		int status=new DataBaseClass().checkUserData(ub);
		int success=0;
		if(status==1)
		{
			
			PreparedStatement p=null;
			try {
				p = ConnectionClass.openConnection().prepareStatement(
						"update admin_credentials set password=md5('"
								+ newp + "') where user_name='"
								+ ub.getUserName() + "'");
				success=p.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}
			
		}
		else
			if(status==2)
			{
				PreparedStatement p=null;
				try {
					p = ConnectionClass.openConnection().prepareStatement(
							"update user_credentials set password=md5('"
									+ newp + "') where user_name='"
									+ ub.getUserName() + "'");
					success=p.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					success=0;
				}
			}
		return success;
	}
	public  int changeMobile(UserTO ub,String newph)
	{
		int success=0;
		int status=new DataBaseClass().checkUserData(ub);
		if(status==2)
		{
			int regid=new DataBaseClass().getRegid(ub);
			PreparedStatement p=null;
			try {
				p = ConnectionClass.openConnection().prepareStatement(
						"update user_details set contact_number='"
								+ newph + "' where register_id='"
								+ regid + "'");
				success=p.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}
			
		}
		return success;
	}
	public  int changeMail(UserTO ub,String newmail)
	{
		int success=0;
		int status=new DataBaseClass().checkUserData(ub);
		if(status==2)
		{
			int regid=new DataBaseClass().getRegid(ub);
			PreparedStatement p=null;
			try {
				p = ConnectionClass.openConnection().prepareStatement(
						"update user_details set email='"
								+ newmail + "' where register_id='"
								+ regid + "'");
				success=p.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				success=0;
			}
			
		}
		return success;
	}


public  String currendate(PolicyTO pb)
{
	String s=null;
	Date d=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
	s=sdf.format(d);
	return s;
	
}
public  String enddate(PolicyTO pb)
{
	
	int duration=pb.getDurationInYears();
	String d=pb.getStartDate();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	sdf.setLenient(false);
	Date d1=null;
	Date d2=null;
	String s2=null;
	try {
		d1=sdf.parse(d);
		Calendar c=Calendar.getInstance();
		c.setTime(d1);
		c.add(Calendar.YEAR,duration);
		d2=c.getTime();
		s2=sdf.format(d2);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
return s2;
	
}
public  int dueamont(PolicyTO pb)
{
	int due=pb.getIntialDeposit()+(pb.getNoOfTerms()*pb.getTermAmount());
	return due;
	
}
public  String nextduedate(PolicyTO pb)
{
	
	int terms=pb.getTermPerYear();
	String d=pb.getStartDate();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	sdf.setLenient(false);
	Date d1=null;
	Date d2=null;
	String s2=null;
	try {
		d1=sdf.parse(d);
		Calendar c=Calendar.getInstance();
		c.setTime(d1);
		c.add(Calendar.MONTH,12/terms);
		d2=c.getTime();
		s2=sdf.format(d2);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return s2;
	
}
public  int calTerms(PolicyTO pb) {
	int duration = pb.getDurationInYears();
	int terms = pb.getTermPerYear();
	int noofterms = duration * terms;
	System.out.println(duration);
	System.out.println(terms);
	System.out.println(noofterms);
	return noofterms;

}
}

