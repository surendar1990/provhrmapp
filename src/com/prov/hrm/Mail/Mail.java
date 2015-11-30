package com.prov.hrm.Mail;

public class Mail {
private Integer mailId;
private String emailSubject;
private String emailPurpose;
private String emailBody;

public Mail()
{
	
}
public Mail(Integer mailId, String emailSubject, String emailPurpose,
		String emailBody) {
	super();
	this.mailId = mailId;
	this.emailSubject = emailSubject;
	this.emailPurpose = emailPurpose;
	this.emailBody = emailBody;
}

public Integer getMailId() {
	return mailId;
}
public void setMailId(Integer mailId) {
	this.mailId = mailId;
}
public String getEmailSubject() {
	return emailSubject;
}
public void setEmailSubject(String emailSubject) {
	this.emailSubject = emailSubject;
}
public String getEmailPurpose() {
	return emailPurpose;
}
public void setEmailPurpose(String emailPurpose) {
	this.emailPurpose = emailPurpose;
}
public String getEmailBody() {
	return emailBody;
}
public void setEmailBody(String emailBody) {
	this.emailBody = emailBody;
}
@Override
public String toString() {
	return "{mailId=" + mailId + ", emailSubject=" + emailSubject
			+ ", emailPurpose=" + emailPurpose + ", emailBody=" + emailBody
			+ "}";
}

}
