package com.prov.hrm.login;



import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.utility.Mail;
import com.prov.hrm.utility.SessionFactoryUtil;

public class LoginDAOImpl implements LoginDAO
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Login> Authentication(Login login) {
	
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		try{
		session.beginTransaction();
		String password=login.getEncryptPassword();
		String username=login.getEncryptName();
		System.out.println(username);
		Query query=session.createSQLQuery("select login_id,organization_id,employee_id,login_name from tbllogin where encrypt_name='"+username+"' and encrypt_password='"+password+"'");
		return query.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
		session.getTransaction().commit();
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
			
		}
	}

	@Override
	public int addLogin(Login login) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status=0;
		try {
			session.beginTransaction();
			status= (int) session.save(login);
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
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
			
		}
	}
	
	
	//Change Password
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
	
	//Forget password
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


    }
 


