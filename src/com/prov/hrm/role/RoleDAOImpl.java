package com.prov.hrm.role;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.utility.SessionFactoryUtil;

public class RoleDAOImpl implements RoleDAO {
	public SessionFactoryUtil sessionfactory = null;

	// Return all the records from tblrole
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAllRole(int organizationId) throws HibernateException,
			ConstraintViolationException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Role.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.addOrder(Order.asc("role"));
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Get the Role By using role_id in tblrole
	@Override
	public Role getRoleById(int roleId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Role role = (Role) session.get(Role.class, roleId);
			if (role != null) {
				if (!role.getDeleteFlag()) {
					return role;
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

	// Insert the new record into the tblrole
	@Override
	public int addRole(Role role) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			role.setInsertDate(date.toString());
			role.setUpdateDate(date.toString());
			session.save(role);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}

	}

	// Update a record in tblrole for specific role_id
	@Override
	public int updateRole(Role role) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Role role1 = getRoleById(role.getRoleId());
			if (role1 != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				role.setUpdateDate(date.toString());
				session.update(role);
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

	public int deleteRole(int roleId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Role role = getRoleById(roleId);
			if (role != null) {
				role.setDeleteFlag(true);
				role.setUpdateBy(null);
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

	// Delete a record in tblrole for specific role_id

	public int deleteRoleHard(int roleId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Role role = (Role) session.get(Role.class, roleId);
			session.delete(role);
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
