package com.prov.hrm.utility;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.prov.hrm.Mail.LeaveRequestSend;




public class LeaveRequestMail {

	public static boolean  send(String toEmail, String ccEmails,LeaveRequestSend getformat,
			Map<String,String> data,String username ){
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("hariharan.p@provintl.com",
						"hariharan@p");
			}
		});

		try {
			String templateString=getformat.getMailBody();
			String templateString1=getformat.getMailSubject();
			StrSubstitutor sub=new StrSubstitutor(data);
			String text=sub.replace(templateString);
			System.out.println(text);
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress("hariharan.p@provintl.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					toEmail));
			if (ccEmails != null) {
				message.addRecipients(RecipientType.CC, InternetAddress.parse(ccEmails));
			}
			message.setSubject(templateString1+" "+username);
			message.setContent(text, "text/html");
			Transport.send(message);
			System.out.println("message sent successfully");
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return false;
		}
	}

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

	public List<LeaveRequestSend> getLeaveRequestFormat() {

		org.hibernate.Session session = SessionFactoryUtil.getSessionFactory()
				.openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session
					.createCriteria(LeaveRequestSend.class);
			criteria.add(Restrictions.eq("reqId", 1))
					.uniqueResult();			 
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();

		}

	}public List<LeaveRequestSend> getLeaveUpdateFormat() {

		org.hibernate.Session session = SessionFactoryUtil.getSessionFactory()
				.openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session
					.createCriteria(LeaveRequestSend.class);
			criteria.add(Restrictions.eq("reqId", 2))
					.uniqueResult();			 
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();

		}

	}
	public List<LeaveRequestSend> getLeaveapproveFormat() {

		org.hibernate.Session session = SessionFactoryUtil.getSessionFactory()
				.openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session
					.createCriteria(LeaveRequestSend.class);
			criteria.add(Restrictions.eq("reqId", 4))
					.uniqueResult();			 
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();

		}

	}
	
	
	public List<LeaveRequestSend> getLeaveDeleteFormat() {

		org.hibernate.Session session = SessionFactoryUtil.getSessionFactory()
				.openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session
					.createCriteria(LeaveRequestSend.class);
			criteria.add(Restrictions.eq("reqId", 3))
					.uniqueResult();			 
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();

		}

	}



	/*public static void main(String[] args) {

		LeaveRequestMail sm = new LeaveRequestMail();
		ArrayList<String> emaillist = new ArrayList<String>();
		LeaveRequestSend sendBody = sm.getEmailFormat("create user").get(
				0);
		emaillist.add("hariharan.p@provintl.com");
		emaillist.add("hariharan.p@provintl.com");
		Map<String,String> ls=new HashMap<String,String>();
		ls.put("name", "hhp");
		ls.put("fromUserName", "hhp");
		ls.put("reason", "Marrige");
		ls.put("fromDate", "25-11-2015");
		ls.put("toDate", "25-11-2016");
		System.out.println(ls);
		if (sm.send("hariharan.p@provintl.com", null, sendBody,ls)) {
			System.out.println("Sending Successfully");
		} else {
			System.out.println("Try again");
		}
	}*/
}
