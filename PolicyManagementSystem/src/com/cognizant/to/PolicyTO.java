package com.cognizant.to;

public class PolicyTO {
private int policyId; 
private String policyNo;
private String policyName;
private String startDate;
private int durationInYears;
private String Company;
private int intialDeposit;
private String policyType;
private String userType;
private int termPerYear;
private int termAmount;
private float interest;
private double maturityAmount;
private String endDate;
private int noOfTerms;
public int getPolicyId() {
	return policyId;
}
public int getNoOfTerms() {
	return noOfTerms;
}
public void setNoOfTerms(int noOfTerms) {
	this.noOfTerms = noOfTerms;
}
public void setPolicyId(int policyId) {
	this.policyId = policyId;
}
public String getPolicyNo() {
	return policyNo;
}
public void setPolicyNo(String policyNo) {
	this.policyNo = policyNo;
}
public String getPolicyName() {
	return policyName;
}
public void setPolicyName(String policyName) {
	this.policyName = policyName;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public int getDurationInYears() {
	return durationInYears;
}
public void setDurationInYears(int durationInYears) {
	this.durationInYears = durationInYears;
}
public String getCompany() {
	return Company;
}
public void setCompany(String company) {
	Company = company;
}
public int getIntialDeposit() {
	return intialDeposit;
}
public void setIntialDeposit(int intialDeposit) {
	this.intialDeposit = intialDeposit;
}
public String getPolicyType() {
	return policyType;
}
public void setPolicyType(String policyType) {
	this.policyType = policyType;
}
public String getUserType() {
	return userType;
}
public void setUserType(String userType) {
	this.userType = userType;
}
public int getTermPerYear() {
	return termPerYear;
}
public void setTermPerYear(int termPerYear) {
	this.termPerYear = termPerYear;
}
public int getTermAmount() {
	return termAmount;
}
public void setTermAmount(int termAmount) {
	this.termAmount = termAmount;
}
public float getInterest() {
	return interest;
}
public void setInterest(float f) {
	this.interest = f;
}
public double getMaturityAmount() {
	return maturityAmount;
}
public void setMaturityAmount(double maturityAmount) {
	this.maturityAmount = maturityAmount;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}

}
