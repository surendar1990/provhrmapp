package com.prov.hrm.visatype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employeeidproof.EmployeeIdproof;
import com.prov.hrm.employeevisa.EmployeeVisa;
import com.prov.hrm.idtype.Idtype;
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
			criteria.addOrder(Order.asc("visaType"));
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

	/*// Insert a record into tblvisatype
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
*/
	
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
				Criteria criteria=session.createCriteria(VisaType.class);
				criteria.add(Restrictions.eq("visaType",visatype.getVisaType()));
				criteria.add(Restrictions.eq("organizationId",visatype.getOrganizationId()));
				criteria.add(Restrictions.eq("countryId",visatype.getCountryId()));
				if(criteria.list().size()!=0){
					List<VisaType> vtype1=new ArrayList<VisaType>();
					vtype1=criteria.list();
					Iterator<VisaType> ite= vtype1.iterator();
					while(ite.hasNext()){
						VisaType vtype2=ite.next();
						if(vtype2.isDeleteFlag()==false){
							status=2;
						}else{
							vtype2.setDeleteFlag(false);
							vtype2.setUpdateDate(date.toString());
							vtype2.setInsertDate(date.toString());
							vtype2.setInsertBy(visatype.getInsertBy());
							vtype2.setUpdateBy(visatype.getUpdateBy());
							session.update(vtype2);
				status = 1;
				}}
				}
				else{
					visatype.setUpdateDate(date.toString());
					visatype.setInsertDate(date.toString());
					session.save(visatype);
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

	// Update a record in tblvisatype for specific visatypeId
	@Override
	public int updateVisaType(VisaType visatype) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Criteria criteria=session.createCriteria(VisaType.class);
			criteria.add(Restrictions.eq("visaType",visatype.getVisaType()));
			criteria.add(Restrictions.eq("organizationId",visatype.getOrganizationId()));
			criteria.add(Restrictions.eq("countryId",visatype.getCountryId()));
			criteria.add(Restrictions.eq("deleteFlag",false));
			criteria.add(Restrictions.ne("visaTypeId", visatype.getVisaTypeId()));
			if(criteria.list().size()==0)
			{
				session.clear();
				java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
				visatype.setUpdateDate(date.toString());
				VisaType visatypeTime = getVisaTypeById(visatype.getVisaTypeId());
					if(visatypeTime != null)
					{
						visatype.setInsertBy(visatypeTime.getInsertBy());
						visatype.setInsertDate(visatypeTime.getInsertDate());
						session.update(visatype);
						status = 1;
						return status;
					} 
					else 
					{
						return status;
					}
			}
	
	else{

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


	// soft delete
	@Override
	public int deleteVisaType(int visatypeId) {
			int status = 0;
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			VisaType visatype = getVisaTypeById(visatypeId);
						
			Criteria criteria= session.createCriteria(EmployeeVisa.class);
			criteria.add(Restrictions.eq("organizationId",visatype.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.eq("visaType",  new VisaType(visatype.getVisaTypeId())));
			List<VisaType> visatypelist=new ArrayList<>();
			visatypelist= criteria.list();
			if(visatypelist.size()!=0)
			{
				status=2;
				return status;
			}
			if (visatype != null &&  visatypelist.size()==0) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				visatype.setUpdateDate(date.toString());
				visatype.setUpdateBy(null);
				visatype.setDeleteFlag(true);
				updateVisaType(visatype);
				status = 1;
				return status;
				
			}
			else {
				return status;
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
			session.close();
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
			session.close();
		}
	}

}
