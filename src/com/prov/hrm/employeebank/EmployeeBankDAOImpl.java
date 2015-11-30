package com.prov.hrm.employeebank;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeBankDAOImpl implements EmployeeBankDAO {

	// GET ALL RECORDS FROM tblempbank
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBank> getAllBankDetails(int organizationId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeBank.class);
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
			public List<EmployeeBank> getEmployeeBankById(int employeeId,int organizationId) throws HibernateException,ConstraintViolationException
			{
				Session session=SessionFactoryUtil.getSessionFactory().openSession();
				try{
					session.beginTransaction();
					Criteria criteria = session.createCriteria(EmployeeBank.class);
					criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
					criteria.add(Restrictions.eq("organizationId", organizationId));
					criteria.add(Restrictions.eq("deleteFlag", false));
					return (List<EmployeeBank>) criteria.list();
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					} finally {
						session.getTransaction().commit();
						
					}
				}
		
	
	

	// GET A SINGLE RECORD FROM tblempbank USING empbankId
	public EmployeeBank getEmployeeBankId(int empbankId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeBank employeebank = (EmployeeBank) session.get(
					EmployeeBank.class, empbankId);
			if (employeebank != null) {
				if (!employeebank.getDeleteFlag()) {
					return employeebank;
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

	// ADD A EMPLOYEE BANK DETAILS IN tblempbank
	@Override
	public int addEmployeeBank(EmployeeBank employeebank)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeebank.setInsertDate(date.toString());
			employeebank.setUpdateDate(date.toString());
			session.save(employeebank);
			status = 1;
			return status;

		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// UPDATE A EMPLOYEE BANK DETAILS IN tblempbank
	@Override
	public int updateEmployeeBank(EmployeeBank employeebank)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeebank.setUpdateDate(date.toString());
			EmployeeBank empbank = getEmployeeBankId(employeebank
					.getEmpbankId());
			if (empbank != null) {
				employeebank.setInsertBy(empbank.getInsertBy());
				employeebank.setInsertDate(empbank.getInsertDate());
				session.update(employeebank);
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

	// DELETE A EMPLOYEE BANK DETAILS (SOFT DELETE)
	@Override
	public int deleteEmployeebank(int empbankId) throws HibernateException,
			ConstraintViolationException {
		int status = 0;
		try {
			EmployeeBank employeebank = getEmployeeBankId(empbankId);
			if (employeebank != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				employeebank.setUpdateDate(date.toString());
				employeebank.setUpdateBy(null);
				employeebank.setDeleteFlag(true);
				updateEmployeeBank(employeebank);
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
	// DELETE A EMPLOYEE BANK DETAILS (HARD DELETE)
	public int deleteEmployeeBankHard(int empbankId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeBank employeebank = getEmployeeBankId(empbankId);
			session.delete(employeebank);
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
