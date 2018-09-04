package com.cognizant.to;

public class PaymentTO {
	private String billid;
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	public float getBalanceamount() {
		return balanceamount;
	}
	public void setBalanceamount(float balanceamount) {
		this.balanceamount = balanceamount;
	}
	private float balanceamount;
private String policyId;
private String billDate;
private int paymentAmount;
private String status;
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
private float fine;
private String dueDate;
public String getPolicyId() {
	return policyId;
}
public void setPolicyId(String policyId) {
	this.policyId = policyId;
}
public String getBillDate() {
	return billDate;
}
public void setBillDate(String billDate) {
	this.billDate = billDate;
}
public int getPaymentAmount() {
	return paymentAmount;
}
public void setPaymentAmount(int paymentAmount) {
	this.paymentAmount = paymentAmount;
}
public float getFine() {
	return fine;
}
public void setFine(float f) {
	this.fine = f;
}
public String getDueDate() {
	return dueDate;
}
public void setDueDate(String dueDate) {
	this.dueDate = dueDate;
}

}
