package com.prov.hrm.department;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.bank.Bank;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeebank.EmployeeBank;
import com.prov.hrm.idtype.Idtype;
import com.prov.hrm.role.Role;
import com.prov.hrm.utility.SessionFactoryUtil;

public class DepartmenDAOImpl implements DepartmentDAO {

	// Return all record from tbldepartment
	public List<Department> getAllDepartment(int organizationId)
			throws HibernateException, ConstraintViolationException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.addOrder(Order.asc("departmentName"));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();
		}

	}

	// Return a record from tbldepartment for specific department_id
	public Department getDepartmentById(int departmentId)
			throws HibernateException, ConstraintViolationException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Department department = (Department) session.get(Department.class,
					departmentId);
			if (!department.getDeleteFlag()) {
				return department;
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

	/*// Insert a record into tbldepartment
	public int addDepartment(Department department) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(Department.class);
			criteria.add(Restrictions.eq("departmentName",department.getDepartmentName()));
			criteria.add(Restrictions.eq("organizationId",department.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag",true));
			if(criteria.list().size()!=0){
				List<Department> dept1=new ArrayList<Department>();
				dept1=criteria.list();
				Iterator<Department> ite= dept1.iterator();
				while(ite.hasNext()){
					Department dept2=ite.next();
					dept2.setDeleteFlag(false);
					dept2.setUpdateDate(date.toString());
					dept2.setInsertDate(date.toString());
					dept2.setInsertBy(department.getInsertBy());
					dept2.setUpdateBy(department.getUpdateBy());
			session.update(dept2);
			}
			}
			else{
				department.setUpdateDate(date.toString());
				department.setInsertDate(date.toString());
				session.save(department);
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
	
	// Insert a record into tbldepartment
			public int addDepartment(Department department) throws HibernateException,
					ConstraintViolationException {
				Session session = SessionFactoryUtil.getSessionFactory().openSession();
				int status = 0;
				try {
					session.beginTransaction();
					java.sql.Timestamp date = new java.sql.Timestamp(
							new java.util.Date().getTime());
					Criteria criteria=session.createCriteria(Department.class);
					criteria.add(Restrictions.eq("departmentName",department.getDepartmentName()));
					criteria.add(Restrictions.eq("organizationId",department.getOrganizationId()));
					if(criteria.list().size()!=0){
						List<Department> dept1=new ArrayList<Department>();
						dept1=criteria.list();
						Iterator<Department> ite= dept1.iterator();
						while(ite.hasNext()){
							Department dept2=ite.next();
							if(dept2.getDeleteFlag()==false){
								status=2;
							}else{
							dept2.setDeleteFlag(false);
							dept2.setUpdateDate(date.toString());
							dept2.setInsertDate(date.toString());
							dept2.setInsertBy(department.getInsertBy());
							dept2.setUpdateBy(department.getUpdateBy());
					session.update(dept2);
					status = 1;
					}}
					}
					else{
						department.setUpdateDate(date.toString());
						department.setInsertDate(date.toString());
						session.save(department);
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


			// Update a record in tbldepartment for specific department_id
			public int updateDepartment(Department department)
					throws HibernateException, ConstraintViolationException {
				Session session = SessionFactoryUtil.getSessionFactory().openSession();
				int status = 0;
				try {
					session.beginTransaction();
					Criteria criteria=session.createCriteria(Department.class);
					criteria.add(Restrictions.eq("departmentName",department.getDepartmentName()));
					criteria.add(Restrictions.eq("organizationId", department.getOrganizationId()));
					criteria.add(Restrictions.eq("deleteFlag",false));
					criteria.add(Restrictions.ne("departmentId", department.getDepartmentId()));
					if(criteria.list().size()==0)
					{	session.clear();
					java.sql.Timestamp date = new java.sql.Timestamp(
							new java.util.Date().getTime());
					department.setUpdateDate(date.toString());
					Department dept = getDepartmentById(department.getDepartmentId());
					if (dept != null)

					{
						department.setUpdateBy(dept.getInsertBy());
						department.setUpdateDate(dept.getInsertDate());
						session.update(department);
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
					SessionFactoryUtil.getSessionFactory().close();
				}
			}


	// Delete a record from tbldepartment for specific department_id(Hard
	// delete)
	public int deleteDepartmentHard(int departmentId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();

			Department department = getDepartmentById(departmentId);
			session.delete(department);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Soft Delete a record from tbldepartment for specific department_id
	public int deleteDepartment(int departmentId) {
		int status = 0;
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			
			Department department = getDepartmentById(departmentId);
			
			List<Employee> departmentlist=new ArrayList<>();
			List<Role> departmentlist1=new ArrayList<>();
			
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("organizationId", department.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.eq("department", new Department(department.getDepartmentId())));
			departmentlist=criteria.list();
			if(departmentlist.size()==0){
			Criteria criteria2= session.createCriteria(Role.class);
			criteria2.add(Restrictions.eq("organizationId",department.getOrganizationId()));
			criteria2.add(Restrictions.eq("deleteFlag", false));
			criteria2.add(Restrictions.eq("department",  new Department(department.getDepartmentId())));
			departmentlist1= criteria2.list();
			}
			if(departmentlist.size()!=0 || departmentlist1.size()!=0)
			{
				status=2;
				return status;
			}
			if (department != null &&  departmentlist.size()==0 && departmentlist1.size()==0) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				department.setUpdateDate(date.toString());
				department.setUpdateBy(null);
				department.setDeleteFlag(true);
				session.update(department);
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
