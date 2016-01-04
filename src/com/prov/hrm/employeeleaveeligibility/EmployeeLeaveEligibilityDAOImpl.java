package com.prov.hrm.employeeleaveeligibility;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

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

			return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();

		}
	}

	// RETURN A RECORD FROM empleaveeligibility by empleaveeligibilityId
	@Override
	public List<EmployeeLeaveEligibility> getEmployeeLeaveEligibilityByEmployeeId(
			int employeeId, int organizationId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();

		}
	}

	// RETURN A RECORD FROM empleaveeligibility by empleaveeligibilityId
	public EmployeeLeaveEligibility getEmployeeLeaveEligibilityById(
			int empleaveeligibilityId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeLeaveEligibility employeeleaveeligibility = (EmployeeLeaveEligibility) session
					.get(EmployeeLeaveEligibility.class, empleaveeligibilityId);
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
			session.close();

		}
	}

	// INSERT A EMPLOYEE LEAVE ELIGIBILITY INTO tblempleaveeligibility
	@Override
	public int addEmployeeLeaveEligibility(
			EmployeeLeaveEligibility employeeleaveeligibility)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();

			Criteria criteria = session
					.createCriteria(EmployeeLeaveEligibility.class)
					.add(Restrictions.eq("organizationId",
							employeeleaveeligibility.getOrganizationId()))
					.add(Restrictions.eq("employee",
							employeeleaveeligibility.getEmployee()))
					.add(Restrictions.eq("leaveType",
							employeeleaveeligibility.getLeaveType()))
					.add(Restrictions.eq("leaveConfiguration",
							employeeleaveeligibility.getLeaveConfiguration()))
					.add(Restrictions.eq("emonth",
							employeeleaveeligibility.getEmonth()))
					.add(Restrictions.eq("eyear",
							employeeleaveeligibility.getEyear()))
					.add(Restrictions.eq("deleteFlag", false));
			if (criteria.list().size() == 0) {
				Timestamp date = new Timestamp(new Date().getTime());
				employeeleaveeligibility.setInsertDate(date.toString());
				employeeleaveeligibility.setUpdateDate(date.toString());

				session.save(employeeleaveeligibility);
				status = 1;
				return status;
			} else {
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			session.close();

		}
	}

	// Insert data into employee leave eligibility for all employee
	public int addAllEmployeeLeaveEligibility(
			List<EmployeeLeaveEligibility> employeeleaveeligibility)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Iterator<EmployeeLeaveEligibility> listempleave = employeeleaveeligibility
					.iterator();
			EmployeeLeaveEligibility empleave = null;
			List<EmployeeLeaveEligibility> insertemplist = new ArrayList<EmployeeLeaveEligibility>();
			List<EmployeeLeaveEligibility> updateemplist = new ArrayList<EmployeeLeaveEligibility>();
			while (listempleave.hasNext()) {
				empleave = listempleave.next();
				Calendar caln=Calendar.getInstance();
				Criterion lhs = Restrictions.isNull("emonth");
				Criterion rhs = Restrictions.eq("emonth", empleave.getEmonth()!=null?empleave.getEmonth():caln.get(Calendar.MONTH));
				EmployeeLeaveEligibility   empleaveelgibility = (EmployeeLeaveEligibility)session
						.createCriteria(EmployeeLeaveEligibility.class)
						.add(Restrictions.eq("organizationId",
								empleave.getOrganizationId()))
						.add(Restrictions.eq("employee", empleave.getEmployee()))
						.add(Restrictions.eq("leaveType",
								empleave.getLeaveType()))
						.add(Restrictions.eq("leaveConfiguration",
								empleave.getLeaveConfiguration()))
						.add(Restrictions.or(lhs, rhs))
						.add(Restrictions.eq("eyear", empleave.getEyear()))
						.add(Restrictions.eq("deleteFlag", false)).uniqueResult();
				if (empleaveelgibility!= null) {
					System.out.println("IF");
					System.out.println(empleaveelgibility);
					Timestamp date = new Timestamp(new Date().getTime());
					empleave.setEmpleaveeligibilityId(empleaveelgibility.getEmpleaveeligibilityId());
					empleave.setInsertDate(date.toString());
					empleave.setUpdateDate(date.toString());
					updateemplist.add(empleave);
					// return status;
				} else {
					
					System.out.println("ELSE");
					Timestamp date = new Timestamp(new Date().getTime());
					empleave.setInsertDate(date.toString());
					empleave.setUpdateDate(date.toString());
					insertemplist.add(empleave);
					// return status;
				}
				
			}
			session.flush();
			session.clear();
			System.out.println("completed adding");
			Iterator<EmployeeLeaveEligibility> empite=insertemplist.iterator();
			int count = 0;
			while(empite.hasNext())
			{
				session.save((EmployeeLeaveEligibility)empite.next());
				if(count%50==0)
				{
					session.flush();
					session.clear();
				}
			}
			System.out.println("completed saving");
			empite=updateemplist.iterator();
			count = 0;
			while(empite.hasNext())
			{
				session.update((EmployeeLeaveEligibility)empite.next());
				if(count%50==0)
				{
					session.flush();
					session.clear();
				}
			}
			System.out.println("completed updating");
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			session.close();

		}
	}

	public int addEmployeeLeaveEligibilityByEmpployeeId(int employeeId,
			int oragnizationId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();

			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			session.close();

		}
	}

	// UPDATE A EMPLOYEE LEAVE REQUEST IN tblempleave
	@Override
	public int updateEmployeeLeaveEligibility(
			EmployeeLeaveEligibility employeeleaveeligibility)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			session.close();

		}
	}

	// Soft Delete a record from tblempleave for specific empleaveid
	@Override
	public int deleteEmployeeLeaveEligibility(int empleaveeligibilityId) {
		int status = 0;
		try {

			return status;

		} catch (Exception e) {
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
			EmployeeLeaveEligibility employeeleaveeligibility = getEmployeeLeaveEligibilityById(empleaveeligibilityId);
			session.delete(employeeleaveeligibility);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.close();

		}
	}

}
