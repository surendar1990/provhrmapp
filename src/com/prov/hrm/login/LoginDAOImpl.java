package com.prov.hrm.login;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.utility.Mail;
import com.prov.hrm.utility.SessionFactoryUtil;

public class LoginDAOImpl implements LoginDAO {
	@SuppressWarnings("unchecked")
	@Override
	public Login Authentication(Login login) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Login check =(Login)session.createCriteria(Login.class)
					.add(Restrictions.eq("loginName", login.getLoginName()))
					.add(Restrictions.eq("deleteFlag",false))
						.add(Restrictions.eq("loginPassword", login.getLoginPassword())).uniqueResult();
			
			if((check!=null)&&(check.getLoginPassword().equals(login.getLoginPassword()))&&(check.getLoginName().equals(login.getLoginName())))
			{
				session.getTransaction().commit();
				
				SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
				if(check.getEmployee()!=null)
				{
					String DOB=check.getEmployee().getDateOfJoining();
					Date Dateofjoining= sdinput.parse(DOB);
					check.getEmployee().setDateOfJoining(sdfOut.format(Dateofjoining));
					return check;
				}
				else
				{
					return check;
				}
				
			}
			else
			{
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Login> getAllLogin() {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Login.class);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	@Override
	public int addLogin(Login login) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			status = (int) session.save(login);
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	@Override
	public Login getLoginById(int login_id) {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			return (Login) session.get(Login.class, login_id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	@Override
	public int forgetPassword(String email) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			
			session.beginTransaction();
			Login login = (Login) session.createCriteria(Login.class)
					.add(Restrictions.eq("loginName", email))
					.add(Restrictions.eq("deleteFlag", false)).uniqueResult();
			session.flush();
			Employee employee = (Employee) session.get(Employee.class,
					login.getEmployee().getEmployeeId());
			Mail.sendMailForForgetPassword(login.getLoginName(),
					employee.getFirstName(), login.getLoginPassword());
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
	
		}
		finally{
			session.getTransaction().commit();		
		}

	}
	
	public int changePassword(int loginId,String userName,String currentPassword,String newPassword,int updateBy)
	{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			
			session.beginTransaction();
			Login login = (Login) session.createCriteria(Login.class)
					.add(Restrictions.eq("loginId", loginId))
					.add(Restrictions.eq("loginName", userName))
					.add(Restrictions.eq("loginPassword", currentPassword)).uniqueResult();
			if(login!=null&&login.getLoginPassword().equals(currentPassword)&&login.getLoginName().equals(userName))
			{
				session.flush();
				login.setLoginPassword(newPassword);
				login.setUpdateBy(updateBy);
				java.sql.Timestamp date = new Timestamp(
						new java.util.Date().getTime());
				login.setUpdateDate(date.toString());
				session.update(login);
				status=1;
				return status;
			}
			else
			{
				return status;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
	
		}
		finally{
			session.getTransaction().commit();		
		}
	}

}
