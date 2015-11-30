package com.prov.hrm.visatype;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.utility.SessionFactoryUtil;

public class VisaTypeDAOImpl implements VisaTypeDAO {

	// Return all record from tblbank
	@SuppressWarnings("unchecked")
	@Override
	public List<VisaType> getAllVisaType(int countryId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(VisaType.class);
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

	// Insert a record into tblvisatype
	@Override
	public int addVisaType(VisaType visatype) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			visatype.setInsertDate(date.toString());
			visatype.setUpdateDate(date.toString());
			session.save(visatype);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Update a record in tblvisatype for specific visatypeId
	@Override
	public int updateVisaType(VisaType visatype) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			visatype.setUpdateDate(date.toString());
			VisaType visatypeTime = getVisaTypeById(visatype.getVisaTypeId());
			visatype.setInsertDate(visatypeTime.getInsertDate());
			session.update(visatype);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// soft delete
	@Override
	public int deleteVisaType(int visatypeId) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			VisaType visatype = getVisaTypeById(visatypeId);
			if (visatype != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				visatype.setUpdateDate(date.toString());
				visatype.setUpdateBy(null);
				visatype.setDeleteFlag(true);
				updateVisaType(visatype);
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

	// hard delete
	public int deleteVisaTypehard(int visatypeId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			VisaType visatype = (VisaType) session.get(VisaType.class,
					visatypeId);
			session.delete(visatype);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
		
		}
	}

	// Return a record from tblvisatype for specific visatypeId
	@Override
	public VisaType getVisaTypeById(int visatypeId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			VisaType visatype = (VisaType) session.get(VisaType.class,
					visatypeId);
			if (!visatype.isDeleteFlag()) {
				return visatype;
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

}
