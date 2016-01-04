package com.prov.hrm.holiday;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.BetweenExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.holidaylist.HolidayList;
import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.utility.Getaccountyear;
import com.prov.hrm.utility.SessionFactoryUtil;

public class HolidayDAOImpl implements HolidayDAO {

	// Return all record from tblholiday
	

	
	public List<Holiday> getAllHoliday(int organizationId, int year) throws HibernateException,
		ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			List<Holiday> holiday = new ArrayList<Holiday>();
			try {
				session.beginTransaction();
				SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
					String fromDate= year+"-01-01";
					String toDate= year+"-12-31";
				Criteria criteria = session.createCriteria(Holiday.class);
					criteria.add(Restrictions.eq("organizationId", organizationId));
					criteria.add(Restrictions.eq("deleteFlag", false));
					criteria.add(Restrictions.between("holidayDate", fromDate, toDate));
					criteria.addOrder(Order.asc("holidayDate"));
					session.getTransaction().commit();
					Iterator<Holiday> ite = criteria.list().iterator();
				while (ite.hasNext()) {
					Holiday holidays = (Holiday) ite.next();
					Date dateFrom = sdinput.parse(holidays.getHolidayDate());
					holidays.setHolidayDate(sdfOut.format(dateFrom));
					holiday.add(holidays);
				}
	
				return holiday;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
	
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
		} finally {
			session.close();
		}
	}


	public List<Holiday> getHolidayByListName(int holidayListId,int organizationId,int year) throws HibernateException,
	ConstraintViolationException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			List<Holiday> holiday=new ArrayList<>(); 
			
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			String fromDate= year+"-01-01";
			String toDate= year+"-12-31";
			
			Criteria criteria=session.createCriteria(Holiday.class);
			criteria.add(Restrictions.eq("organizationId",organizationId));
			criteria.add(Restrictions.eq("holidayList",new HolidayList(holidayListId)));
			criteria.add(Restrictions.between("holidayDate", fromDate, toDate));
			criteria.add(Restrictions.eq("deleteFlag",false));
			Iterator<Holiday> iterator= criteria.list().iterator();
			session.getTransaction().commit();
			while(iterator.hasNext()){
				Holiday holiday1=iterator.next();
				Date holidayDate= sdinput.parse(holiday1.getHolidayDate());
				holiday1.setHolidayDate(sdfOut.format(holidayDate));
				holiday.add(holiday1);
			}
			return holiday;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			
			session.close();
		}
	
	}

	// Insert a record into tblholiday
	public int addHoliday(List<Holiday> holiday) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status =100;
		int i=1;
		try {

			session.beginTransaction();
			SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			
			List<Holiday> holiday4= new ArrayList<>();
			List<Holiday> holiday5= new ArrayList<>();
			
			for(Iterator<Holiday> ite=holiday.iterator();ite.hasNext();i++){
			Holiday holiday1= ite.next();
			
			Date hdate = indate.parse(holiday1.getHolidayDate());
			String holidate = outdate.format(hdate);
			Criteria criteria = session.createCriteria(Holiday.class);
			criteria.add(Restrictions.eq("holidayName", holiday1.getHolidayName()));
			criteria.add(Restrictions.eq("holidayDate", holidate));
			criteria.add(Restrictions.eq("holidayList", new HolidayList(holiday1.getHolidayList().getHolidayListId())));
			criteria.add(Restrictions.eq("organizationId",holiday1.getOrganizationId()));
	
			if (criteria.list().size() != 0) {
				
				for(Iterator<Holiday> iterator = criteria.list().iterator(); iterator.hasNext();)
				{
					
					Holiday holiday3 = iterator.next();
					if (holiday3.getDeleteFlag()==true) {
						
						holiday4.add(holiday3);
					}else if(holiday3.getDeleteFlag()==false){
						status=i;
						return status;
					}
				
				}
			} else {
				holiday5.add(holiday1);
			}
		
			if(holiday.size()==i){
		
						for(Iterator<Holiday> iterator1=holiday5.iterator();iterator1.hasNext();){
						Holiday holiday6= iterator1.next();
						Date fromDate = indate.parse(holiday6.getHolidayDate());
						holiday6.setOrganizationId(holiday6.getOrganizationId());
						holiday6.setHolidayDate(outdate.format(fromDate));
						holiday6.setInsertDate(date.toString());
						holiday6.setUpdateDate(date.toString());
						session.save(holiday6);
						status = 101;
				session.getTransaction().commit();
				session.beginTransaction();
					for(Iterator<Holiday> iterator2=holiday4.iterator();iterator2.hasNext();){
						Holiday holiday7=iterator2.next();
						holiday7.setDeleteFlag(false);
						holiday7.setUpdateDate(date.toString());
						holiday7.setInsertDate(date.toString());
						Date tod = indate.parse(holiday7.getHolidayDate());
						holiday7.setHolidayDate(outdate.format(tod));
						holiday7.setInsertBy(holiday7.getInsertBy());
						holiday7.setUpdateBy(holiday7.getUpdateBy());
						session.update(holiday7);
						status = 101;
						
					}
			}
				}
			}
			
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
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
			SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			Date holidayd = indate.parse(holiday.getHolidayDate());
			String holidaydate = outdate.format(holidayd);
			Criteria criteria = session.createCriteria(Holiday.class);
			criteria.add(Restrictions.and(
					Restrictions.eq("holidayName", holiday.getHolidayName()),
					Restrictions.eq("holidayDate", holidaydate)));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.ne("holidayId", holiday.getHolidayId()));
			criteria.add(Restrictions.eq("organizationId",
					holiday.getOrganizationId()));
			session.clear();
			Criteria criteria1 = session.createCriteria(EmployeeLeave.class);
			criteria1.add(Restrictions.eq("organizationId",
					holiday.getOrganizationId()));
			criteria1.add(Restrictions.eq("deleteFlag", false));
			criteria1.add(Restrictions.ge("fromDate", holidaydate));
			criteria1.add(Restrictions.ge("toDate", holidaydate));
			if (criteria1.list().size() == 0) {
				if (criteria.list().size() == 0) {
					session.clear();
					Holiday holiday1 = getHolidayById(holiday.getHolidayId());
					if (holiday1 != null) {
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
				} else {
					return 6;
				}
			} else {
				status = 5;
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
					SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
					Date fromDate = indate.parse(holiday.getHolidayDate());
					System.out.println(holiday.toString());
					Criteria criteria = session.createCriteria(EmployeeLeave.class);
					criteria.add(Restrictions.eq("organizationId", holiday.getOrganizationId()));
					criteria.add(Restrictions.eq("deleteFlag", false));
					criteria.add(Restrictions.eq("fromDate",holiday.getHolidayDate()));
					criteria.add(Restrictions.ge("toDate",holiday.getHolidayDate()));
					System.out.println(criteria.list().size());
					System.out.println(holiday.getHolidayDate());
					if(criteria.list().size()!=0)
					{
						status=2;
						return status;
					}
					if (holiday != null&&  criteria.list().size()==0) {
						holiday.setUpdateBy(null);
						holiday.setHolidayDate(outdate.format(fromDate));
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