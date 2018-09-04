package com.cognizant.dao;

public interface QueryInterface {
	public String insert_user_details = "insert into user_details(first_name,last_name,date,address,contact_number,email,qualification,gender,salary,pan_number,employer_type,employer_name,security_question,security_answer,user_type)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public String select_maxOfUsers = "select max(register_id) from user_details";
	public String validate_query = "select contact_number,email,pan_number from user_details";
	public String insert_user_creds = "insert into user_credentials(register_id,user_name,password)values(?,?,md5(?))";
	public String insert_policy_details = "insert into policy_details(policy_no,policy_name,start_date,duration,company,initial_deposit,policy_type,user_type,terms_per_year,term_amount,interest,maturity_amount,end_date,no_of_terms)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public String get_user_creds = "select user_name from user_credentials where password=md5(?)";
	public String get_admin_creds = "select user_name from admin_credentials where password=md5(?)";
	public String get_policy_name = "select policy_name from policy_details";
	public String get_policy_details = "select policy_id,start_date,initial_deposit,terms_per_year,interest from policy_details where policy_name=?";
	public String get_complete_policy_details_bycompany = "select policy_id,policy_no,policy_name,start_date,duration,company,initial_deposit,policy_type,user_type,terms_per_year,term_amount,interest,maturity_amount,end_date from policy_details where company=? and duration=? and policy_type=?";
	public String get_complete_policy_details_byno = "select policy_id,policy_no,policy_name,start_date,duration,company,initial_deposit,policy_type,user_type,terms_per_year,term_amount,interest,maturity_amount,end_date from policy_details where policy_no=? ";
	public String insert_payment_details = "insert into policy_payment(policy_id,bill_date,payment_amount,due_date,bill_id,balance_amount)values(?,?,?,?,?,?)";
	public String get_policy_no="select policy_no from user_policy_details where user_name=?";
	public String get_payment_details_byno="select * from user_policy_details where policy_no=?";
	public String bill_date = "select max(bill_date) from policy_payment";
	public String check_for_policy = "select * from policy_details where policy_name=?";
	public String get_regid = "select ud.register_id from user_details as ud join user_credentials as uc on uc.register_id=ud.register_id where uc.user_name=?";
	public String get_counter = "select substring_index(bill_id,'-',-1) as counter from policy_payment where policy_id like ";
	public String get_policy_duration="select distinct(duration) from policy_details where policy_type=? and user_type=?";
	public String insert_user_policy_details="insert into user_policy_details(user_id,policy_no,start_date,maturity_amount,end_date,inital_deposit,term_amount,no_of_terms,due_amount,terms_per_year) values(?,?,?,?,?,?,?,?,?,?)";
    public String insert_user_policy="insert into user_policy_details(user_policy_id,user_name,policy_no,start_date,maturity_amount,end_date,initial_deposit,term_amount,no_of_terms,due_amount,next_due_date,terms_per_year)values(?,?,?,?,?,?,?,?,?,?,?,?)";
    public String get_user_id="select user_id from user_credentials where user_name=?";
    public String count_user_name="select count(policy_no) from user_policy_details where user_name=? group by user_name";
    public String get_regid_user="select register_id from user_credentials where user_name=?" ;
    public String get_user_policy_bypolicyno="select policy_no,start_date,maturity_amount,end_date,initial_deposit,term_amount,no_of_terms,due_amount,next_due_date,terms_per_year from user_policy_details where policy_no=?";
    
   /* public String get_regid_user="select policy_name from policy_details where user_type=? and policy_no not in(select policy_no from user_policy_details) " ;*/
    
    
}
