package com.prov.hrm.leavetypescheme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.employeeleaveeligibility.EmployeeLeaveEligibility;
import com.prov.hrm.leaveconfiguration.LeaveConfiguration;
import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.utility.SessionFactoryUtil;

public class LeaveTypeSchemeDAOImpl implements LeaveTypeSchemeDAO 
{

	@Override
	public List<LeaveTypeScheme> getAllLeaveTypeScheme(int organizationId)
	throws HibernateException, ConstraintViolationException{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(LeaveTypeScheme.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
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
	public LeaveTypeScheme getLeaveTypeSchemeById(int leaveTypeSchemeId)throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			LeaveTypeScheme leavetypescheme = (LeaveTypeScheme) session.get(LeaveTypeScheme.class,leaveTypeSchemeId);
			if (leavetypescheme != null) {
				if (!leavetypescheme.isDeleteFlag()) {
					return leavetypescheme;
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
	public int addLeaveTypeScheme(LeaveTypeScheme leaveTypescheme)
			throws HibernateException, ConstraintViolationException,
			MySQLIntegrityConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(LeaveTypeScheme.class);
			criteria.add(Restrictions.eq("leavetypeSchemeName",leaveTypescheme.getLeavetypeSchemeName()));
			criteria.add(Restrictions.eq("organizationId",leaveTypescheme.getOrganizationId()));
			if(criteria.list().size()!=0){
				List<LeaveTypeScheme> ltypes1=new ArrayList<LeaveTypeScheme>();
				ltypes1=criteria.list();
				Iterator<LeaveTypeScheme> ite= ltypes1.iterator();
				while(ite.hasNext()){
					LeaveTypeScheme ltypes2=ite.next();
					if(ltypes2.isDeleteFlag()==false){
						status=2;
					}else{
						ltypes2.setDeleteFlag(false);
						ltypes2.setUpdateDate(date.toString());
						ltypes2.setInsertDate(date.toString());
						ltypes2.setLeavetypeSchemeDescription(leaveTypescheme.getLeavetypeSchemeDescription());
						ltypes2.setInsertBy(leaveTypescheme.getInsertBy());
						ltypes2.setUpdateBy(leaveTypescheme.getUpdateBy());
						session.update(ltypes2);
			status = 1;
			}}
			}
			else{
				leaveTypescheme.setUpdateDate(date.toString());
				leaveTypescheme.setInsertDate(date.toString());
				session.save(leaveTypescheme);
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
	public int updateLeaveTypeScheme(LeaveTypeScheme leaveTypescheme)
			throws HibernateException, ConstraintViolationException,
			MySQLIntegrityConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			
			Criteria criteria=session.createCriteria(LeaveTypeScheme.class);
			criteria.add(Restrictions.eq("leavetypeSchemeName",leaveTypescheme.getLeavetypeSchemeName()));
			criteria.add(Restrictions.eq("organizationId",leaveTypescheme.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.ne("leaveTypeSchemeId", leaveTypescheme.getLeaveTypeSchemeId()));

			if(criteria.list().size()==0){
				session.clear();
				LeaveTypeScheme leavescheme = getLeaveTypeSchemeById(leaveTypescheme.getLeaveTypeSchemeId());
			if (leavescheme != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				leaveTypescheme.setUpdateDate(date.toString());
				leaveTypescheme.setInsertDate(leavescheme.getInsertDate());
				leaveTypescheme.setInsertBy(leavescheme.getInsertBy());
				leaveTypescheme.setUpdateBy(leaveTypescheme.getUpdateBy());
				session.update(leaveTypescheme);
				
				status = 1;
				return status;
			} else {
				return status;
			}}else{
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
	public int deleteLeaveTypeScheme(int leaveTypeSchemeId)
			throws HibernateException, ConstraintViolationException {
		int status = 0;

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			LeaveTypeScheme leavetypescheme = getLeaveTypeSchemeById(leaveTypeSchemeId);
			List<LeaveTypeScheme> leavetypeschemelist = new ArrayList<>();
			List<LeaveTypeScheme> leavetypeschemelist1 = new ArrayList<>();
			
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("organizationId",leavetypescheme.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.eq("leaveTypeScheme",new LeaveTypeScheme(leavetypescheme.getLeaveTypeSchemeId())));
			leavetypeschemelist = criteria.list();
			if (leavetypeschemelist.size() == 0) {
				Criteria criteria2 = session.createCriteria(LeaveConfiguration.class);
				criteria2.add(Restrictions.eq("organizationId",leavetypescheme.getOrganizationId()));
				criteria2.add(Restrictions.eq("deleteFlag", false));
				criteria2.add(Restrictions.eq("leaveTypeScheme", new LeaveTypeScheme(leavetypescheme.getLeaveTypeSchemeId())));
				leavetypeschemelist1 = criteria2.list();
			}
			if (leavetypeschemelist.size() != 0 || leavetypeschemelist1.size() != 0) {
				status = 2;
				return status;
			}
			if (leavetypescheme != null && leavetypeschemelist.size() == 0 && leavetypeschemelist1.size() == 0) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				leavetypescheme.setUpdateDate(date.toString());
				leavetypescheme.setUpdateBy(null);
				leavetypescheme.setDeleteFlag(true);
				session.update(leavetypescheme);
				status = 1;
				return status;

			} else {
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}finally {
			session.getTransaction().commit();
			session.close();
		}

	}

	public int deleteLeaveTypeschemeHard(int leaveTypeSchemeId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			LeaveTypeScheme leavetypescheme = (LeaveTypeScheme) session.get(LeaveTypeScheme.class,
					leaveTypeSchemeId);
			session.delete(leavetypescheme);
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
