package com.cognizant.bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cognizant.dao.ConnectionClass;
import com.cognizant.dao.QueryInterface;
import com.cognizant.servlets.GetPayment;
import com.cognizant.to.PaymentTO;
import com.cognizant.to.PolicyTO;
import com.cognizant.to.RegisterTO;
import com.cognizant.to.UserTO;

public class DataBaseClass implements QueryInterface {
	private final Logger LOGGER = Logger.getLogger(DataBaseClass.class);
	private PreparedStatement query;
	private ResultSet result;

	public int registerUser(RegisterTO r, String utype) {
		int success = 0;
		LOGGER.info("registerUser()");
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					insert_user_details);
			query.setString(1, r.getFirstName());
			query.setString(2, r.getLastName());
			query.setString(3, r.getDateOfBirth());
			query.setString(4, r.getAddress());
			query.setString(5, r.getContactNumber());
			query.setString(6, r.getEmail());
			query.setString(7, r.getQualification());
			query.setString(8, r.getGender());
			query.setInt(9, r.getSalary());
			query.setString(10, r.getPanNumber());
			query.setString(11, r.getEmployerType());
			query.setString(12, r.getEmployerName());
			query.setString(13, r.getSecurityQuestion());
			query.setString(14, r.getSecurityAnswer());
			query.setString(15, utype);
			success = query.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Exception on registerUser() : " + e.getLocalizedMessage());
			success = 0;
		}
		LOGGER.info("Registered to Database Successfully");
		return success;

	}

	public int putUserData(UserTO u) {
		int success = 0;
		LOGGER.info("putUserData()");
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					select_maxOfUsers);
			result = query.executeQuery();
			result.next();
			int reg_id = result.getInt(1);
			query = ConnectionClass.openConnection().prepareStatement(
					insert_user_creds);
			query.setInt(1, reg_id);
			query.setString(2, u.getUserName());
			query.setString(3, u.getPassword());
			success = query.executeUpdate();
			ConnectionClass.closeConnection();
		} catch (SQLException e) {
			LOGGER.error("Exception on putUserData() : " + e.getLocalizedMessage());
			success = 0;
		}
		LOGGER.info("User " + u.getUserName() + "added successfully");
		return success;
	}

	public int putPolicyData(PolicyTO u) {
		int success = 0;
		LOGGER.info("putPolicyData()");
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					insert_policy_details);
			query.setString(1, u.getPolicyNo());
			query.setString(2, u.getPolicyName());
			query.setString(3, u.getStartDate());
			query.setInt(4, u.getDurationInYears());
			query.setString(5, u.getCompany());
			query.setInt(6, u.getIntialDeposit());
			query.setString(7, u.getPolicyType());
			query.setString(8, u.getUserType());
			query.setInt(9, u.getTermPerYear());
			query.setInt(10, u.getTermAmount());
			query.setFloat(11, u.getInterest());
			query.setDouble(12, u.getMaturityAmount());
			query.setString(13, u.getEndDate());
			query.setInt(14, u.getDurationInYears() * u.getTermPerYear());
			success = query.executeUpdate();
			ConnectionClass.closeConnection();
		} catch (SQLException e) {
			success = 0;
			LOGGER.error("Exception on putPolicyData() : " + e.getLocalizedMessage());
		}
		LOGGER.info("Policy " + u.getPolicyName() + "added successfully");
		return success;
	}

	public int checkUserData(UserTO userTO) {
		LOGGER.info("checkUserData()");
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					get_user_creds);
			query.setString(1, userTO.getPassword());
			result = query.executeQuery();
			while (result.next()) {
/*				System.out.println(result.getString(1));*/
				if (result.getString(1).equals(userTO.getUserName())) {
					ConnectionClass.closeConnection();
					LOGGER.info("User : " + userTO.getUserName() + " is valid");
					return 2;
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Exception on checkUserData() : " + e.getLocalizedMessage());
			return 0;
		}
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					get_admin_creds);
			query.setString(1, userTO.getPassword());
			result = query.executeQuery();

			while (result.next()) {
/*				System.out.println(result.getString(1));*/
				if (result.getString(1).equals(userTO.getUserName())) {
					ConnectionClass.closeConnection();
					LOGGER.info("Admin : " + userTO.getUserName() + " is valid");
					return 1;
				}
			}
		} catch (SQLException e) {
			LOGGER.error("Exception on checkUserData() : " + e.getLocalizedMessage());
			return 0;
		}
		LOGGER.info("User/Admin : " + userTO.getUserName() + " is not valid");
		return 0;

	}

	public int checkPolicy(PolicyTO pb) {
		LOGGER.info("checkPolicy()");
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					get_policy_name);
			result = query.executeQuery();
			while (result.next()) {
				if (result.getString(1).equals(pb.getPolicyName())) {
					LOGGER.info("Policy " + pb.getPolicyName()
							+ " already exists");
					return 1;
				}
			}
			ConnectionClass.closeConnection();
		} catch (SQLException e) {
			LOGGER.error("Exception on checkPolicy: " + e.getLocalizedMessage());
			ConnectionClass.closeConnection();
			return 0;
		}
		LOGGER.info("Policy " + pb.getPolicyName() + " doesn't exist");
		return 0;
	}

	public PolicyTO getPolicyId(PolicyTO pb) {
		LOGGER.info("getPolicyId()");
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					get_policy_details);
			query.setString(1, pb.getPolicyName());
			ResultSet rs = query.executeQuery();
			while (rs.next()) {
				pb.setPolicyId((rs.getInt(1)));
				pb.setStartDate(rs.getString(2));
				pb.setIntialDeposit(rs.getInt(3));
				pb.setTermPerYear((rs.getInt(4)));
				pb.setInterest(rs.getInt(5));
			}
		} catch (SQLException e) {
			LOGGER.error("Exception on getPolicyId() : " + e.getLocalizedMessage());
		}
		LOGGER.info("Sending PolicyBean - getPolicyId()");
		return pb;
	}

	public int editPolicy(PolicyTO pb) {
		LOGGER.info("editPolicy()");
		int s = 0;
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					"update policy_details set policy_type='"
							+ pb.getPolicyType() + "' where policy_id='"
							+ pb.getPolicyId() + "'");
			s = query.executeUpdate();
			query = ConnectionClass.openConnection().prepareStatement(
					"update policy_details set duration='"
							+ pb.getDurationInYears() + "' where policy_id='"
							+ pb.getPolicyId() + "'");
			s = query.executeUpdate();
			query = ConnectionClass.openConnection().prepareStatement(
					"update policy_details set term_amount='"
							+ pb.getTermAmount() + "' where policy_id='"
							+ pb.getPolicyId() + "'");
			s = query.executeUpdate();
			query = ConnectionClass.openConnection().prepareStatement(
					"update policy_details set maturity_amount='"
							+ pb.getMaturityAmount() + "' where policy_id='"
							+ pb.getPolicyId() + "'");
			s = query.executeUpdate();
			query = ConnectionClass.openConnection().prepareStatement(
					"update policy_details set policy_no='" + pb.getPolicyNo()
							+ "' where policy_id='" + pb.getPolicyId() + "'");
			s = query.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Exception on editPolicy() : " + e.getLocalizedMessage());
			s = 0;
		}
		LOGGER.info("Policy Edit is Success - editPolicy()");
		return s;
	}

	public List<PolicyTO> searchPolicy(PolicyTO pb) {
		LOGGER.info("searchPolicy()");
		List<PolicyTO> lp = new ArrayList<PolicyTO>();
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					get_complete_policy_details_bycompany);
			query.setString(1, pb.getCompany());
			query.setInt(2, pb.getDurationInYears());
			query.setString(3, pb.getPolicyType());
			result = query.executeQuery();
			while (result.next()) {
				System.out.println("got it ");
				pb = new PolicyTO();
				pb.setPolicyId((result.getInt(1)));
				pb.setPolicyNo(result.getString(2));
				pb.setPolicyName(result.getString(3));
				pb.setStartDate(result.getString(4));
				pb.setDurationInYears(result.getInt(5));
				pb.setCompany(result.getString(6));
				pb.setIntialDeposit(result.getInt(7));
				pb.setPolicyType(result.getString(8));
				pb.setUserType(result.getString(9));
				pb.setTermPerYear((result.getInt(10)));
				pb.setTermAmount(result.getInt(11));
				pb.setInterest(result.getFloat(12));
				pb.setMaturityAmount(result.getDouble(13));
				pb.setEndDate(result.getString(14));
				lp.add(pb);
				System.out.println(lp);
			}
		} catch (SQLException e) {
			LOGGER.error("Exception on searchPolicy() : " + e.getLocalizedMessage());
		}
		LOGGER.info("Sending List");
		return lp;
	}

	public int payment(PaymentTO pb) {
		LOGGER.info("payment()");
		int success = 0;
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					insert_payment_details);
			query.setString(1, pb.getPolicyId());
			query.setString(2, pb.getBillDate());
			query.setInt(3, pb.getPaymentAmount());
			/* ps.setFloat(4, pb.getFine()); */
			query.setString(4, pb.getDueDate());
			query.setString(5, pb.getBillid());
			query.setFloat(6, pb.getBalanceamount());
			success = query.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("Exception on payment() : " + e.getLocalizedMessage());
			success = 0;
		}
		LOGGER.info("Payment added to database");
		return success;
	}

	public PolicyTO getPolicy(PaymentTO pb, PolicyTO po) {
		LOGGER.info("getPolicy()");
		try {
			PaymentModifier payModify=new PaymentModifier();
			query = ConnectionClass.openConnection().prepareStatement(
					get_user_policy_bypolicyno);
			query.setString(1, pb.getPolicyId());
			result = query.executeQuery();
			if (result.next()) {
				po.setStartDate(result.getString(2));
				po.setTermAmount(result.getInt(6));
				po.setTermPerYear((result.getInt(10)));
				po.setEndDate(result.getString(4));
				po.setPolicyType(pb.getPolicyId().substring(0,2));
				po.setNoOfTerms(result.getInt(7));
				Date sdate=null;
				Date edate=null;
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				sdate=sdf.parse(po.getStartDate());
				edate=sdf.parse(po.getEndDate());
				po.setDurationInYears(payModify.calculateDurationInYears(sdate, edate));
			}
		} catch (SQLException e) {
			LOGGER.error("Got into an Exception" + e.getLocalizedMessage());
		} catch (ParseException e) {
			LOGGER.error("Got into an Exception" + e.getLocalizedMessage());
		}
		LOGGER.info("Sending policy");
		return po;
	}

	public int getRegid(UserTO ub) {
		LOGGER.info("getRegId()");
		int regid = 0;
		try {
			query = ConnectionClass.openConnection()
					.prepareStatement(get_regid);
			query.setString(1, ub.getUserName());
			result = query.executeQuery();
			result.next();
			regid = result.getInt(1);
		} catch (SQLException e) {
			LOGGER.error("Exception on getRegId() : " + e.getLocalizedMessage());
		}
		LOGGER.info("Register id gathered successfully");
		return regid;
	}

	public PolicyTO getPolicy(String policyNo) {
		LOGGER.info("getPolicy()");
		PolicyTO pb = new PolicyTO();

		try {
			query = ConnectionClass.openConnection().prepareStatement(
					get_complete_policy_details_byno);
			query.setString(1, policyNo);
			result = query.executeQuery();
			while (result.next()) {
				System.out.println("got it ");
				pb = new PolicyTO();
				pb.setPolicyId((result.getInt(1)));
				pb.setPolicyNo(result.getString(2));
				pb.setPolicyName(result.getString(3));
				pb.setStartDate(result.getString(4));
				pb.setDurationInYears(result.getInt(5));
				pb.setCompany(result.getString(6));
				pb.setIntialDeposit(result.getInt(7));
				pb.setPolicyType(result.getString(8));
				pb.setUserType(result.getString(9));
				pb.setTermPerYear((result.getInt(10)));
				pb.setTermAmount(result.getInt(11));
				pb.setInterest(result.getFloat(12));
				pb.setMaturityAmount(result.getDouble(13));
				pb.setEndDate(result.getString(14));
			}
		} catch (SQLException e) {
			LOGGER.error("Exception on getPolicy() : " + e.getLocalizedMessage());
		}
		LOGGER.info("Sending Policy");
		return pb;
	}

	public int getUserId(String name) {
		LOGGER.info("getUserId()");
		int regid = 0;
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					get_user_id);
			query.setString(1, name);
			result = query.executeQuery();
			result.next();
			regid = result.getInt(1);
			System.out.println(regid);
		} catch (SQLException e) {
			LOGGER.error("Exception on getUserId() : " + e.getLocalizedMessage());
		}
		return regid;
	}

	public int userPolicy(PolicyTO policyBean, String user_name, int due_amount, int regid,
			String duedate) {
		LOGGER.info("userPolicy()");
		int success = 0;
		int count = 1;
		int regestid;
		try {
			query = ConnectionClass.openConnection().prepareStatement(
					get_regid_user);
			query.setString(1, user_name);
			result = query.executeQuery();
			result.next();
			regestid = result.getInt(1);
			query = ConnectionClass.openConnection().prepareStatement(
					count_user_name);
			query.setString(1, user_name);
			result = query.executeQuery();
			if (result.next())
				count = result.getInt(1);
			if (count < 2) {
				query = ConnectionClass.openConnection().prepareStatement(
						insert_user_policy);
				query.setInt(1, regestid);
				query.setString(2, user_name);
				query.setString(3, policyBean.getPolicyNo());
				query.setString(4, policyBean.getStartDate());
				query.setDouble(5, policyBean.getMaturityAmount());
				query.setString(6, policyBean.getEndDate());
				query.setFloat(7, policyBean.getIntialDeposit());
				query.setFloat(8, policyBean.getTermAmount());
				query.setInt(9, policyBean.getDurationInYears() * policyBean.getTermPerYear());
				query.setInt(10, due_amount);
				query.setString(11, duedate);
				query.setInt(12, policyBean.getTermPerYear());
				success = query.executeUpdate();
			}
		} catch (SQLException e) {
			LOGGER.error("Exception on userPolicy()" + e.getLocalizedMessage());
			success = 0;
		}
		return success;
	}

	public int checkPolicyRegister(String policynum) {
		LOGGER.info("checkPolicyRegister()");
		try {
			query = ConnectionClass
					.openConnection()
					.prepareStatement(
							"select policy_no from user_policy_details where policy_no=?");
			query.setString(1, policynum);
			result = query.executeQuery();
			if (result.next()) {
				return 0;
			} else
				return 1;
		} catch (SQLException e) {
			LOGGER.error("Exception on checkPolicyRegister() : "+e.getLocalizedMessage()); 
			return 0;
		}
	}

}