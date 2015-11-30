package com.prov.hrm.employeeeducation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeEducationDAOImpl implements EmployeeEducationDAO {

	// GET ALL RECORDS FROM tblempeducation
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEducation> getAllEmployeeEducation(int organizationId)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeEducation.class);
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
	
	//GET A SINGLE RECORD by employeeId
		@Override
		public List<EmployeeEducation> getEmployeeEducationById(int employeeId,int organizationId) throws HibernateException,ConstraintViolationException
		{
			Session session=SessionFactoryUtil.getSessionFactory().openSession();
			try{
				session.beginTransaction();
				Criteria criteria = session.createCriteria(EmployeeEducation.class);
				criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
				criteria.add(Restrictions.eq("organizationId", organizationId));
				criteria.add(Restrictions.eq("deleteFlag", false));
				return (List<EmployeeEducation>) criteria.list();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					session.getTransaction().commit();
					
				}
			}
	
	
	
	
	

	// GET A SINGLE RECORD FROM tblempeducation USING empeducationId
	
	public EmployeeEducation getEmployeeEducationId(int empeducationId)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeEducation empeducation = (EmployeeEducation) session.get(
					EmployeeEducation.class, empeducationId);
			if (empeducation != null) {
				if (!empeducation.getDeleteFlag()) {
					return empeducation;
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
	// ADD A EMPLOYEE EDUCATION DETAILS IN tblempeducation
	@Override
	public int addEmployeeEducation(EmployeeEducation employeeeducation)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeeeducation.setUpdateDate(date.toString());
			employeeeducation.setInsertDate(date.toString());
			session.save(employeeeducation);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// UPDATE A EMPLOYEE EDUCATION DETAILS IN tblempeducation
	@Override
	public int updateEmployeeEducation(EmployeeEducation employeeeducation)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeeeducation.setUpdateDate(date.toString());
			EmployeeEducation empeducation = getEmployeeEducationId(employeeeducation.getEmpeducationId());
			if (empeducation != null) {
				employeeeducation.setInsertBy(empeducation.getInsertBy());
				employeeeducation.setInsertDate(empeducation.getInsertDate());
				session.update(employeeeducation);
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
	// DELETE A EMPLOYEE EDUCATION DETAILS (SOFT DELETE)
	public int deleteEmployeeEducation(int empeducationId)throws HibernateException, ConstraintViolationException {
		int status = 0;
		try {
			EmployeeEducation employeeeducation = getEmployeeEducationId(empeducationId);
			if (employeeeducation != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				employeeeducation.setUpdateDate(date.toString());
				employeeeducation.setUpdateBy(null);
				employeeeducation.setDeleteFlag(true);
				updateEmployeeEducation(employeeeducation);
				status = 1;
				return status;
			} else {
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
	}
	// DELETE A EMPLOYEE EDUCATION DETAILS (HARD DELETE)
	public int deleteEmployeeEducationHard(int empeducationId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeEducation employeeEducation = getEmployeeEducationId(empeducationId);
			session.delete(employeeEducation);
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
