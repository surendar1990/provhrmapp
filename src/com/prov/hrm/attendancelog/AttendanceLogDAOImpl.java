package com.prov.hrm.attendancelog;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.utility.SessionFactoryUtil;

public class AttendanceLogDAOImpl implements AttendanceLogDAO {

	// Return all record from tblattendancelog
	@SuppressWarnings("unchecked")
	public List<AttendanceLog> getAllAttendanceLog(int organizationId)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(AttendanceLog.class);
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

	// Return a record from tblattendancelog for specific attendancelogid
	public AttendanceLog getAttendanceLogById(int attendanceLogId)
			throws HibernateException, ConstraintViolationException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			AttendanceLog attendancelog = (AttendanceLog) session.get(
					AttendanceLog.class, attendanceLogId);
			if (attendancelog != null) {
				if (!attendancelog.isDeleteFlag()) {
					return attendancelog;
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

	// Insert a record into tblattendancelog
	public int addAttendanceLog(AttendanceLog attendanceLog)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			attendanceLog.setUpdateDate(date.toString());
			attendanceLog.setInsertDate(date.toString());
			session.save(attendanceLog);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();

		}
	}

	// Update a record in tblattendancelog for specific attendancelogid
	public int updateAttendanceLog(AttendanceLog attendanceLog)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			AttendanceLog attendance = getAttendanceLogById(attendanceLog
					.getAttendancelogId());
			if (attendance != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				attendanceLog.setInsertBy(attendance.getInsertBy());
				attendanceLog.setInsertDate(attendance.getInsertDate());
				attendanceLog.setUpdateDate(date.toString());
				session.update(attendanceLog);
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

		}
	}

	// Soft Delete a record from tblattendancelog for specific attendancelogid
	public int deleteAttendanceLog(int attendancelogId) {
		int status = 0;
		try {
			AttendanceLog attendancelog = getAttendanceLogById(attendancelogId);
			attendancelog.setUpdateBy(null);
			attendancelog.setDeleteFlag(true);
			updateAttendanceLog(attendancelog);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
	}

	// Hard Delete a record from tblattendancelog for specific attendancelogid
	public int deleteAttendanceLogHard(int attendancelogId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			AttendanceLog attendancelog = getAttendanceLogById(attendancelogId);
			session.delete(attendancelog);
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
