package com.prov.hrm.holiday;

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

import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.utility.SessionFactoryUtil;

public class HolidayDAOImpl implements HolidayDAO {

	// Return all record from tblholiday
	public List<Holiday> getAllHoliday(int organizationid)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			List< Holiday> holiday=new ArrayList<Holiday>();
			List<Holiday> holiday1=new ArrayList<Holiday>();
			
			Criteria criteria = session.createCriteria(Holiday.class);
			criteria.add(Restrictions.eq("organizationId", organizationid));
			criteria.add(Restrictions.eq("deleteFlag", false));
			holiday=criteria.list();
			session.getTransaction().commit();
			Iterator<Holiday> iterator = holiday.iterator();
			 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			while(iterator.hasNext()) {
				Holiday holidays=(Holiday)iterator.next();
				Date Date = sdinput.parse(holidays.getHolidayDate());
				holidays.setHolidayDate(sdfOut.format(Date));
				holiday1.add(holidays);
			}
			return holiday;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			session.close();
		}

	}

	// Return a record from tblholiday for specific holiday_id
	public Holiday getHolidayById(int holidayId) throws HibernateException,
			ConstraintViolationException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			Holiday holiday = (Holiday) session.get(Holiday.class, holidayId);
			session.getTransaction().commit();
			if (holiday != null) {
				if (!holiday.getDeleteFlag()) {
					Date Date = sdinput.parse(holiday.getHolidayDate());
					holiday.setHolidayDate(sdfOut.format(Date));
						
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
		} finally{
			session.close();
		}
	}

	// Insert a record into tblholiday
	public int addHoliday(Holiday holiday) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Date fromDate = indate.parse(holiday.getHolidayDate());
			holiday.setHolidayDate(outdate.format(fromDate));
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
			session.close();

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
				SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				Date fromDate = indate.parse(holiday.getHolidayDate());
				holiday.setHolidayDate(outdate.format(fromDate));
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
			session.clear();
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
			session.close();

		}
	}

}
