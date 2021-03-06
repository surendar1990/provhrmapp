package com.prov.hrm.employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.login.Encryption;
import com.prov.hrm.login.Login;
import com.prov.hrm.organization.Organization;
import com.prov.hrm.utility.Mail;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	// Get all the records from the tblemployee
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployee(int organizationId)
			throws HibernateException, ConstraintViolationException {

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		List<Employee> employeede = new ArrayList<Employee>();
		List<Employee> emp = new ArrayList<Employee>();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.addOrder(Order.asc("firstName"));
			employeede = criteria.list();
			session.getTransaction().commit();
			Iterator<Employee> ite = employeede.iterator();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			while (ite.hasNext()) {
				Employee employee = (Employee) ite.next();
				if (employee.getPassportValidity() != null) {
					Date passport = sdinput.parse(employee
							.getPassportValidity());
					employee.setPassportValidity(sdfOut.format(passport));
				} else {
					employee.setPassportValidity(null);
				}
				Date DOJ = sdinput.parse(employee.getDateOfJoining());
				employee.setDateOfJoining(sdfOut.format(DOJ));
				String fname = employee.getFirstName();
				String lname = employee.getLastName();
				String name = fname + " " + lname;
				employee.setName(name);
				emp.add(employee);
			}

			return employeede;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();

		}
	}

	// Get a record by id using the employeeId column from tblemployee
	@Override
	public Employee getEmployeeById(int employeeId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		List<Employee> employeede = new ArrayList<Employee>();
		List<Employee> emp = new ArrayList<Employee>();
		try {
			session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class,
					employeeId);
			session.getTransaction().commit();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			if (employee != null) {
				if (!employee.getDeleteFlag()) {
					if (employee.getPassportValidity() != null) {
						Date passport = sdinput.parse(employee
								.getPassportValidity());
						employee.setPassportValidity(sdfOut.format(passport));
					} else {
						employee.setPassportValidity(null);
					}
					Date DOJ = sdinput.parse(employee.getDateOfJoining());
					employee.setDateOfJoining(sdfOut.format(DOJ));
					String fname = employee.getFirstName();
					String lname = employee.getLastName();
					String name = fname + " " + lname;
					employee.setName(name);
					return employee;
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
		
			session.close();
		}
	}

	

	// Add the new employee in tblemployee
	@Override
	public int addEmployee(Employee employee) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		String encryptname = null;
		String encryptpassword = null;
		int status = 0;
		try {
			session.beginTransaction();
			List<Employee> employee1 = new ArrayList<>();
			List<Login> employee2 = new ArrayList<>();

			Criterion rest1 = Restrictions.eq("deleteFlag", true);
			Criterion rest2 = Restrictions.eq("deleteFlag", false);
			Criteria criteria1 = session.createCriteria(Login.class);
			criteria1.add(Restrictions.eq("loginName", employee.getEmailId()));
			criteria1.add(Restrictions.or(rest1, rest2)).uniqueResult();
			employee2 = criteria1.list();

			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("organizationId",
					employee.getOrganizationId()));
			criteria.add(Restrictions.eq("empNo", employee.getEmpNo()));
			employee1 = criteria.list();

			if (employee2.size() == 0 && employee1.size() == 0) {
				encryptname = new Encryption().encrypt(employee.getEmailId());
				encryptpassword = new Encryption().encrypt("Prov@123");

				SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");

				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				employee.setInsertDate(date.toString());
				employee.setUpdateDate(date.toString());

				if (employee.getPassportValidity() != null) {
					Date passport = indate
							.parse(employee.getPassportValidity());
					employee.setPassportValidity(outdate.format(passport));
				} else {
					employee.setPassportValidity(null);
				}
				Date DOJ = indate.parse(employee.getDateOfJoining());
				employee.setDateOfJoining(outdate.format(DOJ));
				Login login = new Login();
				login.setEmployee(employee);
				login.setLoginName(employee.getEmailId());
				login.setEncryptName(encryptname);
				login.setOrganization(new Organization(employee
						.getOrganizationId()));
				login.setLoginPassword("Prov@123");
				login.setEncryptPassword(encryptpassword);
				login.setInsertDate(date.toString());
				login.setUpdateDate(date.toString());
				Set<Login> set = new HashSet<Login>();
				set.add(login);
				employee.setLogin(set);
				int key = (int) session.save(employee);
				status = 1;
				if (status == 1) {
					boolean mailStatus = Mail.sendMailForCreateUser(
							employee.getEmailId(), employee.getFirstName(),
							employee.getEmailId(), "Prov@123",
							employee.getOrganizationId());
					status = 3;
					return mailStatus ? key : key;
				}
			} else if(employee1.size() != 0) {
				status = 4;
			}else  {
				status = 5;
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		return status;
	}

	// update the employee details in table tblemployee using employeeId

		@Override
		public int updateEmployee(Employee employee) throws HibernateException,
				ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status = 0;
			try {
				session.beginTransaction();
				
				SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				Employee emp = getEmployeeById(employee.getEmployeeId());
				
				Criterion rest1 = Restrictions.eq("deleteFlag", true);
				Criterion rest2 = Restrictions.eq("deleteFlag", false);
				Criteria criteria2 = session.createCriteria(Login.class);
				criteria2.add(Restrictions.ne("loginName", emp.getEmailId()));
				criteria2.add(Restrictions.eq("loginName", employee.getEmailId()));
				criteria2.add(Restrictions.or(rest1, rest2)).uniqueResult();
				
				Criteria criteria = session.createCriteria(Employee.class);
				criteria.add(Restrictions.ne("employeeId", employee.getEmployeeId()));
				criteria.add(Restrictions.eq("organizationId",
						employee.getOrganizationId()));
				criteria.add(Restrictions.eq("empNo", employee.getEmpNo()));
				
				if ((criteria.list().size() == 0 )&&( criteria2.list().size() == 0)) 
				{
					
						if (employee.getPassportValidity() != null) {
							Date passport = indate
								.parse(employee.getPassportValidity());
						employee.setPassportValidity(outdate.format(passport));
					} else {
						employee.setPassportValidity(null);
					}
						System.out.println(employee);
					Date DOJ = indate.parse(employee.getDateOfJoining());
					employee.setDateOfJoining(outdate.format(DOJ));
					employee.setInsertBy(emp.getInsertBy());
					employee.setInsertDate(emp.getInsertDate());
					employee.setUpdateDate(date.toString());
					session.update(employee);
					session.getTransaction().commit();
					session.beginTransaction();
					Criteria criteria1=session.createCriteria(Login.class);
					criteria1.add(Restrictions.eq("organization", new Organization(employee.getOrganizationId())));
					criteria1.add(Restrictions.eq("employee", new Employee(employee.getEmployeeId())));
					criteria1.add(Restrictions.eq("deleteFlag",false));
					System.out.println("Inside If");
					Iterator<Login> login= criteria1.list().iterator();
					while(login.hasNext()){
						Login login1= (Login) login.next();
						login1.setLoginName(employee.getEmailId());
						session.update(login1);
						status = 1;
						System.out.println("Inside while");
						
						
					}
				
				}else if(criteria2.list().size() != 0){
					return 5;
				}else {
					return 4;
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				session.getTransaction().commit();
				session.close();
			}
			return status;
		}
	// soft delete
	@Override
	public int deleteEmployee(int employeeId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Employee employee = getEmployeeById(employeeId);
			if (employee != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				employee.setUpdateDate(date.toString());
				employee.setUpdateBy(null);
				employee.setDeleteFlag(true);
				session.update(employee);
				session.getTransaction().commit();
				session.beginTransaction();
				Login check = (Login) session
						.createCriteria(Login.class)
						.add(Restrictions.eq("loginName", employee.getEmailId()))
						.add(Restrictions.eq("deleteFlag", false))
						.uniqueResult();
				check.setDeleteFlag(true);

				session.update(check);
				session.getTransaction().commit();
				status = 1;
				return status;
			} else {
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.close();
		}
	}

	public int deleteEmployeeHard(int employeeId) throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Employee employee = getEmployeeById(employeeId);
			session.delete(employee);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();

		}
	}

}
