package com.prov.hrm.leavetype;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.idtype.Idtype;
import com.prov.hrm.utility.SessionFactoryUtil;

public class LeaveTypeDAOImpl implements LeaveTypeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveType> getAllLeaveType(int organizationId,String fromDate,String toDate)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		 List<LeaveType> leavtype=new ArrayList<LeaveType>();
		 List<LeaveType> leavetype=new ArrayList<LeaveType>();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(LeaveType.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.between("fromDate", fromDate, toDate));
			criteria.add(Restrictions.between("toDate", fromDate, toDate));
			leavtype=criteria.list();
			session.getTransaction().commit();
			Iterator<LeaveType> ite = leavtype.iterator();
			 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			while(ite.hasNext()) {
				LeaveType leave=(LeaveType)ite.next();
				Date dateFrom = sdinput.parse(leave.getFromDate());
				Date dateTo = sdinput.parse(leave.getToDate());
				leave.setFromDate(sdfOut.format(dateFrom));
				leave.setToDate(sdfOut.format(dateTo));
				leavetype.add(leave);
			}
			return leavetype;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		
		}
	}



		@SuppressWarnings("unchecked")
		@Override
		public List<LeaveType> getAllLeaveTypenodate(int organizationId)
				throws HibernateException, ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				Criteria criteria = session.createCriteria(LeaveType.class);
				criteria.add(Restrictions.eq("organizationId", organizationId));
				criteria.add(Restrictions.eq("deleteFlag", false));
				return criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				
			
			}
		}
	@Override
	public LeaveType getLeaveTypeById(int leavetypeId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			LeaveType leavetype = (LeaveType) session.get(LeaveType.class,leavetypeId);
			if (leavetype != null) {
				if (!leavetype.getDeleteFlag()) {
					session.getTransaction().commit();
					 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
					 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
					 Date dateFrom = sdinput.parse(leavetype.getFromDate());
						Date dateTo = sdinput.parse(leavetype.getToDate());
						leavetype.setFromDate(sdfOut.format(dateFrom));
						leavetype.setToDate(sdfOut.format(dateTo));
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
			SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			leavetype.setInsertDate(date.toString());
			leavetype.setUpdateDate(date.toString());
			Date fromDate = indate.parse(leavetype.getFromDate());
			Date toDate = indate.parse(leavetype.getToDate());
			leavetype.setFromDate(outdate.format(fromDate));
			leavetype.setToDate(outdate.format(toDate));
			
			session.save(leavetype);
			status = 1;
			return status;
		}catch (ConstraintViolationException e) {
			throw new ConstraintViolationException(e.getMessage(), e.getSQLException(),e.getCause().getMessage());
		
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

			LeaveType leave = getLeaveTypeById(leavetype.getLeavetypeId());
			if (leave != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
				leavetype.setUpdateDate(date.toString());
				leavetype.setInsertDate(leave.getInsertDate());
				leavetype.setInsertBy(leave.getInsertBy());
				Date fromDate = indate.parse(leavetype.getFromDate());
				Date toDate = indate.parse(leavetype.getToDate());
				leavetype.setFromDate(outdate.format(fromDate));
				leavetype.setToDate(outdate.format(toDate));
				session.update(leavetype);
				status = 1;
				return status;
			} else {
				return status;
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
		try {

			LeaveType leavetype = getLeaveTypeById(leavetypeId);
			if (leavetype != null) {
				leavetype.setUpdateBy(null);
				leavetype.setDeleteFlag(true);
				updateLeaveType(leavetype);
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
		
		}
	}

}
