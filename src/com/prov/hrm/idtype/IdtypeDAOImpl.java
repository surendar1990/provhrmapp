package com.prov.hrm.idtype;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.utility.SessionFactoryUtil;

public class IdtypeDAOImpl implements IdtypeDAO {

	// Return all record from tblidtype
	@SuppressWarnings("unchecked")
	@Override
	public List<Idtype> getAllIdtype(int organizationid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Idtype.class);
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

	// Return a record from tblidtype for specific idtype_id
	public Idtype getIdtypeById(int idtypeId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Idtype idtype = (Idtype) session.get(Idtype.class, idtypeId);
			if (idtype != null) {
				if (!idtype.isDeleteFlag()) {
					return idtype;
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

	// Insert a record into tblidtype
	@Override
	public int addIdtype(Idtype idtype) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			idtype.setInsertDate(date.toString());
			idtype.setUpdateDate(date.toString());
			session.save(idtype);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Update a record in tblidtype for specific idtype_id
	@Override
	public int updateIdtype(Idtype idtype) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Idtype idt = getIdtypeById(idtype.getIdtypeId());
			if (idt != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				idtype.setUpdateDate(date.toString());
				idtype.setInsertBy(idt.getInsertBy());
				idtype.setInsertDate(idt.getInsertDate());
				session.update(idtype);
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

	// Delete a record from tblidtype for specific idtype_id
	@Override
	public int deleteIdtype(int idtypeId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Idtype idtype = getIdtypeById(idtypeId);
			if (idtype != null) {
				idtype.setUpdateBy(null);
				idtype.setDeleteFlag(true);
				session.update(idtype);
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

}
