package com.prov.hrm.idtype;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.department.Department;
import com.prov.hrm.designation.Designation;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeidproof.EmployeeIdproof;
import com.prov.hrm.role.Role;
import com.prov.hrm.stateprovince.StateProvince;
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
			criteria.addOrder(Order.asc("idtypeName"));
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
			session.close();
		}
	}

	/*// Insert a record into tblidtype
	@Override
	public int addIdtype(Idtype idtype) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(Idtype.class);
			criteria.add(Restrictions.eq("idtypeName",idtype.getIdtypeName()));
			criteria.add(Restrictions.eq("organizationId",idtype.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag",true));
			if(criteria.list().size()!=0){
				List<Idtype> state1=new ArrayList<Idtype>();
				state1=criteria.list();
				Iterator<Idtype> ite= state1.iterator();
				while(ite.hasNext()){
					Idtype idtype2=ite.next();
					idtype2.setDeleteFlag(false);
					idtype2.setUpdateDate(date.toString());
					idtype2.setInsertDate(date.toString());
					idtype2.setInsertBy(idtype.getInsertBy());
					idtype2.setUpdateBy(idtype.getUpdateBy());
			session.update(idtype2);
			}
			}
			else{
				idtype.setUpdateDate(date.toString());
				idtype.setInsertDate(date.toString());
				session.save(idtype);
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
				Criteria criteria=session.createCriteria(Idtype.class);
				criteria.add(Restrictions.eq("idtypeName",idtype.getIdtypeName()));
				criteria.add(Restrictions.eq("organizationId",idtype.getOrganizationId()));
				if(criteria.list().size()!=0){
					List<Idtype> idtype1=new ArrayList<Idtype>();
					idtype1=criteria.list();
					Iterator<Idtype> ite= idtype1.iterator();
					while(ite.hasNext()){
						Idtype idtype2=ite.next();
						if(idtype2.isDeleteFlag()==false){
							status=2;
						}else{
							idtype2.setDeleteFlag(false);
							idtype2.setUpdateDate(date.toString());
							idtype2.setInsertDate(date.toString());
							idtype2.setInsertBy(idtype.getInsertBy());
							idtype2.setUpdateBy(idtype.getUpdateBy());
							session.update(idtype2);
				status = 1;
				}}
				}
				else{
					idtype.setUpdateDate(date.toString());
					idtype.setInsertDate(date.toString());
					session.save(idtype);
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
		// Update a record in tblidtype for specific idtype_id
		@Override
		public int updateIdtype(Idtype idtype) throws HibernateException,
				ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status = 0;
			try {
				session.beginTransaction();
				Criteria criteria=session.createCriteria(Idtype.class);
				criteria.add(Restrictions.eq("idtypeName",idtype.getIdtypeName()));
				criteria.add(Restrictions.eq("organizationId",idtype.getOrganizationId()));
				criteria.add(Restrictions.eq("deleteFlag",false));
				criteria.add(Restrictions.ne("idtypeId",idtype.getIdtypeId()));
				if(criteria.list().size()==0){
					session.clear();
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


	// Delete a record from tblidtype for specific idtype_id
	@Override
	public int deleteIdtype(int idtypeId) throws HibernateException,
			ConstraintViolationException {
		int status = 0;
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Idtype idtype = getIdtypeById(idtypeId);
						
			Criteria criteria2= session.createCriteria(EmployeeIdproof.class);
			criteria2.add(Restrictions.eq("organizationId",idtype.getOrganizationId()));
			criteria2.add(Restrictions.eq("deleteFlag", false));
			criteria2.add(Restrictions.eq("idType",  new Idtype(idtype.getIdtypeId())));
			List<Idtype> idtypelist=new ArrayList<>();
			idtypelist= criteria2.list();
			if(idtypelist.size()!=0)
			{
				status=2;
				return status;
			}
			if (idtype != null &&  idtypelist.size()==0) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				idtype.setUpdateDate(date.toString());
				idtype.setUpdateBy(null);
				idtype.setDeleteFlag(true);
				session.update(idtype);
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
