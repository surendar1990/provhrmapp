package com.prov.hrm.employeeidproof;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeebank.EmployeeBank;
import com.prov.hrm.idtype.Idtype;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeIdproofDAOImpl implements EmployeeIdproofDAO {

	// Return all record from tblempidproof
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeIdproof> getAllIdProof(int organizationid)
			throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		List<EmployeeIdproof> empidproof=new ArrayList<EmployeeIdproof>();
		List<EmployeeIdproof> empidproof1=new ArrayList<EmployeeIdproof>();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeIdproof.class);
			criteria.add(Restrictions.eq("organizationId", organizationid));
			criteria.add(Restrictions.eq("deleteFlag", false));
			empidproof1= criteria.list();
			session.getTransaction().commit();
			Iterator<EmployeeIdproof> ite = empidproof1.iterator();
			 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			while(ite.hasNext()) {
				EmployeeIdproof empid=(EmployeeIdproof)ite.next();
				if(empid.getValidUpto()!=null){
				Date DOJ = sdinput.parse(empid.getValidUpto());
				empid.setValidUpto(sdfOut.format(DOJ));
				empidproof.add(empid);
				}else{ 
					empid.setValidUpto(null);
					}
			}
			return empidproof;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			
			session.close();
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
			 
			SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			
			 Criteria criteria=session.createCriteria(EmployeeIdproof.class);
			 criteria.add(Restrictions.eq("organizationId",idproof.getOrganizationId()));
			 criteria.add(Restrictions.eq("employee", new Employee(idproof.getEmployee().getEmployeeId())));
			 criteria.add(Restrictions.eq("idType", new Idtype(idproof.getIdType().getIdtypeId())));
			
			 if(criteria.list().size()==0){
			 idproof.setInsertDate(date.toString());
			idproof.setUpdateDate(date.toString());
			if(idproof.getValidUpto()!=null){
				Date validdate = indate.parse(idproof.getValidUpto());	
				idproof.setValidUpto(outdate.format(validdate));
			}else{
				idproof.setValidUpto(null);
			}	
			session.save(idproof);
			status = 1;
			 }else{
				 Iterator<EmployeeIdproof> iterator= criteria.list().iterator();
				 while(iterator.hasNext()){
					 EmployeeIdproof employeeid=iterator.next();
					 if(employeeid.isDeleteFlag()==true){
						 employeeid.setDeleteFlag(false);
						 employeeid.setInsertDate(date.toString());
						 employeeid.setUpdateDate(date.toString());
							if(idproof.getValidUpto()!=null){
								Date validdate = indate.parse(idproof.getValidUpto());	
								employeeid.setValidUpto(outdate.format(validdate));
							}else{
								employeeid.setValidUpto(null);
							}
						 session.update(employeeid);
						 status = 1;
					 }else{
						 return 5;
					 }
				 }
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		return status;
	}

	// Update a record in tblempidproof for specific Empidproof_id
	@Override
	public int updateIdProof(EmployeeIdproof idproof)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			 EmployeeIdproof empidproof = getIdProofId(idproof.getEmpidproofId());
			
			 Criteria criteria=session.createCriteria(EmployeeIdproof.class);
			 criteria.add(Restrictions.eq("organizationId",idproof.getOrganizationId()));
			 criteria.add(Restrictions.ne("empidproofId", idproof.getEmpidproofId()));
			 criteria.add(Restrictions.eq("employee", new Employee(idproof.getEmployee().getEmployeeId())));
			 criteria.add(Restrictions.eq("idType", new Idtype(idproof.getIdType().getIdtypeId())));
			 criteria.add(Restrictions.or(Restrictions.eq("deleteFlag",true),Restrictions.eq("deleteFlag", false)));
			
			 if((criteria.list().size()==0)&&(empidproof!=null)){
			idproof.setInsertDate(empidproof.getInsertDate());
			idproof.setUpdateDate(date.toString());
			if(idproof.getValidUpto()!=null){
				Date validdate = indate.parse(idproof.getValidUpto());	
				idproof.setValidUpto(outdate.format(validdate));
			}else{
				idproof.setValidUpto(null);
			}
			session.update(idproof);
			status = 1;
			}else{
				return 5;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			session.close();			
		}
		return status;
	}

	// Delete a record from tblempidproof for specific Empidproof_id(SOFT DELETE)
	@Override
	public int deleteIdProof(int idproofId)throws HibernateException,ConstraintViolationException 
	{
		int status = 0;
		try {
			EmployeeIdproof idproof = getIdProofId(idproofId);
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
			session.close();
		}
	}

	//GET A SINGLE RECORD by employeeId
			@Override
			public List<EmployeeIdproof> getIdProofById(int employeeId,int organizationId) throws HibernateException,ConstraintViolationException
			{
				Session session=SessionFactoryUtil.getSessionFactory().openSession();
				try{
					session.beginTransaction();
					List<EmployeeIdproof> empidproof=new ArrayList<EmployeeIdproof>();
					List<EmployeeIdproof> empidproof1=new ArrayList<EmployeeIdproof>();
					 Criteria criteria = session.createCriteria(EmployeeIdproof.class);
					criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
					criteria.add(Restrictions.eq("organizationId", organizationId));
					criteria.add(Restrictions.eq("deleteFlag", false));
					empidproof=criteria.list();
					Iterator<EmployeeIdproof> ite = empidproof.iterator();
					 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
					 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
					while(ite.hasNext()) {
						EmployeeIdproof empid=(EmployeeIdproof)ite.next();
						if(empid.getValidUpto()!=null){
						Date DOJ = sdinput.parse(empid.getValidUpto());
						empid.setValidUpto(sdfOut.format(DOJ));
						empidproof1.add(empid);
						}else{ 
							empid.setValidUpto(null);
							empidproof1.add(empid);
						}
					}
					return empidproof1;
					
				}catch (Exception e) {
						e.printStackTrace();
						return null;
					} finally {
						
						session.close();
					}
			
				
				}
			
	
	// Return a record from tblempidproof for specific Empidproof_id

	public EmployeeIdproof getIdProofId(int idproofId)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeIdproof idproof = (EmployeeIdproof) session.get(
					EmployeeIdproof.class, idproofId);
			if(idproof!=null){
			if (!idproof.isDeleteFlag()) {
				return idproof;
			} else {
				return null;
			}
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


}
