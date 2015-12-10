package com.prov.hrm.organization;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.login.Login;
import com.prov.hrm.utility.SessionFactoryUtil;

public class OrganizationDAOImpl implements OrganizationDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Organization> getAllOrganization() throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Organization.class);
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	@Override
	public Organization getOrganizationById(int organizationId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Organization organization = (Organization) session.get(
					Organization.class, organizationId);
			if (organization != null) {
				if (!organization.getDeleteFlag()) {
					return organization;
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

	@Override
	public int addOrganization(Organization organization)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			organization.setInsertDate(date.toString());
			organization.setUpdateDate(date.toString());
			Login login=new Login();
			login.setOrganization(organization);
			login.setLoginName(organization.getOrganizationEmail());
			login.setLoginPassword("Admin@123");
			Set<Login> set=new HashSet<Login>();
			set.add(login);
			organization.setLogin(set);
			session.save(organization);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	@Override
	public int updateOrganization(Organization organization)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {

			session.beginTransaction();
			Organization org = getOrganizationById(organization
					.getOrganizationId());
			if (org != null) {
				java.sql.Timestamp date = new Timestamp(
						new java.util.Date().getTime());
				org.setInsertDate(org.getInsertDate());
				org.setInsertBy(org.getInsertBy());
				org.setUpdateDate(date.toString());
				session.update(organization);
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

	@Override
	// Soft Delete a record from tblannouncement for specific announcement_id
		public int deleteOrganization(int organizationId) {
			int status = 0;
			try {
				Organization organization = getOrganizationById(organizationId);
				if (organization != null) {
					organization.setUpdateBy(null);
					organization.setDeleteFlag(true);
					updateOrganization(organization);
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

	
	public int deleteOrganizationHard(int organizationId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Organization organization = getOrganizationById(organizationId);
			if (organization != null) {
				organization.setDeleteFlag(true);
				organization.setUpdateBy(null);
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
