package com.prov.hrm.educationlevel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.bank.Bank;
import com.prov.hrm.employeebank.EmployeeBank;
import com.prov.hrm.employeeeducation.EmployeeEducation;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EducationlevelDAOImpl implements EducationlevelDAO {

	// Return all record from tbleducationlevel
	@SuppressWarnings("unchecked")
	@Override
	public List<EducationLevel> getAllEducationlevel(int countryId)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EducationLevel.class);
			criteria.add(Restrictions.eq("countryId", countryId));
			criteria.addOrder(Order.asc("educationLevel"));
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();			
		}
	}
	// Return a record from tbleducationlevel for specific educationlevel_id
		@Override
		public EducationLevel getEducationlevelById(int educationlevelId)throws HibernateException,ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				EducationLevel educationlevel = (EducationLevel) session.get(
						EducationLevel.class, educationlevelId);
				if (!educationlevel.getDeleteFlag()) {
					return educationlevel;
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
	/*// Insert a record into tbleducationlevel
	@Override
	public int addEducationlevel(EducationLevel educationlevel)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(EducationLevel.class);
			criteria.add(Restrictions.eq("educationLevel",educationlevel.getEducationLevel()));
			criteria.add(Restrictions.eq("countryId", educationlevel.getCountryId()));
			criteria.add(Restrictions.eq("deleteFlag",true));
			if(criteria.list().size()!=0){
				List<EducationLevel> edulevel=new ArrayList<EducationLevel>();
				edulevel=criteria.list();
				Iterator<EducationLevel> ite= edulevel.iterator();
				while(ite.hasNext()){
					EducationLevel education=ite.next();
					education.setDeleteFlag(false);
					education.setUpdateDate(date.toString());
					education.setInsertDate(date.toString());
					education.setInsertBy(educationlevel.getInsertBy());
					education.setUpdateBy(educationlevel.getUpdateBy());
			session.update(education);
			}
			}else{
			educationlevel.setInsertDate(date.toString());
			educationlevel.setUpdateDate(date.toString());
			session.save(educationlevel);
			}
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}*/

		// Insert a record into tbleducationlevel
		@Override
		public int addEducationlevel(EducationLevel educationlevel)throws HibernateException,ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status = 0;
			try {
				session.beginTransaction();
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				Criteria criteria=session.createCriteria(EducationLevel.class);
				criteria.add(Restrictions.eq("educationLevel",educationlevel.getEducationLevel()));
				criteria.add(Restrictions.eq("countryId",educationlevel.getCountryId()));
				if(criteria.list().size()!=0){
					List<EducationLevel> edlevel1=new ArrayList<EducationLevel>();
					edlevel1=criteria.list();
					Iterator<EducationLevel> ite= edlevel1.iterator();
					while(ite.hasNext()){
						EducationLevel edlevel2=ite.next();
						if(edlevel2.getDeleteFlag()==false){
							status=2;
						}else{
							edlevel2.setDeleteFlag(false);
							edlevel2.setUpdateDate(date.toString());
							edlevel2.setInsertDate(date.toString());
							edlevel2.setInsertBy(educationlevel.getInsertBy());
							edlevel2.setUpdateBy(educationlevel.getUpdateBy());
							session.update(edlevel2);
				status = 1;
				}}
				}
				else{
					educationlevel.setUpdateDate(date.toString());
					educationlevel.setInsertDate(date.toString());
					session.save(educationlevel);
					status = 1;
				}
				
			
				return status;
			} catch (Exception e) {
				e.printStackTrace();
				return status;
			} finally {
				session.getTransaction().commit();
				session.close();
			}
		}
		// Update a record in tbleducationlevel for specific educationlevel_id
		@Override
		public int updateEducationlevel(EducationLevel educationlevel)throws HibernateException,ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status = 0;
			try {
				session.beginTransaction();
				Criteria criteria=session.createCriteria(EducationLevel.class);
				criteria.add(Restrictions.eq("educationLevel",educationlevel.getEducationLevel()));
				criteria.add(Restrictions.eq("countryId", educationlevel.getCountryId()));
				criteria.add(Restrictions.eq("deleteFlag",false));
				criteria.add(Restrictions.ne("educationLevelId", educationlevel.getEducationLevelId()));
				if(criteria.list().size()==0)
				{
					session.clear();
				java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
				educationlevel.setUpdateDate(date.toString());
				EducationLevel educationlevelTime = getEducationlevelById(educationlevel.getEducationLevelId());
				educationlevel.setInsertDate(educationlevelTime.getInsertDate());
				session.update(educationlevel);
				status = 1;
				return status;
			} 
			
			else
			{
				return 5;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
				return status;
			} finally {
				session.getTransaction().commit();
				session.close();
			}
		}
		

		

	// Delete a record from tbleducationlevel for specificeducationlevel_id(SOFT DELETE)
	@Override
	public int deleteEducationlevel(int educationlevelId) {
		int status=0;
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			EducationLevel educationlevel = getEducationlevelById(educationlevelId);
			Criteria criteria = session.createCriteria(EmployeeEducation.class);
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.eq("educationLevel", new EducationLevel(educationlevel.getEducationLevelId())));
			System.out.println(criteria.list().size());
			if(criteria.list().size()!=0)
			{
				status=2;
				return status;
			}
			if (educationlevel != null &&  criteria.list().size()==0) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				educationlevel.setUpdateDate(date.toString());
				educationlevel.setUpdateBy(null);
				educationlevel.setDeleteFlag(true);
				session.update(educationlevel);
				status = 1;
				return status;
				
			}
			else {
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}finally{
			session.getTransaction().commit();
			session.close();
		}
	}
	// Delete a record from tbleducationlevel for specificeducationlevel_id(HARD DELETE)
	public int deleteEducationlevelHard(int educationlevelId)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EducationLevel educationlevel = (EducationLevel) session.get(EducationLevel.class, educationlevelId);
			session.delete(educationlevel);
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
