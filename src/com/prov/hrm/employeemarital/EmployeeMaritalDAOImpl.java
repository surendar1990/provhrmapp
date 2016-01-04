package com.prov.hrm.employeemarital;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeebank.EmployeeBank;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeMaritalDAOImpl implements EmployeeMaritalDAO {

	// GET ALL RECORDS FROM tblempmarital
	@Override
	public List<EmployeeMarital> getAllEmployeeMarital(int organizationId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeMarital.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();
		}

	}

	//GET A SINGLE RECORD by employeeId
			@Override
			public List<EmployeeMarital> getEmployeeMaritalById(int employeeId,int organizationId) throws HibernateException,ConstraintViolationException
			{
				Session session=SessionFactoryUtil.getSessionFactory().openSession();
				try{
					session.beginTransaction();
					Criteria criteria = session.createCriteria(EmployeeMarital.class);
					criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
					criteria.add(Restrictions.eq("organizationId", organizationId));
					criteria.add(Restrictions.eq("deleteFlag", false));
					return (List<EmployeeMarital>) criteria.list();
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					} finally {
						session.getTransaction().commit();
						session.close();
					}
				}
	// GET A SINGLE RECORD FROM tblempmarital USING empmaritalId
	
	public EmployeeMarital getEmployeeMaritalId(int empmaritalId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeMarital employeemarital = (EmployeeMarital) session.get(
					EmployeeMarital.class, empmaritalId);
			if (employeemarital != null) {
				if (!employeemarital.getDeleteFlag()) {
					return employeemarital;
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
			session.close();
		}
	}
	// Insert a record into tblempmarital
	@Override
	public int addEmployeeMarital(EmployeeMarital employeemarital)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(EmployeeMarital.class);
			criteria.add(Restrictions.eq("organizationId",employeemarital.getOrganizationId()));
			criteria.add(Restrictions.eq("employee", new Employee(employeemarital.getEmployee().getEmployeeId())));
			criteria.add(Restrictions.eq("deleteFlag", true));
			if(criteria.list().size()==0){
			employeemarital.setUpdateDate(date.toString());
			employeemarital.setInsertDate(date.toString());
			session.save(employeemarital);
			status = 1;
			}else{
				Iterator<EmployeeMarital> iterator=criteria.list().iterator();
				while(iterator.hasNext()){
					EmployeeMarital empmarital=iterator.next();
					empmarital.setAge(employeemarital.getAge());
					empmarital.setSpouseName(employeemarital.getSpouseName());
					empmarital.setNoOfChildren(employeemarital.getNoOfChildren());
					empmarital.setInsertBy(employeemarital.getInsertBy());
					empmarital.setUpdateBy(employeemarital.getUpdateBy());
					empmarital.setDeleteFlag(false);
					empmarital.setInsertDate(date.toString());
					empmarital.setUpdateDate(date.toString());
					session.update(empmarital);
					status=1;
				}
			}
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	// Update a record in tblempmarital for specific emplleaveid
	@Override
	public int updateEmployeeMarital(EmployeeMarital employeemarital)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeemarital.setUpdateDate(date.toString());
			employeemarital.setUpdateBy(null);
			EmployeeMarital empmarita = getEmployeeMaritalId(employeemarital.getEmpmaritalId());
			if (empmarita != null) {
				
				session.update(employeemarital);
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
			session.close();
		}
	}

	// Soft Delete a record from tblempmarital for specific empmaritalid
	public int deleteEmployeeMarital(int empmaritalid) {
		int status = 0;
		try {
			EmployeeMarital employeemarital = getEmployeeMaritalId(empmaritalid);
			employeemarital.setUpdateBy(null);
			employeemarital.setDeleteFlag(true);
			updateEmployeeMarital(employeemarital);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
	}

	// Hard Delete a record from tblempmarital for specific empmaritalid

	public int deleteEmployeeMaritalHard(int employeemaritalid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeMarital employeemarital = getEmployeeMaritalId(employeemaritalid);
			session.delete(employeemarital);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

}
