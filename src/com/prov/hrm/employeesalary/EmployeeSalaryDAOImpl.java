package com.prov.hrm.employeesalary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeSalaryDAOImpl implements EmployeeSalaryDAO {

	// Return all record from tblempsalary
	@Override
	public List<EmployeeSalary> getAllEmployeeSalary(int organizationid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		List<EmployeeSalary> employeesalary=new ArrayList<EmployeeSalary>();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeSalary.class);
			criteria.add(Restrictions.eq("organizationId", organizationid));
			criteria.add(Restrictions.eq("deleteFlag", false));
			employeesalary=criteria.list();
			session.getTransaction().commit();
			 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			Iterator<EmployeeSalary> iterate=employeesalary.iterator();
			while(iterate.hasNext()){
				EmployeeSalary empsalary= (EmployeeSalary) iterate.next();
				empsalary.setEffectiveFrom(sdfOut.format(sdinput.parse(empsalary.getEffectiveFrom())));
				empsalary.setEffectiveTo(sdfOut.format(sdinput.parse(empsalary.getEffectiveTo())));
				
			}
			return employeesalary;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		
		}

	}

	// Return a record from tblempsalary for specific empsalaryid
	@Override
	public EmployeeSalary getEmployeeSalaryById(int employeesalaryid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			return (EmployeeSalary) session.get(EmployeeSalary.class,
					employeesalaryid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Insert a record into tblempsalary
	@Override
	public int addEmployeeSalary(EmployeeSalary employeesalary)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeesalary.setUpdateDate(date);
			employeesalary.setInsertDate(date);
			session.save(employeesalary);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Update a record in tblempsalary for specific emplsalaryid
	@Override
	public int updateEmployeeSalary(EmployeeSalary employeesalary)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeesalary.setUpdateDate(date);
			session.update(employeesalary);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Soft Delete a record from tblempsalary for specific empsalaryid
	public int deleteEmployeeSalary(int employeesalaryid) {
		int status = 0;
		try {
			EmployeeSalary employeesalary = getEmployeeSalaryById(employeesalaryid);
			employeesalary.setUpdateBy(null);
			employeesalary.setDeleteFlag(true);
			updateEmployeeSalary(employeesalary);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
	}

	// Hard Delete a record from tblempsalary for specific empsalaryid
	public int deleteEmployeeSalaryHard(int employeesalaryid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeSalary employeesalary = getEmployeeSalaryById(employeesalaryid);
			session.delete(employeesalary);
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
