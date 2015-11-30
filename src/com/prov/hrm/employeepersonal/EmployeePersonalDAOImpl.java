package com.prov.hrm.employeepersonal;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeePersonalDAOImpl implements EmployeePersonalDAO
{

	//GET ALL RECORDS
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeePersonal> getAllEmpPersonal(int organizationId)throws HibernateException,ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Criteria criteria=session.createCriteria(EmployeePersonal.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			return criteria.list();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.getTransaction().commit();
			
		}
		
	}

	//GET A SINGLE RECORD by employeeId
	@Override
	public List<EmployeePersonal> getEmpPersonal(int employeeId,int organizationId) throws HibernateException,ConstraintViolationException
	{
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeePersonal.class);
			criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			return (List<EmployeePersonal>) criteria.list();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				session.getTransaction().commit();
				
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
			employeepersonal.setInsertDate(date.toString());
			employeepersonal.setUpdateDate(date.toString());
			session.save(employeepersonal);
			status=1;
			return status;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			session.getTransaction().commit();
			
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
			employeepersonal.setUpdateDate(date.toString());
			EmployeePersonal emppersonal = getEmployeePersonal(employeepersonal.getEmppersonalId());
			if (emppersonal != null) {
				employeepersonal.setInsertBy(emppersonal.getInsertBy());
				employeepersonal.setInsertDate(emppersonal.getInsertDate());
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
