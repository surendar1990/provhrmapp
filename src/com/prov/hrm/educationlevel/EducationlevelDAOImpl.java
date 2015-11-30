package com.prov.hrm.educationlevel;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

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
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
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
				
			}
		}
	// Insert a record into tbleducationlevel
	@Override
	public int addEducationlevel(EducationLevel educationlevel)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			educationlevel.setInsertDate(date.toString());
			educationlevel.setUpdateDate(date.toString());
			session.save(educationlevel);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Update a record in tbleducationlevel for specific educationlevel_id
	@Override
	public int updateEducationlevel(EducationLevel educationlevel)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			educationlevel.setUpdateDate(date.toString());
			EducationLevel educationlevelTime = getEducationlevelById(educationlevel.getEducationLevelId());
			educationlevel.setInsertDate(educationlevelTime.getInsertDate());
			session.update(educationlevel);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}
		

	// Delete a record from tbleducationlevel for specificeducationlevel_id(SOFT DELETE)
	@Override
	public int deleteEducationlevel(int educationlevelId) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EducationLevel educationlevel = getEducationlevelById(educationlevelId);
			if(educationlevel!=null)
			{
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			educationlevel.setUpdateDate(date.toString());
			educationlevel.setUpdateBy(null);
			educationlevel.setDeleteFlag(true);
			updateEducationlevel(educationlevel);
			status = 1;
			return status;
			}
			else
			{
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
		finally {
			session.getTransaction().commit();
			
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
			
		}
	}

	
}
