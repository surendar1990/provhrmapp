package com.prov.hrm.holiday;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.utility.SessionFactoryUtil;

public class HolidayDAOImpl implements HolidayDAO {

	// Return all record from tblholiday
	public List<Holiday> getAllHoliday(int organizationid)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Holiday.class);
			criteria.add(Restrictions.eq("organizationId", organizationid));
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();

		}

	}

	// Return a record from tblholiday for specific holiday_id
	public Holiday getHolidayById(int holidayId) throws HibernateException,
			ConstraintViolationException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Holiday holiday = (Holiday) session.get(Holiday.class, holidayId);
			if (holiday != null) {
				if (!holiday.getDeleteFlag()) {
					return holiday;
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

	// Insert a record into tblholiday
	public int addHoliday(Holiday holiday) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			holiday.setInsertDate(date.toString());
			holiday.setUpdateDate(date.toString());
			session.save(holiday);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();

		}
	}

	// Update a record in tblholiday for specific holiday_id
	public int updateHoliday(Holiday holiday) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();

			Holiday holiday1 = getHolidayById(holiday.getHolidayId());
			if (holiday1 != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				holiday.setUpdateDate(date.toString());
				holiday.setInsertBy(holiday1.getInsertBy());
				holiday.setInsertDate(holiday1.getInsertDate());
				session.update(holiday);
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

	// Delete a record from tblholiday for specific holiday_id
	public int deleteHoliday(int holidayId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Holiday holiday = getHolidayById(holidayId);
			if (holiday != null) {
				holiday.setUpdateBy(null);
				holiday.setDeleteFlag(true);
				session.update(holiday);

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

}
