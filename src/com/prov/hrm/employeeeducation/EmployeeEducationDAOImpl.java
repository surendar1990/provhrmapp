package com.prov.hrm.employeeeducation;

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

import com.prov.hrm.announcement.Announcement;
import com.prov.hrm.educationlevel.EducationLevel;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeEducationDAOImpl implements EmployeeEducationDAO {

	// GET ALL RECORDS FROM tblempeducation
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeEducation> getAllEmployeeEducation(int organizationId)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		
		List<EmployeeEducation> employeeeducation1=new ArrayList<>();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeEducation.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			employeeeducation1= criteria.list();
			session.getTransaction().commit();
			
			return employeeeducation1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			session.close();
		}

		
	}
	
	//GET A SINGLE RECORD by employeeId
		@Override
		public List<EmployeeEducation> getEmployeeEducationById(int employeeId,int organizationId) throws HibernateException,ConstraintViolationException
		{
			Session session=SessionFactoryUtil.getSessionFactory().openSession();
			try{
				session.beginTransaction();
				
				List<EmployeeEducation> employeeeducation1=new ArrayList<>();
				Criteria criteria = session.createCriteria(EmployeeEducation.class);
				criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
				criteria.add(Restrictions.eq("organizationId", organizationId));
				criteria.add(Restrictions.eq("deleteFlag", false));
				employeeeducation1= criteria.list();
				return  employeeeducation1;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}finally{
					session.getTransaction().commit();
					session.close();
				} 
			}
	
	// GET A SINGLE RECORD FROM tblempeducation USING empeducationId
	
	public EmployeeEducation getEmployeeEducationId(int empeducationId)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeEducation empeducation = (EmployeeEducation) session.get(
					EmployeeEducation.class, empeducationId);
			if (empeducation != null) {
				if (!empeducation.getDeleteFlag()) {
					
					return empeducation;
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
	// ADD A EMPLOYEE EDUCATION DETAILS IN tblempeducation
	@Override
	public int addEmployeeEducation(EmployeeEducation employeeeducation)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			
			Criteria criteria= session.createCriteria(EmployeeEducation.class);
			criteria.add(Restrictions.eq("organizationId", employeeeducation.getOrganizationId()));
			criteria.add(Restrictions.eq("employee",new Employee(employeeeducation.getEmployee().getEmployeeId())));
			criteria.add(Restrictions.eq("educationLevel", new EducationLevel(employeeeducation.getEducationLevel().getEducationLevelId())));
			
			if(criteria.list().size()==0){
			employeeeducation.setUpdateDate(date.toString());
			employeeeducation.setInsertDate(date.toString());
			session.save(employeeeducation);
			status = 1;
			}else{
				Iterator<EmployeeEducation> iterator=criteria.list().iterator();
				while(iterator.hasNext()){
					EmployeeEducation empeducation= (EmployeeEducation) iterator.next();
					if(empeducation.getDeleteFlag()==true){
					empeducation.setDeleteFlag(false);
					empeducation.setInsertDate(date.toString());
					empeducation.setUpdateDate(date.toString());
					session.update(empeducation);
					status=1;
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

	// UPDATE A EMPLOYEE EDUCATION DETAILS IN tblempeducation
	@Override
	public int updateEmployeeEducation(EmployeeEducation employeeeducation)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			
			EmployeeEducation empeducation = getEmployeeEducationId(employeeeducation.getEmpeducationId());
			
			Criteria criteria= session.createCriteria(EmployeeEducation.class);
			criteria.add(Restrictions.eq("organizationId", employeeeducation.getOrganizationId()));
			criteria.add(Restrictions.ne("empeducationId", employeeeducation.getEmpeducationId()));
			criteria.add(Restrictions.eq("employee",new Employee(employeeeducation.getEmployee().getEmployeeId())));
			criteria.add(Restrictions.eq("educationLevel", new EducationLevel(employeeeducation.getEducationLevel().getEducationLevelId())));
			
			if((criteria.list().size()==0)&&(empeducation != null)){
			
				employeeeducation.setInsertBy(empeducation.getInsertBy());
				employeeeducation.setInsertDate(empeducation.getInsertDate());
				employeeeducation.setUpdateDate(date.toString());
				session.update(employeeeducation);
				status = 1;
			} else {
				return 5;
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		return status;
	}
	// DELETE A EMPLOYEE EDUCATION DETAILS (SOFT DELETE)
	public int deleteEmployeeEducation(int empeducationId)throws HibernateException, ConstraintViolationException {
		int status = 0;
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeEducation employeeeducation = getEmployeeEducationId(empeducationId);
			if (employeeeducation != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				employeeeducation.setUpdateDate(date.toString());
				employeeeducation.setDeleteFlag(true);
				session.update(employeeeducation);
				status = 1;
				return status;
			} else {
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
	// DELETE A EMPLOYEE EDUCATION DETAILS (HARD DELETE)
	public int deleteEmployeeEducationHard(int empeducationId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeEducation employeeEducation = getEmployeeEducationId(empeducationId);
			session.delete(employeeEducation);
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
