package com.prov.hrm.attendancelogfile;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.utility.SessionFactoryUtil;

public class AttendanceLogFileDAOImpl implements AttendanceLogFileDAO {

	// Return all record from tblattendancelogfile
	public List<AttendanceLogFile> getAllAttendanceLogFile(int organizationId) {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(AttendanceLogFile.class);
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

	// Return a record from tblattendancelogfile for specific
	// attendancelogfileid
	public AttendanceLogFile getAttendanceLogFileById(int attendanceLogFileId) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			AttendanceLogFile attendancelogfile = (AttendanceLogFile) session
					.get(AttendanceLogFile.class, attendanceLogFileId);
			if (!attendancelogfile.isDeleteFlag()) {
				return attendancelogfile;
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

	// Insert a record into tblattendancelogfile
	public int addAttendanceLogFile(AttendanceLogFile attendanceLogFile) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			attendanceLogFile.setUpdateDate(date.toString());
			attendanceLogFile.setInsertDate(date.toString());
			session.save(attendanceLogFile);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Update a record in tblattendancelogfile for specific attendancelogidfile
	public int updateAttendanceLogFile(AttendanceLogFile attendanceLogFile) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			attendanceLogFile.setUpdateDate(date.toString());
			session.update(attendanceLogFile);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Soft Delete a record from tblattendancelogfile for specific attendancelogfileid
	public int deleteAttendanceLogFile(int attendanceLogFileId) {
		int status = 0;
		try {
			AttendanceLogFile attendancelogfile = getAttendanceLogFileById(attendanceLogFileId);
			attendancelogfile.setUpdateBy(null);
			attendancelogfile.setDeleteFlag(true);
			updateAttendanceLogFile(attendancelogfile);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
	}

	// Hard Delete a record from tblattendancelogfile for specific attendancelogfileid
	public int deleteAttendanceLogFileHard(int attendanceLogFileId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			AttendanceLogFile attendancelogfile = getAttendanceLogFileById(attendanceLogFileId);
			session.delete(attendancelogfile);
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
