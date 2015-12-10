package com.prov.hrm.announcement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.utility.SessionFactoryUtil;

public class AnnouncementDAOImpl implements AnnouncementDAO {

	// Return all record from tblannouncement
	@SuppressWarnings("unchecked")
	public List<Announcement> getAllAnnouncement(int organizationId)
			throws HibernateException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		List<Announcement> announcement=new ArrayList<Announcement>();
		List<Announcement> announcements1=new ArrayList<Announcement>();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Announcement.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			announcement=criteria.list();
			session.getTransaction().commit();
			Iterator<Announcement> ite = announcement.iterator();
			
			
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			 
			 while(ite.hasNext()) {
				Announcement announce=(Announcement)ite.next();
				Date fromDate = sdinput.parse(announce.getFromDate());
				Date toDate = sdinput.parse(announce.getToDate());
				announce.setFromDate(sdfOut.format(fromDate));
				announce.setToDate(sdfOut.format(toDate));
				announcements1.add(announce);
			}
			return announcements1;	
			} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	// Return a record from tblannouncement for specific announcement_id
	public Announcement getAnnouncementById(int announcementid)
			throws HibernateException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Announcement announcement = (Announcement) session.get(	Announcement.class, announcementid);
			session.getTransaction().commit();
			if (announcement != null) {
				if (!announcement.getDeleteFlag()) {
					 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
					 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
					 Date formDate = sdinput.parse(announcement.getFromDate());
						Date toDate = sdinput.parse(announcement.getToDate());
						announcement.setFromDate(sdfOut.format(formDate));
						announcement.setToDate(sdfOut.format(toDate));
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
		} 
		}

	// Insert a record into tblannouncement
	public int addAnnouncement(Announcement announcement)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			 SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			announcement.setUpdateDate(date.toString());
			announcement.setInsertDate(date.toString());
			
			 Date formDate = indate.parse(announcement.getFromDate());
				Date toDate = indate.parse(announcement.getToDate());
				announcement.setFromDate(outdate.format(formDate));
				announcement.setToDate(outdate.format(toDate));
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
				 SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
				 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				announcement.setUpdateDate(date.toString());
				announcement.setInsertBy(announce.getInsertBy());
				announcement.setInsertDate(announce.getInsertDate());
				Date fromDate = indate.parse(announcement.getFromDate());
				Date toDate = indate.parse(announcement.getToDate());
				announcement.setFromDate(outdate.format(fromDate));
				announcement.setToDate(outdate.format(toDate));
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
