package com.prov.hrm.employeeleaveeligibility;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeLeaveEligibilityDAOImpl implements
		EmployeeLeaveEligibilityDAO {

	// GET ALL RECORD FROM tblempleaveeligibility
	@Override
	public List<EmployeeLeaveEligibility> getAllEmployeeLeaveEligibility(
			int organizationId) throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session
					.createCriteria(EmployeeLeaveEligibility.class);
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

	
	// RETURN A RECORD FROM empleaveeligibility by empleaveeligibilityId
	@Override
	public List<EmployeeLeaveEligibility> getEmployeeLeaveEligibilityById(
			int employeeId,int organizationId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeLeaveEligibility.class);
			criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			return (List<EmployeeLeaveEligibility>) criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				session.getTransaction().commit();
				
			}
		}
	// RETURN A RECORD FROM empleaveeligibility by empleaveeligibilityId
	
	public EmployeeLeaveEligibility getEmployeeLeaveById(
			int employeeId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeLeaveEligibility employeeleaveeligibility = (EmployeeLeaveEligibility) session
					.get(EmployeeLeaveEligibility.class, employeeId);
			if (employeeleaveeligibility != null) {
				if (!employeeleaveeligibility.isDeleteFlag()) {
					return employeeleaveeligibility;
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

	// INSERT A EMPLOYEE LEAVE ELIGIBILITY INTO tblempleaveeligibility
	@Override
	public int addEmployeeLeaveEligibility(
			EmployeeLeaveEligibility employeeleaveeligibility) throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeeleaveeligibility.setInsertDate(date.toString());
			employeeleaveeligibility.setUpdateDate(date.toString());
			session.save(employeeleaveeligibility);
			status=1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// UPDATE A EMPLOYEE LEAVE REQUEST IN tblempleave
	@Override
	public int updateEmployeeLeaveEligibility(
			EmployeeLeaveEligibility employeeleaveeligibility) throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeeleaveeligibility.setUpdateDate(date.toString());
			EmployeeLeaveEligibility empleaveeligibility = getEmployeeLeaveById(employeeleaveeligibility.getEmpleaveeligibilityId());
			if (empleaveeligibility != null) {
				employeeleaveeligibility.setInsertBy(empleaveeligibility.getInsertBy());
				employeeleaveeligibility.setInsertDate(empleaveeligibility.getInsertDate());
				session.update(employeeleaveeligibility);
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
	// Soft Delete a record from tblempleave for specific empleaveid
	@Override
	public int deleteEmployeeLeaveEligibility(int empleaveeligibilityId) 
	{
		int status = 0;
	try {
		EmployeeLeaveEligibility employeeleaveeligibility = getEmployeeLeaveById(empleaveeligibilityId);
		if(employeeleaveeligibility!=null)
		{
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		employeeleaveeligibility.setUpdateDate(date.toString());
		employeeleaveeligibility.setUpdateBy(null);
		employeeleaveeligibility.setDeleteFlag(true);
		updateEmployeeLeaveEligibility(employeeleaveeligibility);
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
	// Hard Delete a record from tblempleave for specific empleaveid
		public int deleteEmployeeLeaveEligibilityHard(int empleaveeligibilityId)
				throws HibernateException, ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status = 0;
			try {
				session.beginTransaction();
				EmployeeLeaveEligibility employeeleaveeligibility = getEmployeeLeaveById(empleaveeligibilityId);
				session.delete(employeeleaveeligibility);
				status = 1;
				return status;
			} catch (Exception e) {
				e.printStackTrace();
				return status;
			} finally {
				session.getTransaction().commit();
				
			}
		}
}
