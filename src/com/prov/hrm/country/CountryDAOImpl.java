package com.prov.hrm.country;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.utility.SessionFactoryUtil;

public class CountryDAOImpl implements CountryDAO {

	// Return all record from tblcountry
	@Override
	public List<Country> getAllCountry() throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Country.class);
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
		}

	}

	// Return a record from tblcountry for specific country_id
	@Override
	public Country getCountryById(int countryid) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Country country = (Country) session.get(Country.class, countryid);
			if (country != null) {
				if (!country.getDeleteFlag()) {
					return country;
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

	// Insert a record into tblcountry
	@Override
	public int addCountry(Country country) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(Country.class);
			criteria.add(Restrictions.eq("country",country.getCountry()));
			criteria.add(Restrictions.eq("deleteFlag",true));
			if(criteria.list().size()!=0){
				List<Country> country1=new ArrayList<Country>();
				country1=criteria.list();
				Iterator<Country> ite= country1.iterator();
				while(ite.hasNext()){
					Country country2=ite.next();
					country2.setDeleteFlag(false);
					country2.setUpdateDate(date.toString());
					country2.setInsertDate(date.toString());
					country2.setInsertBy(country.getInsertBy());
					country2.setUpdateBy(country.getUpdateBy());
			session.update(country2);
			}
			}
			else{
				country.setUpdateDate(date.toString());
				country.setInsertDate(date.toString());
				session.save(country);
			}
			status = 1;
			return status;
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Update a record in tblcountry for specific country_id
	@Override
	public int updateCountry(Country country) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			country.setUpdateDate(date.toString());
			Country country1 = getCountryById(country.getCountryId());
			if (country1 != null) {
				country.setInsertBy(country1.getInsertBy());
				country.setInsertDate(country1.getInsertDate());
				session.update(country);
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

	// Soft Delete a record from tblcountry for specific country_id
	public int deleteCountry(int countryid) {
		int status = 0;
		try {
			Country country = getCountryById(countryid);
			if (country != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				country.setUpdateDate(date.toString());
				country.setUpdateBy(null);
				country.setDeleteFlag(true);
				updateCountry(country);
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

	// Hard Delete a record from tblcountry for specific country_id
	public int deleteCountryHard(int countryid) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Country country = getCountryById(countryid);
			session.delete(country);
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
