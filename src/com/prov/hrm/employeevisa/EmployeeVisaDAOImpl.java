package com.prov.hrm.employeevisa;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeVisaDAOImpl implements EmployeeVisaDAO {

	// Return all record from tblempvisa
	@Override
	public List<EmployeeVisa> getAllEmployeeVisa(int organizationid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeVisa.class);
			criteria.add(Restrictions.eq("organizationId", organizationid));
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
		}

	}

	// Return a record from tblempvisa for specific empvisaid
	@Override
	public EmployeeVisa getEmployeeVisaById(int employeeVisaId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			return (EmployeeVisa) session.get(EmployeeVisa.class,
					employeeVisaId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
		
		}
	}

	// Insert a record into tblempvisa
	@Override
	public int addEmployeeVisa(EmployeeVisa employeevisa)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeevisa.setUpdateDate(date.toString());
			employeevisa.setInsertDate(date.toString());
			session.save(employeevisa);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Update a record in tblempvisa for specific emplvisaid
	@Override
	public int updateEmployeeVisa(EmployeeVisa employeevisa)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeevisa.setUpdateDate(date.toString());
			session.update(employeevisa);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Soft Delete a record from tblempvisa for specific empvisaid
	public int deleteEmployeeVisa(int employeevisaid) {
		int status = 0;
		try {
			EmployeeVisa employeemarital = getEmployeeVisaById(employeevisaid);
			employeemarital.setUpdateBy(null);
			employeemarital.setDeleteFlag(true);
			updateEmployeeVisa(employeemarital);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
	}

	// Hard Delete a record from tblempvisa for specific empvisaid

	public int deleteEmployeeVisaHard(int employeevisaid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeVisa employeemarital = getEmployeeVisaById(employeevisaid);
			session.delete(employeemarital);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	@Override
	public List<EmployeeVisa> getEmployeeVisaByEmployeeId(int employeeId, int organizationId) 
		throws HibernateException, ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				Criteria criteria = session.createCriteria(EmployeeVisa.class);
				criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
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

	}


