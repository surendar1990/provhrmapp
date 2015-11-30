package com.prov.hrm.employee;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.login.Login;
import com.prov.hrm.utility.Mail;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeDAOImpl implements EmployeeDAO
{

	//Get all the records from the tblemployee
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployee(int organizationId) throws HibernateException,ConstraintViolationException
	{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
		}
	}
	
	//Get a record by id using the employeeId column from tblemployee
	@Override
	public Employee getEmployeeById(int employeeId) throws HibernateException,ConstraintViolationException
	{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Employee employee =(Employee) session.get(Employee.class, employeeId); 
			if (employee != null) {
				if (!employee.getDeleteFlag()) {
					return employee;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	
	//Add the new employee in tblemployee
	@Override
	public int addEmployee(Employee employee) throws HibernateException,ConstraintViolationException
	{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status=0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			employee.setInsertDate(date.toString());
			employee.setUpdateDate(date.toString());
			Login login=new Login();
			login.setEmployee(employee);
			login.setLoginName(employee.getEmailId());
			login.setOrganizationId(employee.getOrganizationId());
			login.setLoginPassword("Prov@123");
			login.setInsertDate(date.toString());
			login.setUpdateDate(date.toString());
			Set<Login> set=new HashSet<Login>();
			set.add(login);
			employee.setLogin(set);
			int key=(int)session.save(employee);
			status=1;
			if(status==1)
			{
				boolean mailStatus=Mail.sendMailForCreateUser(employee.getEmailId(),employee.getFirstName(), employee.getEmailId(), "Prov@123");
				status=3;
				return mailStatus?key:1;
			}


			return status;

		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}
	
	//update the employee details in table tblemployee using employeeId

	@Override
	public int updateEmployee(Employee employee) throws HibernateException,ConstraintViolationException
	{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			employee.setUpdateDate(date.toString());
			Employee emp = getEmployeeById(employee.getEmployeeId());
			if (emp != null) {
				employee.setInsertBy(emp.getInsertBy());
				employee.setInsertDate(emp.getInsertDate());
				session.update(employee);
			} else {
				return status;
			}
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}
	
	//soft delete
	@Override
	public int deleteEmployee(int employeeId) throws HibernateException,ConstraintViolationException
	{
		int status = 0;
		try {
			Employee employee =getEmployeeById(employeeId);
			if(employee!=null)
			{
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			employee.setUpdateDate(date.toString());
			employee.setUpdateBy(null);
			employee.setDeleteFlag(true);
			Login login=new Login();
			login.setEmployee(employee);
			updateEmployee(employee);
			status=1;
			return status;
		}
			else {
				return status;
			}
		}
			catch (Exception e) {
			e.printStackTrace();
			return status;
		} 
	}
		
	public int deleteEmployeeHard(int employeeId) throws HibernateException,ConstraintViolationException
	{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Employee employee =getEmployeeById(employeeId);
			session.delete(employee);
			status=1;
			return status;
		}
			catch (Exception e) {
			e.printStackTrace();
			return status;
		} 
		finally{
			session.getTransaction().commit();
			
		}
	}
		

}
