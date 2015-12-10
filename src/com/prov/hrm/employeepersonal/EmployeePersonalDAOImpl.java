package com.prov.hrm.employeepersonal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeePersonalDAOImpl implements EmployeePersonalDAO
{

	//GET ALL RECORDS
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeePersonal> getAllEmpPersonal(int organizationId)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		List<EmployeePersonal> emppersonal=new ArrayList<EmployeePersonal>();
		List<EmployeePersonal> emppersonal1=new ArrayList<EmployeePersonal>();
		try{
			session.beginTransaction();
			Criteria criteria=session.createCriteria(EmployeePersonal.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			emppersonal1= criteria.list();
			session.getTransaction().commit();
			Iterator<EmployeePersonal> ite = emppersonal1.iterator();
			 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			while(ite.hasNext()) {
				EmployeePersonal employeepersonal=(EmployeePersonal)ite.next();
				Date DOB = sdinput.parse(employeepersonal.getDateOfBirth());
				employeepersonal.setDateOfBirth(sdfOut.format(DOB));
				emppersonal.add(employeepersonal);
			}
			return emppersonal;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
			
		}
		
	}

	//GET A SINGLE RECORD by employeeId
	@Override
	public List<EmployeePersonal> getEmpPersonal(int employeeId,int organizationId) throws HibernateException,ConstraintViolationException
	{
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		List<EmployeePersonal> emppersonal=new ArrayList<EmployeePersonal>();
		List<EmployeePersonal> emppersonal1=new ArrayList<EmployeePersonal>();
		
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeePersonal.class);
			criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			emppersonal= criteria.list();
			session.getTransaction().commit();
			Iterator<EmployeePersonal> ite = emppersonal.iterator();
			 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			while(ite.hasNext()) {
				EmployeePersonal employeepersonal=(EmployeePersonal)ite.next();
				Date DOB = sdinput.parse(employeepersonal.getDateOfBirth());
				employeepersonal.setDateOfBirth(sdfOut.format(DOB));
				emppersonal1.add(employeepersonal);
			}
			return emppersonal1;
			
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
			session.close();
				
			}
		}
	
	//GET A SINGLE RECORD by emppersonalId
		public EmployeePersonal getEmployeePersonal(int emppersonalId) throws HibernateException,ConstraintViolationException
		{
			Session session=SessionFactoryUtil.getSessionFactory().openSession();
			try{
				session.beginTransaction();
				EmployeePersonal employeepersonal=(EmployeePersonal) session.get(EmployeePersonal.class, emppersonalId);
				if (employeepersonal != null) {
					if (!employeepersonal.getDeleteFlag()) {
						return employeepersonal;
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


	//INSERT A SINGLE RECORD IN EMPLOYEE PERSONAL
	@Override
	public int addEmpPersonal(EmployeePersonal employeepersonal)throws HibernateException,ConstraintViolationException {
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		int status=0;
		try{
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			employeepersonal.setInsertDate(date.toString());
			employeepersonal.setUpdateDate(date.toString());
			Date fromDate = indate.parse(employeepersonal.getDateOfBirth());
			employeepersonal.setDateOfBirth(outdate.format(fromDate));
			session.save(employeepersonal);
			status=1;
			return status;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			session.getTransaction().commit();
			session.close();
			
		}
	}

	//UPDATE A SINGLE RECORD IN EMPLOYEE PERSONAL
	@Override
	public int updateEmpPersonal(EmployeePersonal employeepersonal)throws HibernateException,ConstraintViolationException {
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		int status=0;
		try{
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			employeepersonal.setUpdateDate(date.toString());
			EmployeePersonal emppersonal = getEmployeePersonal(employeepersonal.getEmppersonalId());
			if (emppersonal != null) {
				employeepersonal.setInsertBy(emppersonal.getInsertBy());
				employeepersonal.setInsertDate(emppersonal.getInsertDate());
				Date fromDate = indate.parse(employeepersonal.getDateOfBirth());
				employeepersonal.setDateOfBirth(outdate.format(fromDate));
				session.update(employeepersonal);
			} else {
				return status;
			}
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

	//DELETE A RECORD (SOFT DELETE)
	@Override
	public int deleteEmpPersonal(int emppersonalId)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			EmployeePersonal emppersonal = getEmployeePersonal(emppersonalId);
			if(emppersonal!=null)
			{
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			emppersonal.setUpdateDate(date.toString());
			emppersonal.setUpdateBy(null);
			emppersonal.setDeleteFlag(true);
			updateEmpPersonal(emppersonal);
			status=1;
			return status;
		}
			else {
				return status;
			}
		}
			catch (Exception e) {
			e.printStackTrace();
			return status;
		} 
	}
	
	//DELETE A RECORD (HARD DELETE)
	public int deleteEmployeeHard(int emppersonalId) throws HibernateException,ConstraintViolationException
	{
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeePersonal emppersonal = getEmployeePersonal(emppersonalId);
			session.delete(emppersonal);
			status=1;
			return status;
		}
			catch (Exception e) {
			e.printStackTrace();
			return status;
		} 
		finally{
			session.getTransaction().commit();
			
		}
	}
	

}
