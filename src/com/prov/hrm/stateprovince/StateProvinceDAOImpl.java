package com.prov.hrm.stateprovince;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.country.Country;
import com.prov.hrm.educationlevel.EducationLevel;
import com.prov.hrm.employeepersonal.EmployeePersonal;
import com.prov.hrm.utility.SessionFactoryUtil;
import com.prov.hrm.visatype.VisaType;

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
			criteria.addOrder(Order.asc("stateprovince"));
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
			session.close();
		}
	}

	/*@Override
	public int addState(StateProvince stateprovince) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(StateProvince.class);
			criteria.add(Restrictions.eq("stateprovince",stateprovince.getStateprovince()));
			criteria.add(Restrictions.eq("country",stateprovince.getCountry()));
			criteria.add(Restrictions.eq("deleteFlag",true));
			if(criteria.list().size()!=0){
				List<StateProvince> state1=new ArrayList<StateProvince>();
				state1=criteria.list();
				Iterator<StateProvince> ite= state1.iterator();
				while(ite.hasNext()){
					StateProvince state2=ite.next();
					state2.setDeleteFlag(false);
					state2.setUpdateDate(date.toString());
					state2.setInsertDate(date.toString());
					state2.setInsertBy(stateprovince.getInsertBy());
					state2.setUpdateBy(stateprovince.getUpdateBy());
			session.update(state2);
			}
			}
			else{
				stateprovince.setUpdateDate(date.toString());
				stateprovince.setInsertDate(date.toString());
				session.save(stateprovince);
			}
		
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}
	}*/
	
	@Override
	public int addState(StateProvince stateprovince) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(StateProvince.class);
			criteria.add(Restrictions.eq("stateprovince",stateprovince.getStateprovince()));
			criteria.add(Restrictions.eq("country",new Country(stateprovince.getCountry().getCountryId())));
			if(criteria.list().size()!=0){
				List<StateProvince> state1=new ArrayList<StateProvince>();
				state1=criteria.list();
				Iterator<StateProvince> ite= state1.iterator();
				while(ite.hasNext()){
					StateProvince state2=ite.next();
					if(state2.getDeleteFlag()==false){
						status=2;
					}else{
						state2.setDeleteFlag(false);
						state2.setUpdateDate(date.toString());
						state2.setInsertDate(date.toString());
						state2.setInsertBy(stateprovince.getInsertBy());
						state2.setUpdateBy(stateprovince.getUpdateBy());
						session.update(state2);
			status = 1;
			}}
			}
			else{
				stateprovince.setUpdateDate(date.toString());
				stateprovince.setInsertDate(date.toString());
				session.save(stateprovince);
				status = 1;
			}
			
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	@Override
	public int updateState(StateProvince stateprovince)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Criteria criteria=session.createCriteria(StateProvince.class);
			criteria.add(Restrictions.eq("stateprovince",stateprovince.getStateprovince()));
			criteria.add(Restrictions.eq("country",stateprovince.getCountry()));
			criteria.add(Restrictions.ne("stateprovinceId", stateprovince.getStateprovinceId()));
			criteria.add(Restrictions.eq("deleteFlag",false));
			if(criteria.list().size()==0){
				session.clear();
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
			}
			else
			{
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

	@Override
	public int deleteState(int stateId) throws HibernateException,
			ConstraintViolationException {
		
		int status = 0;
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			StateProvince state = getStateById(stateId);
			List<EmployeePersonal> state1=new ArrayList<>();
			List<EmployeePersonal> state2=new ArrayList<>();
			
			
			
			Criteria criteria= session.createCriteria(EmployeePersonal.class);
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.eq("currentStateprovince",  new StateProvince(state.getStateprovinceId())));
			state1= criteria.list();
			if(state1.size()==0){
			Criteria criteria1= session.createCriteria(EmployeePersonal.class);
			criteria1.add(Restrictions.eq("deleteFlag", false));
			criteria1.add(Restrictions.eq("permanentStateprovince",  new StateProvince(state.getStateprovinceId())));
			state2= criteria1.list();
			}		
			if(state1.size()!=0 || state2.size()!=0)
			{
				status=2;
				return status;
			}
			if (state != null &&  state1.size()==0 && state2.size()==0) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				state.setUpdateDate(date.toString());
				state.setUpdateBy(null);
				state.setDeleteFlag(true);
				session.update(state);
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

}
