package com.prov.hrm.Mail;

import com.prov.hrm.employee.Employee;

public class LeaveRequestSend 
{
	private int reqId;
	private Employee toMail;
	private String ccMail;
	private String mailSubject;
	private String mailBody;
	public LeaveRequestSend() 
	{
		
	}
	public LeaveRequestSend(int reqId, Employee toMail, String ccMail,
			String mailSubject, String mailBody) {
		super();
		this.reqId = reqId;
		this.toMail = toMail;
		this.ccMail = ccMail;
		this.mailSubject = mailSubject;
		this.mailBody = mailBody;
	}
	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
	}
	public Employee getToMail() {
		return toMail;
	}
	public void setToMail(Employee toMail) {
		this.toMail = toMail;
	}
	public String getCcMail() {
		return ccMail;
	}
	public void setCcMail(String ccMail) {
		this.ccMail = ccMail;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	
	

}
