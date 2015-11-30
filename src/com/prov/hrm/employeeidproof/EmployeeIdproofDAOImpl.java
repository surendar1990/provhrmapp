package com.prov.hrm.employeeidproof;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeIdproofDAOImpl implements EmployeeIdproofDAO {

	// Return all record from tblempidproof
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeIdproof> getAllIdProof(int organizationid)
			throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeIdproof.class);
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

	// Insert a record into tblempidproof
	@Override
	public int addIdProof(EmployeeIdproof idproof)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			idproof.setInsertDate(date.toString());
			idproof.setUpdateDate(date.toString());
			session.save(idproof);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
		
		}
	}

	// Update a record in tblempidproof for specific Empidproof_id
	@Override
	public int updateIdProof(EmployeeIdproof idproof)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			idproof.setUpdateDate(date.toString());
			EmployeeIdproofDAOImpl idproofdaoimpl = new EmployeeIdproofDAOImpl();
			EmployeeIdproof idp = idproofdaoimpl.getIdProofById(idproof
					.getEmpidproofId());
			idproof.setInsertDate(idp.getInsertDate());
			session.update(idproof);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Delete a record from tblempidproof for specific Empidproof_id(SOFT DELETE)
	@Override
	public int deleteIdProof(int idproofId)throws HibernateException,ConstraintViolationException 
	{
		int status = 0;
		try {
			EmployeeIdproof idproof = getIdProofById(idproofId);
			if (idproof != null) {
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
				idproof.setUpdateDate(date.toString());
				idproof.setUpdateBy(null);
				idproof.setDeleteFlag(true);
				updateIdProof(idproof);
			status = 1;
			return status;
			}else{
			return status;
		} 
		}catch (Exception e) {
			e.printStackTrace();
			return status;
		} 

	}

	// Delete a record from tblempidproof for specific Empidproof_id(HARDDELETE)
	 
	public int deleteIdProofhard(int idproofId)throws HibernateException,ConstraintViolationException 
	{ 
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeIdproof idproof = (EmployeeIdproof) session.get(
					EmployeeIdproof.class, idproofId);
			session.delete(idproof);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Return a record from tblempidproof for specific Empidproof_id
	@Override
	public EmployeeIdproof getIdProofById(int idproofId)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeIdproof idproof = (EmployeeIdproof) session.get(
					EmployeeIdproof.class, idproofId);
			if (!idproof.isDeleteFlag()) {
				return idproof;
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
