package com.prov.hrm.utility;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	
	//send mail for creating user
	public static boolean sendMailForCreateUser(String toEmail,String name,
			 String username, String password) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sreemat.ap@provintl.com",
						"angularJS123");
			}
		});
		String body = "<p> Hi "+name+",</p><p>Welcome onboard</p><p>Your username is "
				+ username + "</p><p>Your password is " + password + "</p>"+"<a href=\"http://220.225.222.172/projects/ProvAdminConsole/\">Click Here To Login</a>";

		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sreemat.ap@provintl.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					toEmail));
			message.setSubject("Welcome to ProV International");
			message.setContent(body, "text/html");
			
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return false;
		}
	}

	//Send mail for change password
	public static boolean sendMailForChangePassword(String toEmail,String toUserName) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sreemat.ap@provintl.com",
						"angularJS123");
			}
		});
		String body = "<p>Dear "+toUserName+",</p><p>Your password is changed successfully.</p>";

		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("sreemat.ap@provintl.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					toEmail));
			message.setSubject("Password Changed");
			message.setContent(body, "text/html");
	
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return false;
		}
	}
	
	//Send mail for forget password
		public static boolean sendMailForForgetPassword(String toEmail,String toUserName,String password) {

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("sreemat.ap@provintl.com",
							"angularJS123");
				}
			});
			String body = "<p>Dear "+toUserName+",</p><p>Your password is "+password+".</p>";

			
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress("sreemat.ap@provintl.com"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(
						toEmail));
				message.setSubject("Forget password");
				message.setContent(body, "text/html");
		
				Transport.send(message);
				return true;
			} catch (MessagingException e) {
				System.out.println(e.toString());
				e.printStackTrace();
				return false;
			}
		}
	
	//Send mail for leave request
		public static boolean sendMailForLeave(String fromMail,String toEmail,String toUserName,String fromUserName,
				ArrayList<String> ccEmails, String fromDate, String toDate,String reason) {

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("sreemat.ap@provintl.com",
							"angularJS123");
				}
			});
			String body = "<p>Dear "+toUserName+",</p><p>"+fromUserName+"("+fromMail+")"
					+ "has requested leave from date "+fromDate+"to "+toDate+"</p><p>Reason: "+reason+"</p>";

			
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress("sreemat.ap@provintl.com"));
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(
						toEmail));
				if (ccEmails != null) {
					message.addRecipients(RecipientType.CC, getEmailsList(ccEmails));
				}
				message.setSubject("Leave Request");
				message.setContent(body, "text/html");
		
				Transport.send(message);
				return true;
			} catch (MessagingException e) {
				System.out.println(e.toString());
				e.printStackTrace();
				return false;
			}
		}
	
	//Convert string emailid to address emailid
	private static Address[] getEmailsList(ArrayList<String> emails) {

		Address[] emaiAddresses = new Address[emails.size()];

		for (int i = 0; i < emails.size(); i++) {
			try {
				emaiAddresses[i] = new InternetAddress(emails.get(i));
			} catch (AddressException e) {

				e.printStackTrace();
			}
		}
		return emaiAddresses;
	}

	
	
	
	/*
	 * //Getting mail format from database public List<com.prov.hrm.mail.Mail>
	 * getEmailFormat(String purpose) {
	 * 
	 * org.hibernate.Session session = SessionFactoryUtil.getSessionFactory()
	 * .openSession(); try { session.beginTransaction(); Criteria criteria =
	 * session .createCriteria(com.prov.hrm.mail.Mail.class);
	 * criteria.add(Restrictions.eq("emailPurpose", purpose)) .uniqueResult();
	 * return criteria.list(); } catch (Exception e) { e.printStackTrace();
	 * return null; } finally { session.getTransaction().commit();
	 * 
	 * }
	 * 
	 * }
	 */
	/*
	 * public static void main(String[] args) {
	 * 
	 * Mail sm = new Mail(); ArrayList<String> emaillist = new
	 * ArrayList<String>(); emaillist.add("surendar.subramani@provintl.com");
	 * emaillist.add("hariharan.p@provintl.com");
	 * emaillist.add("pavithran.babu@provintl.com"); if
	 * (sm.send("sreemat.ap@provintl.com", null,
	 * sm.getEmailFormat("change password").get(0))) {
	 * System.out.println("Sending Successfully"); } else {
	 * System.out.println("Try again"); } }
	 */
}
