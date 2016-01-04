package com.prov.hrm.holidaylist;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.holiday.Holiday;
import com.prov.hrm.utility.SessionFactoryUtil;

public class HolidayListDAOImpl implements HolidayListDAO{

	@Override
	public List<HolidayList> getAllHolidayList(int organizationId)throws HibernateException,
	ConstraintViolationException{
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Criteria criteria=session.createCriteria(HolidayList.class);
			criteria.add(Restrictions.eq("organizationId",organizationId));
			criteria.add(Restrictions.eq("deleteFlag",false));
			
			System.out.println(criteria.list().size());
			return criteria.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.getTransaction().commit();
			session.close();
		}
		
	}

	@Override
	public HolidayList getHolidayListById(int holidaylistId) {
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			 HolidayList holidaylist=(HolidayList) session.get(HolidayList.class,holidaylistId);
			
			 return holidaylist;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.getTransaction().commit();
			session.close();
		}
		
	}

	@Override
	public int addHolidayList(HolidayList holidaylist) {
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		int status=0;
		try{
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(HolidayList.class);
			criteria.add(Restrictions.eq("holidayListName", holidaylist.getHolidayListName()));
			criteria.add(Restrictions.eq("organizationId",holidaylist.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag", false));
			if(criteria.list().size()==0){
			System.out.println(holidaylist);
			holidaylist.setInsertDate(date.toString());
			holidaylist.setUpdateDate(date.toString());
			session.save(holidaylist);
			status=1;
			
			}else{
				return 2;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();
		}
		return status;
	}

	@Override
	public int updateHolidayList(HolidayList holidaylist) {
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		int status=0;
		try{
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(HolidayList.class);
			criteria.add(Restrictions.ne("holidayListId", holidaylist.getHolidayListId()));
			criteria.add(Restrictions.eq("holidayListName", holidaylist.getHolidayListName()));
			criteria.add(Restrictions.eq("organizationId",holidaylist.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag", false));
			if(criteria.list().size()==0){
			holidaylist.setInsertDate(date.toString());
			holidaylist.setUpdateDate(date.toString());
			session.update(holidaylist);
			status=1;
			}else{
				return 2;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();
		}
		return status;
	}
	

	@Override
	public int deleteHolidayList(int holidaylistId) {
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		int status=0;
		try{
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			List<HolidayList> holidaylist1=new ArrayList<>();
			List<HolidayList> holidaylist2=new ArrayList<>();
			
			HolidayList holidaylist= getHolidayListById(holidaylistId);
			
			Criteria criteria=session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("holidayList", new HolidayList(holidaylist.getHolidayListId())));
			criteria.add(Restrictions.eq("organizationId",holidaylist.getOrganizationId()));
			holidaylist1=criteria.list();
			if(holidaylist1.size()==0){
				Criteria criteria1=session.createCriteria(Holiday.class);
				criteria1.add(Restrictions.eq("holidayList", new HolidayList(holidaylist.getHolidayListId())));
				criteria1.add(Restrictions.eq("organizationId",holidaylist.getOrganizationId()));
				holidaylist2=criteria.list();
			}
			if((holidaylist!=null) &&(holidaylist1.size()==0)&&(holidaylist2.size()==0) )
			{
				holidaylist.setDeleteFlag(true);
				holidaylist.setUpdateDate(date.toString());
				session.update(holidaylist);
				status=1;
				
			}else{
				return 2;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();
		}
		return status;
	}	
	}

