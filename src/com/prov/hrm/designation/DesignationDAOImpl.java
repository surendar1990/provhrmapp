package com.prov.hrm.designation;

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
import com.prov.hrm.employee.Employee;
import com.prov.hrm.role.Role;
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
			criteria.addOrder(Order.asc("designationName"));
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
			session.close();
		}
	}

	/*// Insert a record into tbldesignation
	public int addDesignation(Designation designation)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status=0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(Designation.class);
			criteria.add(Restrictions.eq("designationName",designation.getDesignationName()));
			criteria.add(Restrictions.eq("organizationId",designation.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag",true));
			if(criteria.list().size()!=0){
				List<Designation> dept1=new ArrayList<Designation>();
				dept1=criteria.list();
				Iterator<Designation> ite= dept1.iterator();
				while(ite.hasNext()){
					Designation des2=ite.next();
					des2.setDeleteFlag(false);
					des2.setUpdateDate(date.toString());
					des2.setInsertDate(date.toString());
					des2.setInsertBy(designation.getInsertBy());
					des2.setUpdateBy(designation.getUpdateBy());
			session.update(des2);
			}
			}
			else{
				designation.setUpdateDate(date.toString());
				designation.setInsertDate(date.toString());
				session.save(designation);
			}
			status=1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}

	}
*/
	// Insert a record into tbldesignation
		public int addDesignation(Designation designation)throws HibernateException,ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status=0;
			try {

				session.beginTransaction();
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				Criteria criteria=session.createCriteria(Designation.class);
				criteria.add(Restrictions.eq("designationName",designation.getDesignationName()));
				criteria.add(Restrictions.eq("organizationId",designation.getOrganizationId()));
				if(criteria.list().size()!=0){
					List<Designation> desg1=new ArrayList<Designation>();
					desg1=criteria.list();
					Iterator<Designation> ite= desg1.iterator();
					while(ite.hasNext()){
						Designation desg2=ite.next();
						if(desg2.getDeleteFlag()==false){
							status=2;
						}else{
							desg2.setDeleteFlag(false);
							desg2.setUpdateDate(date.toString());
							desg2.setInsertDate(date.toString());
							desg2.setInsertBy(designation.getInsertBy());
							desg2.setUpdateBy(designation.getUpdateBy());
				session.update(desg2);
				status = 1;
				}}
				}
				else{
					designation.setUpdateDate(date.toString());
					designation.setInsertDate(date.toString());
					session.save(designation);
					status = 1;
				}
				
				return status;
			} catch (Exception e) {
				e.printStackTrace();
				return status;
			} finally {
				session.getTransaction().commit();
				session.close();
			}

		}
		// Update a record in tbldesignation for specific designation_id
		public int updateDesignation(Designation designation)throws HibernateException,ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status = 0;
			try {
				session.beginTransaction();
				Criteria criteria=session.createCriteria(Designation.class);
				criteria.add(Restrictions.eq("designationName",designation.getDesignationName()));
				criteria.add(Restrictions.eq("organizationId", designation.getOrganizationId()));
				criteria.add(Restrictions.eq("deleteFlag",false));
				criteria.add(Restrictions.ne("designationId", designation.getDesignationId()));
				if(criteria.list().size()==0)
				{ 	session.clear();
				java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
				designation.setUpdateDate(date.toString());
				Designation designationTime = getDesignationById(designation.getDesignationId());
				designation.setInsertDate(designationTime.getInsertDate());
				session.update(designation);
				status = 1;
				return status;
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
				SessionFactoryUtil.getSessionFactory().close();
				
			}
		}

	//soft delete
		@Override
		public int deleteDesignation(int designationId) {
			int status = 0;
			
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				
				Designation designation = getDesignationById(designationId);
				
				List<Designation> designationlist=new ArrayList<>();
				List<Department> designationlist1=new ArrayList<>();
				
				Criteria criteria = session.createCriteria(Employee.class);
				criteria.add(Restrictions.eq("organizationId", designation.getOrganizationId()));
				criteria.add(Restrictions.eq("deleteFlag", false));
				criteria.add(Restrictions.eq("designation", new Designation(designation.getDesignationId())));
				designationlist=criteria.list();
				if(designationlist.size()==0){
				Criteria criteria2= session.createCriteria(Role.class);
				criteria2.add(Restrictions.eq("organizationId",designation.getOrganizationId()));
				criteria2.add(Restrictions.eq("deleteFlag", false));
				criteria2.add(Restrictions.eq("designation",  new Designation(designation.getDesignationId())));
				designationlist1= criteria2.list();
				}
				if(designationlist.size()!=0 || designationlist1.size()!=0)
				{
					status=2;
					return status;
				}
				if (designation != null &&  designationlist.size()==0 && designationlist1.size()==0) {
					java.sql.Timestamp date = new java.sql.Timestamp(
							new java.util.Date().getTime());
					designation.setUpdateDate(date.toString());
					designation.setUpdateBy(null);
					designation.setDeleteFlag(true);
					session.update(designation);
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
			session.close();
		}
	}

}
