package com.prov.hrm.employeeleaveeligibility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeLeaveEligibilityDAOImpl implements
		EmployeeLeaveEligibilityDAO {

	private Object crudService;
	// GET ALL RECORD FROM tblempleaveeligibility
	@Override
	public List<EmployeeLeaveEligibility> getAllEmployeeLeaveEligibility(
			int organizationId) throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		List <EmployeeLeaveEligibility>resultset=new ArrayList<EmployeeLeaveEligibility>();
		try {
			session.beginTransaction();
			Criteria leavecriteria = session.createCriteria(LeaveType.class);
			leavecriteria.add(Restrictions.eq("organizationId", organizationId));
			leavecriteria.add(Restrictions.eq("deleteFlag", false));
			Criteria empeligibilitycriteria=session.createCriteria(EmployeeLeaveEligibility.class);
			empeligibilitycriteria.add(Restrictions.eq("organizationId", organizationId));
			empeligibilitycriteria.add(Restrictions.eq("deleteFlag", false));
			System.out.println("1");
			List <EmployeeLeaveEligibility>empeligibilitylist= empeligibilitycriteria.list();
			session.flush();
			System.out.println(empeligibilitylist);
/*			Criteria  leavecriteria= empeligibilitycriteria.createCriteria("LeaveType");
			leavecriteria.add(Restrictions.eq("organizationId", organizationId));
			leavecriteria.add(Restrictions.eq("deleteFlag", false));
*/			List <LeaveType>leavetypelist= leavecriteria.list();
			System.out.println(leavetypelist);
			session.flush();
			System.out.println("2");
			
			LeaveType lt=null;
			EmployeeLeaveEligibility eel=null;
			Iterator<LeaveType> iteratorleavetype =leavetypelist.iterator();
			Iterator<EmployeeLeaveEligibility> itreatorempeligibility =empeligibilitylist.iterator();
			
			while(iteratorleavetype.hasNext())
			{
				 lt=new LeaveType();
				 lt=iteratorleavetype.next();
				 eel=new EmployeeLeaveEligibility();
				 eel=itreatorempeligibility.next();
				 /*if(lt.getLeavetypeId().SIZE!=eel.getLeaveType().getLeavetypeId().SIZE)
				 {
					 
				 }*/
				 
				 while(itreatorempeligibility.hasNext())
				 {
					 eel=new EmployeeLeaveEligibility();
					 eel=itreatorempeligibility.next();
					 
					 if(lt.getLeavetypeId()!=eel.getLeaveType().getLeavetypeId())
					 {
						System.out.println("Inside"); 
						 EmployeeLeaveEligibility eml=new EmployeeLeaveEligibility();
						 eml.setDeleteFlag(false);
						 eml.setEmployee(eel.getEmployee());
						 eml.setOrganizationId(eel.getOrganizationId());
						 eml.setFromDate(eel.getFromDate());
						 eml.setToDate(eel.getToDate());
						 int leave=lt.getLeavetypeId();
						 LeaveType leave1=new LeaveType(leave);
						 eml.setLeaveType(leave1);
						 //addEmployeeLeaveEligibility(eml);
						 resultset.add(eml);
						
					 }
					 else
					 {
						 resultset.add(eel);
					 }
				 }
				
			}
			return resultset;
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
		List <EmployeeLeaveEligibility>resultset=new ArrayList<EmployeeLeaveEligibility>();
		try {
			session.beginTransaction();
			Criteria leavecriteria = session.createCriteria(LeaveType.class);
			leavecriteria.add(Restrictions.eq("organizationId", organizationId));
			leavecriteria.add(Restrictions.eq("deleteFlag", false));
			System.out.println(employeeId);
			EmployeeLeaveEligibility empeligibilitycriteria=(EmployeeLeaveEligibility) session.get(EmployeeLeaveEligibility.class,employeeId);
			//empeligibilitycriteria.add(Restrictions.eq("organizationId", organizationId));
			//empeligibilitycriteria.add(Restrictions.eq("deleteFlag", false));
			System.out.println("1");
			System.out.println(empeligibilitycriteria);
			Query query=session.createSQLQuery("select e.empleaveeligibility_id,d.organization_id,e.employee_id,e.from_date,e.to_date,e.eligibilitydays,d.leavetype_id,d.leavetype,d.eligible_days,d.leave_description from (select * from tblempleaveeligibility as a where a.employee_id=4 and a.organization_id=3) e RIGHT JOIN  tblleavetype d on e.leavetype_id=d.leavetype_id where d.organization_id='"+organizationId+"'");
             
			
					
			
			//List <EmployeeLeaveEligibility> empeligibilitylist= (List<EmployeeLeaveEligibility>) empeligibilitycriteria;
			session.flush();
			System.out.println(empeligibilitycriteria);
/*			Criteria  leavecriteria= empeligibilitycriteria.createCriteria("LeaveType");
			leavecriteria.add(Restrictions.eq("organizationId", organizationId));
			leavecriteria.add(Restrictions.eq("deleteFlag", false));
*/			List <LeaveType>leavetypelist= leavecriteria.list();
			System.out.println(leavetypelist);
			session.flush();
			System.out.println("2");
			
//			LeaveType lt=null;
//			EmployeeLeaveEligibility eel=null;
//			Iterator<LeaveType> iteratorleavetype =leavetypelist.iterator();
//			//Iterator<EmployeeLeaveEligibility> itreatorempeligibility =empeligibilitylist.iterator();
//			
//			while(iteratorleavetype.hasNext())
//			{
//				 lt=new LeaveType();
//				 lt=iteratorleavetype.next();
//				 eel=new EmployeeLeaveEligibility();
//				// eel=itreatorempeligibility.next();
//				 /*if(lt.getLeavetypeId().SIZE!=eel.getLeaveType().getLeavetypeId().SIZE)
//				 {
//					 
//				 }*/
//				 
//				 while(itreatorempeligibility.hasNext())
//				 {
//					 eel=new EmployeeLeaveEligibility();
//					 eel=itreatorempeligibility.next();
//					 
//					 if(lt.getLeavetypeId()!=eel.getLeaveType().getLeavetypeId())
//					 {
//						System.out.println("Inside"); 
//						 EmployeeLeaveEligibility eml=new EmployeeLeaveEligibility();
//						 eml.setDeleteFlag(false);
//						 eml.setEmployee(eel.getEmployee());
//						 eml.setOrganizationId(eel.getOrganizationId());
//						 eml.setFromDate(eel.getFromDate());
//						 eml.setToDate(eel.getToDate());
//						 int leave=lt.getLeavetypeId();
//						 LeaveType leave1=new LeaveType(leave);
//						 eml.setLeaveType(leave1);
//						// addEmployeeLeaveEligibility(eml);
//						 resultset.add(eml);
//						
//					 }
//					 else
//					 {
//						 resultset.add(eel);
//					 }
//				 }
//				
//			}
			
			
			
			return query.list();
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
