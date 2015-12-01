package com.prov.hrm.leavetype;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.prov.hrm.idtype.Idtype;
import com.prov.hrm.utility.SessionFactoryUtil;

public class LeaveTypeDAOImpl implements LeaveTypeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveType> getAllLeaveType(int organizationId,String fromDate,String toDate)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(LeaveType.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.between("fromDate", fromDate, toDate));
			criteria.add(Restrictions.between("toDate", fromDate, toDate));
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
					System.out.println(leavetype);
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
			
		
		}
	}

	@Override
	public int addLeaveType(LeaveType leavetype)throws HibernateException,
	ConstraintViolationException,MySQLIntegrityConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			session.setFlushMode(FlushMode.COMMIT);
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			leavetype.setInsertDate(date.toString());
			leavetype.setUpdateDate(date.toString());
			session.save(leavetype);
			status = 1;
			session.getTransaction().commit();
			return status;

		}

		catch (ConstraintViolationException e) {
			System.out.println(e.getMessage());
			throw new ConstraintViolationException(e.getMessage(), e.getSQLException(),e.getCause().getMessage());
		
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
				leavetype.setUpdateDate(date.toString());
				leavetype.setInsertDate(leave.getInsertDate());
				leavetype.setInsertBy(leave.getInsertBy());
				session.update(leavetype);
				status = 1;
				return status;
			} else {
				return status;
			}

		} 
		catch (ConstraintViolationException e) {
			System.out.println(e.getMessage());
			throw new ConstraintViolationException(e.getMessage(), e.getSQLException(),e.getCause().getMessage());
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();

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
