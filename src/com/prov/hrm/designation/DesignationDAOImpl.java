package com.prov.hrm.designation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.utility.SessionFactoryUtil;

public class DesignationDAOImpl implements DesignationDAO {
	public SessionFactoryUtil sessionfactory = null;

	// Return all record from tbldesignation
	@SuppressWarnings("unchecked")
	public List<Designation> getAllDesignation(int organizationId)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Designation.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Return a record from tbldesignation for specific designation_id
	public Designation getDesignationById(int designationId)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			Designation designation =(Designation) session.get(Designation.class, designationId); 
			if(!designation.getDeleteFlag())
			{
			return designation;
			}
			else
			{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
		
		}
	}

	// Insert a record into tbldesignation
	public int addDesignation(Designation designation)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status=0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			designation.setInsertDate(date.toString());
			designation.setUpdateDate(date.toString());
			session.save(designation);
			status=1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}

	}

	// Update a record in tbldesignation for specific designation_id
	public int updateDesignation(Designation designation)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			designation.setUpdateDate(date.toString());
			Designation designationTime = getDesignationById(designation.getDesignationId());
			designation.setInsertDate(designationTime.getInsertDate());
			session.update(designation);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}
	//soft delete
		@Override
		public int deleteDesignation(int designationId) {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status = 0;
			try {
				session.beginTransaction();
				Designation designation =getDesignationById(designationId);
				if(designation!=null)
				{
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				designation.setUpdateDate(date.toString());
				designation.setUpdateBy(null);
				designation.setDeleteFlag(true);
				updateDesignation(designation);
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
	//hard Delete
	public int deleteDesignationHard(int designation_id)throws HibernateException,ConstraintViolationException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Designation designationmaster = (Designation) session.get(
					Designation.class, designation_id);
			session.delete(designationmaster);
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
