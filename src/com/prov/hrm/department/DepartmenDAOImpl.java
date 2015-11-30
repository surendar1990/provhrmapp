package com.prov.hrm.department;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

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
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
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
			
		}
	}

	// Insert a record into tbldepartment
	public int addDepartment(Department department) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			department.setUpdateDate(date.toString());
			department.setInsertDate(date.toString());
			session.save(department);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// Update a record in tbldepartment for specific department_id
	public int updateDepartment(Department department)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			department.setUpdateDate(date.toString());
			Department dept = getDepartmentById(department.getDepartmentId());
			if (dept != null)

			{
				department.setInsertBy(dept.getInsertBy());
				department.setInsertDate(dept.getInsertDate());
				session.update(department);
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
		try {
			Department department = getDepartmentById(departmentId);
			if (department != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				department.setUpdateDate(date.toString());
				department.setUpdateBy(null);
				department.setDeleteFlag(true);
				updateDepartment(department);
				status = 1;
				return status;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
	}

}
