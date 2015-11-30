package com.prov.hrm.employeeleave;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeLeaveDAOImpl implements EmployeeLeaveDAO {

	// GET ALL RECORD FROM tblempleave
	@Override
	public List<EmployeeLeave> getAllEmployeeLeave(int organizationId)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeLeave.class);
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
	@Override
	public List<EmployeeLeave> getEmployeebyEmployeeId(int employeeId,
			int organizationId) throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeLeave.class);
			
			criteria.add(Restrictions.eq("employee",new Employee(employeeId)));
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

	// RETURN A RECORD FROM tblempleave USING empleaveId
	@Override
	public EmployeeLeave getEmployeeLeaveById(int empleaveId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeLeave employeeleave =(EmployeeLeave) session.get(EmployeeLeave.class, empleaveId); 
			if (employeeleave != null) {
				if (!employeeleave.isDeleteFlag()) {
					return employeeleave;
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
	// INSERT A EMPLOYEE LEAVE REQUST INTO tblempleave
	@Override
	public int addEmployeeLeave(EmployeeLeave employeeleave)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeeleave.setInsertDate(date.toString());
			employeeleave.setUpdateDate(date.toString());
			session.save(employeeleave);
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
	public int updateEmployeeLeave(EmployeeLeave employeeleave)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeeleave.setUpdateDate(date.toString());
			EmployeeLeave empleave = getEmployeeLeaveById(employeeleave.getEmpleaveId());
			if (empleave != null) {
				employeeleave.setInsertBy(empleave.getInsertBy());
				employeeleave.setInsertDate(empleave.getInsertDate());
				session.update(employeeleave);
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
	public int deleteEmployeeLeave(int employeeleaveId) {
		int status = 0;
		try {
			EmployeeLeave employeeleave = getEmployeeLeaveById(employeeleaveId);
			if(employeeleave!=null)
			{
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			employeeleave.setUpdateDate(date.toString());
			employeeleave.setUpdateBy(null);
			employeeleave.setDeleteFlag(true);
			updateEmployeeLeave(employeeleave);
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
	public int deleteEmployeeLeaveHard(int employeeleaveid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeLeave employeeLeave = getEmployeeLeaveById(employeeleaveid);
			session.delete(employeeLeave);
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
