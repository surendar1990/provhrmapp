package com.prov.hrm.leavetype;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.prov.hrm.department.Department;
import com.prov.hrm.designation.Designation;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.employeeleaveeligibility.EmployeeLeaveEligibility;
import com.prov.hrm.idtype.Idtype;
import com.prov.hrm.leaveconfiguration.LeaveConfiguration;
import com.prov.hrm.role.Role;
import com.prov.hrm.utility.Getaccountyear;
import com.prov.hrm.utility.SessionFactoryUtil;

public class LeaveTypeDAOImpl implements LeaveTypeDAO {

		@SuppressWarnings("unchecked")
		@Override
		public List<LeaveType> getAllLeaveTypenodate(int organizationId)
				throws HibernateException, ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				Criteria criteria = session.createCriteria(LeaveType.class);
				criteria.add(Restrictions.eq("organizationId", organizationId));
				criteria.addOrder(Order.asc("leavetype"));
				criteria.add(Restrictions.eq("deleteFlag", false));
				return criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				session.close();			
			}
		}
		
	@Override
	public LeaveType getLeaveTypeById(int leavetypeId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			LeaveType leavetype = (LeaveType) session.get(LeaveType.class,leavetypeId);
			System.out.println(leavetype);
			if (leavetype != null) {
				if (!leavetype.getDeleteFlag()) {
					return leavetype;
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

	

	@Override
	public int addLeaveType(LeaveType leavetype)throws HibernateException,
	ConstraintViolationException,MySQLIntegrityConstraintViolationException{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(LeaveType.class);
			criteria.add(Restrictions.eq("leavetype",leavetype.getLeavetype()));
			criteria.add(Restrictions.eq("organizationId",leavetype.getOrganizationId()));
			if(criteria.list().size()!=0){
				List<LeaveType> ltype1=new ArrayList<LeaveType>();
				ltype1=criteria.list();
				Iterator<LeaveType> ite= ltype1.iterator();
				while(ite.hasNext()){
					LeaveType ltype2=ite.next();
					if(ltype2.getDeleteFlag()==false){
						status=2;
					}else{
						ltype2.setDeleteFlag(false);
						ltype2.setUpdateDate(date.toString());
						ltype2.setInsertDate(date.toString());
						ltype2.setLeaveDescription(leavetype.getLeaveDescription());
						ltype2.setInsertBy(leavetype.getInsertBy());
						ltype2.setUpdateBy(leavetype.getUpdateBy());
						session.update(ltype2);
			status = 1;
			}}
			}
			else{
				leavetype.setUpdateDate(date.toString());
				leavetype.setInsertDate(date.toString());
				session.save(leavetype);
				status = 1;
			}

			return status;
		}catch (Exception e) {
			e.printStackTrace();
			return status;
		}finally {
			session.getTransaction().commit();
			session.close();
		}
	}


	@Override
	public int updateLeaveType(LeaveType leavetype) throws HibernateException,
			ConstraintViolationException,MySQLIntegrityConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			
			Criteria criteria=session.createCriteria(LeaveType.class);
			criteria.add(Restrictions.eq("leavetype",leavetype.getLeavetype()));
			criteria.add(Restrictions.eq("organizationId",leavetype.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.ne("leavetypeId", leavetype.getLeavetypeId()));
			if(criteria.list().size()==0){
				session.clear();
			LeaveType leave = getLeaveTypeById(leavetype.getLeavetypeId());
			System.out.println(leave);
			if (leave != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				leavetype.setUpdateDate(date.toString());
				leavetype.setInsertDate(leave.getInsertDate());
				leavetype.setInsertBy(leave.getInsertBy());
				leavetype.setUpdateBy(leavetype.getUpdateBy());
				session.update(leavetype);
				
				status = 1;
				return status;
			} else {
				return status;
			}}

			else
			{
				return 5;
			}
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationException(e.getMessage(), e.getSQLException(),e.getCause().getMessage());
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return status;
		}  finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	@Override
	public int deleteLeaveType(int leavetypeId) throws HibernateException,
			ConstraintViolationException {
		int status = 0;
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			LeaveType leavetype = getLeaveTypeById(leavetypeId);
			System.out.println(leavetype);
			List<LeaveType> leavetypelist=new ArrayList<>();
			List<LeaveType> leavetypelist1=new ArrayList<>();
			List<LeaveType> leavetypelist2=new ArrayList<>();
			
			Criteria criteria = session.createCriteria(EmployeeLeave.class);
			criteria.add(Restrictions.eq("organizationId", leavetype.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.eq("leaveType", new LeaveType(leavetype.getLeavetypeId())));
			leavetypelist=criteria.list();
			
			if(leavetypelist.size()==0){
			Criteria criteria2= session.createCriteria(EmployeeLeaveEligibility.class);
			criteria2.add(Restrictions.eq("organizationId",leavetype.getOrganizationId()));
			criteria2.add(Restrictions.eq("deleteFlag", false));
			criteria2.add(Restrictions.eq("leaveTypeId", leavetype.getLeavetypeId()));
			leavetypelist1= criteria2.list();
			}
			if(leavetypelist1.size()==0){
				Criteria criteria3= session.createCriteria(LeaveConfiguration.class);
				criteria3.add(Restrictions.eq("organizationId",leavetype.getOrganizationId()));
				criteria3.add(Restrictions.eq("deleteFlag", false));
				criteria3.add(Restrictions.eq("leavetype",  new LeaveType(leavetype.getLeavetypeId())));
				leavetypelist2= criteria3.list();
			}
			if(leavetypelist.size()!=0 || leavetypelist1.size()!=0 || leavetypelist2.size()!=0)
			{
				status=2;
				return status;
			}
			if (leavetype != null &&  leavetypelist.size()==0 && leavetypelist1.size()==0 && leavetypelist2.size()==0) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				leavetype.setUpdateDate(date.toString());
				leavetype.setUpdateBy(null);
				leavetype.setDeleteFlag(true);
				session.update(leavetype);
				status = 1;
				return status;
				
			}
			else {
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}finally{
			session.getTransaction().commit();
			session.close();
		}

	}

	public int deleteLeaveTypeHard(int leavetypeId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			LeaveType leavetype = (LeaveType) session.get(LeaveType.class,
					leavetypeId);
			session.delete(leavetype);
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
