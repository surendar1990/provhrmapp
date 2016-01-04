package com.prov.hrm.country;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.educationlevel.EducationLevel;
import com.prov.hrm.employeepersonal.EmployeePersonal;
import com.prov.hrm.employeevisa.EmployeeVisa;
import com.prov.hrm.stateprovince.StateProvince;
import com.prov.hrm.utility.SessionFactoryUtil;
import com.prov.hrm.visatype.VisaType;

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
			criteria.addOrder(Order.asc("country"));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();
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
			session.close();
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
				if(criteria.list().size()!=0){
					List<Country> country1=new ArrayList<Country>();
					country1=criteria.list();
					Iterator<Country> ite= country1.iterator();
					while(ite.hasNext()){
						Country country2=ite.next();
						if(country2.getDeleteFlag()==false)
						{
							status=2;
						}else{
						country2.setDeleteFlag(false);
						country2.setUpdateDate(date.toString());
						country2.setInsertDate(date.toString());
						country2.setInsertBy(country.getInsertBy());
						country2.setUpdateBy(country.getUpdateBy());
				session.update(country2);
				status = 1;
				}}
				}
				else{
					country.setUpdateDate(date.toString());
					country.setInsertDate(date.toString());
					session.save(country);
					status = 1;
				}
				
				return status;
			}
			catch (Exception e) {
				e.printStackTrace();
				return 0;
			} finally {
				session.getTransaction().commit();
				session.close();
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
				Criteria criteria=session.createCriteria(Country.class);
				criteria.add(Restrictions.eq("country",country.getCountry()));
				criteria.add(Restrictions.eq("deleteFlag",false));
				criteria.add(Restrictions.ne("countryId", country.getCountryId()));
				if(criteria.list().size()==0)
				{ 
					session.clear();
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
	}
				
				else{

					return 5;
					
				}

			} catch (Exception e) {
				e.printStackTrace();
				return status;
			} finally {
				session.getTransaction().commit();
				session.close();
			}
		}



	// Soft Delete a record from tblcountry for specific country_id
	public int deleteCountry(int countryid) {
			int status = 0;
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Country country = getCountryById(countryid);
			List<VisaType> country1=new ArrayList<>();
			List<EmployeePersonal> country2=new ArrayList<>();
			List<StateProvince> country3=new ArrayList<>();
			List<EducationLevel> country4=new ArrayList<>();
			List<EmployeePersonal> country5=new ArrayList<>();
			
			Criteria criteria= session.createCriteria(VisaType.class);
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.eq("countryId", countryid));
			country1= criteria.list();
			if(country1.size()==0){
			Criteria criteria1= session.createCriteria(EmployeePersonal.class);
			criteria1.add(Restrictions.eq("deleteFlag", false));
			criteria1.add(Restrictions.eq("permanentCountry",  new Country(country.getCountryId())));
			country2= criteria1.list();
			}else if(country2.size()==0){
			Criteria criteria2= session.createCriteria(StateProvince.class);
			criteria2.add(Restrictions.eq("deleteFlag", false));
			criteria2.add(Restrictions.eq("country",  new Country(country.getCountryId())));
			country3= criteria2.list();
			}else if(country3.size()==0){
			Criteria criteria3= session.createCriteria(EducationLevel.class);
			criteria3.add(Restrictions.eq("deleteFlag", false));
			criteria3.add(Restrictions.eq("countryId",  countryid));
			country4= criteria3.list();
			}else if(country4.size()==0){
			Criteria criteria4= session.createCriteria(EmployeePersonal.class);
			criteria4.add(Restrictions.eq("deleteFlag", false));
			criteria4.add(Restrictions.eq("currentCountry",  new Country(country.getCountryId())));
			country5= criteria4.list();
			}			
			if(country1.size()!=0 && country2.size()!=0 || country3.size()!=0 || country4.size()!=0 || country5.size()!=0)
			{
				status=2;
				
			}
			if (country != null &&  country1.size()==0 && country2.size()==0 && country3.size()==0 && country4.size()==0 && country5.size()==0) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				country.setUpdateDate(date.toString());
				country.setUpdateBy(null);
				country.setDeleteFlag(true);
				session.update(country);
				status = 1;
			
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally{
			session.getTransaction().commit();
			session.close();
			
		}
		return status;
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
			session.close();
		}
	}
}
