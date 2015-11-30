package com.prov.hrm.stateprovince;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.country.Country;
import com.prov.hrm.utility.SessionFactoryUtil;

public class StateProvinceDAOImpl implements StateProvinceDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<StateProvince> getAllState(Country country)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(StateProvince.class);
			criteria.add(Restrictions.eq("country", country));
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
	public StateProvince getStateById(int stateId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			StateProvince stateprovince = (StateProvince) session.get(
					StateProvince.class, stateId);
			if (!stateprovince.getDeleteFlag()) {
				return stateprovince;
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
	public int addState(StateProvince stateprovince) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			stateprovince.setInsertDate(date.toString());
			stateprovince.setUpdateDate(date.toString());
			session.save(stateprovince);
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
	public int updateState(StateProvince stateprovince)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			stateprovince.setUpdateDate(date.toString());
			StateProvince state = getStateById(stateprovince
					.getStateprovinceId());
			if (state != null) {
				stateprovince.setInsertBy(state.getInsertBy());
				stateprovince.setInsertDate(state.getInsertDate());
				session.update(stateprovince);
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
	public int deleteState(int stateId) throws HibernateException,
			ConstraintViolationException {
		
		int status = 0;
		try {
			
			StateProvince stateprovince = getStateById(stateId);
			stateprovince.setUpdateBy(null);
			stateprovince.setDeleteFlag(true);
			updateState(stateprovince);
				status = 1;
				return status;
			
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}

	}

}
