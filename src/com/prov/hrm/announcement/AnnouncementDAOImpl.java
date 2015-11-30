package com.prov.hrm.announcement;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.utility.SessionFactoryUtil;

public class AnnouncementDAOImpl implements AnnouncementDAO {

	// Return all record from tblannouncement
	@SuppressWarnings("unchecked")
	public List<Announcement> getAllAnnouncement(int organizationId)
			throws HibernateException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Announcement.class);
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

	// Return a record from tblannouncement for specific announcement_id
	public Announcement getAnnouncementById(int announcementid)
			throws HibernateException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Announcement announcement = (Announcement) session.get(
					Announcement.class, announcementid);
			if (announcement != null) {
				if (!announcement.getDeleteFlag()) {
					return announcement;
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

	// Insert a record into tblannouncement
	public int addAnnouncement(Announcement announcement)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			announcement.setUpdateDate(date.toString());
			announcement.setInsertDate(date.toString());
			session.save(announcement);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
		
		}
	}

	// Update a record in tblannouncement for specific announcement_id
	public int updateAnnouncement(Announcement announcement)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Announcement announce = getAnnouncementById(announcement
					.getAnnouncementId());
			if (announce != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				announcement.setUpdateDate(date.toString());
				announcement.setInsertBy(announce.getInsertBy());
				announcement.setInsertDate(announce.getInsertDate());
				session.update(announcement);
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

	// Soft Delete a record from tblannouncement for specific announcement_id
	public int deleteAnnouncement(int announcementId) {
		int status = 0;
		try {
			Announcement announcement = getAnnouncementById(announcementId);
			if (announcement != null) {
				announcement.setUpdateBy(null);
				announcement.setDeleteFlag(true);
				updateAnnouncement(announcement);
				status = 1;
				return status;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
	}

	// Hard Delete a record from tblannouncement for specific announcement_id
	public int deleteAnnouncementHard(int announcementId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Announcement announcement = getAnnouncementById(announcementId);
			session.delete(announcement);
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
