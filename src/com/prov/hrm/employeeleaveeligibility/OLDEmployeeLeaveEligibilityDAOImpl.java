package com.prov.hrm.employeeleaveeligibility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.utility.SessionFactoryUtil;

public class OLDEmployeeLeaveEligibilityDAOImpl implements
		EmployeeLeaveEligibilityDAO {

	// GET ALL RECORD FROM tblempleaveeligibility
	@Override
	public List<EmployeeLeaveEligibility> getAllEmployeeLeaveEligibility(
			int organizationId) throws HibernateException {
		return null;
		/*Session session = SessionFactoryUtil.getSessionFactory().openSession();
		List<EmployeeLeaveEligibility> employeeleaveeligibility=new ArrayList<EmployeeLeaveEligibility>();
		List<EmployeeLeaveEligibility> employeeleaveeligibility1=new ArrayList<EmployeeLeaveEligibility>();
		try {
			session.beginTransaction();
			Criteria criteria = session
					.createCriteria(EmployeeLeaveEligibility.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			employeeleaveeligibility1=criteria.list();
			session.getTransaction().commit();
			Iterator<EmployeeLeaveEligibility> ite = employeeleaveeligibility1.iterator();
			 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			while(ite.hasNext()) {
				EmployeeLeaveEligibility empleaveeligibility=(EmployeeLeaveEligibility)ite.next();
				Date formDate = sdinput.parse(empleaveeligibility.getFromDate());
				Date toDate = sdinput.parse(empleaveeligibility.getToDate());
				empleaveeligibility.setFromDate(sdfOut.format(formDate));
				empleaveeligibility.setToDate(sdfOut.format(toDate));
				employeeleaveeligibility.add(empleaveeligibility);
			}
			return employeeleaveeligibility;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
			
		}*/
	}

	
	/*// RETURN A RECORD FROM empleaveeligibility by empleaveeligibilityId
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
		}*/
	
	// RETURN A RECORD FROM empleaveeligibility by empleaveeligibilityId
		@Override
		public List<EmployeeLeaveEligibility> getEmployeeLeaveEligibilityByEmployeeId(
				int employeeId,int organizationId) throws HibernateException,
				ConstraintViolationException {
			return null;
		/*	Session session = SessionFactoryUtil.getSessionFactory().openSession();
			List<EmployeeLeaveEligibility> employeeleaveeligibility=new ArrayList<EmployeeLeaveEligibility>();
			List<EmployeeLeaveEligibility> employeeleaveeligibility1=new ArrayList<EmployeeLeaveEligibility>();
			
			try {
				session.beginTransaction();
				Criteria leavecriteria = session.createCriteria(LeaveType.class);
				leavecriteria.add(Restrictions.eq("organizationId", organizationId));
				leavecriteria.add(Restrictions.eq("deleteFlag", false));
				EmployeeLeaveEligibility empeligibilitycriteria=(EmployeeLeaveEligibility) session.get(EmployeeLeaveEligibility.class,employeeId);
				Query query=session.createSQLQuery("select e.empleaveeligibility_id,d.organization_id,e.employee_id,e.from_date,e.to_date,e.eligibilitydays,d.leavetype_id,d.leavetype,d.eligible_days,d.leave_description,e.leave_reporting_head,e.leave_reporting_to,e.leave_reporting_hr from"
						+ " (select * from tblempleaveeligibility as a where a.employee_id='"+employeeId+"' and a.organization_id='"+organizationId+"' and a.delete_flag=0) e RIGHT JOIN "
						+ " tblleavetype d on e.leavetype_id=d.leavetype_id where d.organization_id='"+organizationId+"' and d.delete_flag=0");
	            session.flush();
				List <LeaveType>leavetypelist= leavecriteria.list();
				session.flush();
				employeeleaveeligibility=query.list();
				session.getTransaction().commit();
				Iterator<EmployeeLeaveEligibility> ite = employeeleaveeligibility1.iterator();
				 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
				 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
				while(ite.hasNext()) {
					EmployeeLeaveEligibility empleaveeligibility=(EmployeeLeaveEligibility)ite.next();
					Date formDate = sdinput.parse(empleaveeligibility.getFromDate());
					Date toDate = sdinput.parse(empleaveeligibility.getToDate());
					empleaveeligibility.setFromDate(sdfOut.format(formDate));
					empleaveeligibility.setToDate(sdfOut.format(toDate));
					employeeleaveeligibility.add(empleaveeligibility);
				}
				return employeeleaveeligibility;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				session.close();
				
			}*/
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
			session.close();
			
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
			/*java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			employeeleaveeligibility.setInsertDate(date.toString());
			employeeleaveeligibility.setUpdateDate(date.toString());
			Date formDate = indate.parse(employeeleaveeligibility.getFromDate());
			Date toDate = indate.parse(employeeleaveeligibility.getToDate());
			employeeleaveeligibility.setFromDate(outdate.format(formDate));
			employeeleaveeligibility.setToDate(outdate.format(toDate));
			session.save(employeeleaveeligibility);
			status=1;*/
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
			EmployeeLeaveEligibility employeeleaveeligibility) throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			/*java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			employeeleaveeligibility.setUpdateDate(date.toString());
			EmployeeLeaveEligibility empleaveeligibility = getEmployeeLeaveById(employeeleaveeligibility.getEmpleaveeligibilityId());
			if (empleaveeligibility != null) {
				employeeleaveeligibility.setInsertBy(empleaveeligibility.getInsertBy());
				employeeleaveeligibility.setInsertDate(empleaveeligibility.getInsertDate());
				Date formDate = indate.parse(employeeleaveeligibility.getFromDate());
				Date toDate = indate.parse(employeeleaveeligibility.getToDate());
				employeeleaveeligibility.setFromDate(outdate.format(formDate));
				employeeleaveeligibility.setToDate(outdate.format(toDate));
				session.update(employeeleaveeligibility);
			} else {
				return status;
			}
			status = 1;*/
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
				session.close();
				
			}
		}
}
