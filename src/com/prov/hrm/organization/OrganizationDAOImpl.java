package com.prov.hrm.organization;

import java.nio.channels.SeekableByteChannel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.bank.Bank;
import com.prov.hrm.country.Country;
import com.prov.hrm.department.Department;
import com.prov.hrm.designation.Designation;
import com.prov.hrm.educationlevel.EducationLevel;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeebank.EmployeeBank;
import com.prov.hrm.employeeeducation.EmployeeEducation;
import com.prov.hrm.employeeidproof.EmployeeIdproof;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.employeeleaveeligibility.EmployeeLeaveEligibility;
import com.prov.hrm.employeemarital.EmployeeMarital;
import com.prov.hrm.employeepersonal.EmployeePersonal;
import com.prov.hrm.employeevisa.EmployeeVisa;
import com.prov.hrm.holiday.Holiday;
import com.prov.hrm.idtype.Idtype;
import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.login.Login;
import com.prov.hrm.role.Role;
import com.prov.hrm.stateprovince.StateProvince;
import com.prov.hrm.utility.Mail;
import com.prov.hrm.utility.SessionFactoryUtil;
import com.prov.hrm.visatype.VisaType;

public class OrganizationDAOImpl implements OrganizationDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<Organization> getAllOrganization() throws HibernateException,
			ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(Organization.class);
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

	@Override
	public Organization getOrganizationById(int organizationId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Organization organization = (Organization) session.get(
					Organization.class, organizationId);
			if (organization != null) {
				if (!organization.getDeleteFlag()) {
					return organization;
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

	@Override
	public int addOrganization(Organization organization)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();

			session.beginTransaction();
			Criterion rest1 = Restrictions.eq("deleteFlag", true);
			Criterion rest2 = Restrictions.eq("deleteFlag", false);
			Login org1 = (Login) session
					.createCriteria(Login.class)
					.add(Restrictions.eq("loginName",
							organization.getOrganizationEmail()))
					.add(Restrictions.or(rest1, rest2)).uniqueResult();
			if (org1 == null) {

				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				organization.setInsertDate(date.toString());
				organization.setUpdateDate(date.toString());
				Login login = new Login();
				login.setOrganization(organization);
				login.setLoginName(organization.getOrganizationEmail());
				login.setLoginPassword("Admin@123");
				Set<Login> set = new HashSet<Login>();
				set.add(login);
				organization.setLogin(set);
				int key = (int) session.save(organization);
				status = 1;
				if (status == 1) {
					boolean mailStatus = Mail.sendMailForCreateOrganization(
							organization.getOrganizationName(),
							organization.getOrganizationEmail(), "Admin@123");
					status = 3;
					return mailStatus ? key : 1;
				}
			} else {
				status = 4;
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

	@Override
	public int updateOrganization(Organization organization)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {

			session.beginTransaction();
			Organization org = getOrganizationById(organization.getOrganizationId());
			
			Criterion rest1 = Restrictions.eq("deleteFlag", true);
			Criterion rest2 = Restrictions.eq("deleteFlag", false);
			Criteria criteria = session.createCriteria(Login.class);
			criteria.add(Restrictions.ne("loginName",org.getOrganizationEmail()));
			criteria.add(Restrictions.eq("loginName",organization.getOrganizationEmail()));
			criteria.add(Restrictions.or(rest1, rest2)).uniqueResult();

			if ((org != null) && (criteria.list().size()== 0)) {

				java.sql.Timestamp date = new Timestamp(
						new java.util.Date().getTime());
				organization.setInsertDate(org.getInsertDate());
				organization.setInsertBy(org.getInsertBy());
				organization.setUpdateDate(date.toString());
				session.update(organization);
				
				session.getTransaction().commit();
				session.beginTransaction();
			
				Criteria criteria1 = session.createCriteria(Login.class);
				criteria1.add(Restrictions.eq("organization", new Organization(	organization.getOrganizationId())));
				criteria1.add(Restrictions.eq("loginName",org.getOrganizationEmail()));
				criteria1.add(Restrictions.eq("deleteFlag", false));
				Iterator<Login> login= criteria1.list().iterator();
				while(login.hasNext()){
							Login loginupdate= (Login) login.next();
							loginupdate.setLoginName(organization.getOrganizationEmail());
							session.update(loginupdate);
							status = 1;
						}
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

	@Override
	// Soft Delete a record from tblannouncement for specific announcement_id
	public int deleteOrganization(int organizationId) {
		int status = 0;

		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			Organization organization = getOrganizationById(organizationId);

			List<Organization> organization1 = new ArrayList<>();
			List<Organization> organization2 = new ArrayList<>();
			List<Organization> organization3 = new ArrayList<>();
			List<Organization> organization4 = new ArrayList<>();
			List<Organization> organization5 = new ArrayList<>();
			List<Organization> organization6 = new ArrayList<>();
			List<Organization> organization7 = new ArrayList<>();
			List<Organization> organization8 = new ArrayList<>();
			List<Organization> organization9 = new ArrayList<>();
			List<Organization> organization10 = new ArrayList<>();
			List<Organization> organization11 = new ArrayList<>();
			List<Organization> organization12 = new ArrayList<>();
			List<Organization> organization13 = new ArrayList<>();
			List<Organization> organization14 = new ArrayList<>();
			List<Organization> organization15 = new ArrayList<>();
			List<Organization> organization16 = new ArrayList<>();
			List<Organization> organization17 = new ArrayList<>();

			Criteria criteria = session.createCriteria(Bank.class);
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.eq("organizationId", organizationId));
			organization1 = criteria.list();
			if (organization1.size() == 0) {
				Criteria criteria1 = session.createCriteria(Department.class);
				criteria1.add(Restrictions.eq("deleteFlag", false));
				criteria1
						.add(Restrictions.eq("organizationId", organizationId));
				organization2 = criteria1.list();
			} else if (organization2.size() == 0) {
				Criteria criteria2 = session.createCriteria(Designation.class);
				criteria2.add(Restrictions.eq("deleteFlag", false));
				criteria2
						.add(Restrictions.eq("organizationId", organizationId));
				organization3 = criteria2.list();
			} else if (organization3.size() == 0) {
				Criteria criteria3 = session.createCriteria(Employee.class);
				criteria3.add(Restrictions.eq("deleteFlag", false));
				criteria3
						.add(Restrictions.eq("organizationId", organizationId));
				organization4 = criteria3.list();
			} else if (organization4.size() == 0) {
				Criteria criteria4 = session.createCriteria(EmployeeBank.class);
				criteria4.add(Restrictions.eq("deleteFlag", false));
				criteria4
						.add(Restrictions.eq("organizationId", organizationId));
				organization5 = criteria4.list();
			} else if (organization5.size() == 0) {
				Criteria criteria5 = session
						.createCriteria(EmployeeEducation.class);
				criteria5.add(Restrictions.eq("deleteFlag", false));
				criteria5
						.add(Restrictions.eq("organizationId", organizationId));
				organization6 = criteria5.list();
			} else if (organization6.size() == 0) {
				Criteria criteria6 = session
						.createCriteria(EmployeeIdproof.class);
				criteria6.add(Restrictions.eq("deleteFlag", false));
				criteria6
						.add(Restrictions.eq("organizationId", organizationId));
				organization7 = criteria6.list();
			} else if (organization7.size() == 0) {
				Criteria criteria7 = session
						.createCriteria(EmployeeLeave.class);
				criteria7.add(Restrictions.eq("deleteFlag", false));
				criteria7
						.add(Restrictions.eq("organizationId", organizationId));
				organization8 = criteria7.list();
			} else if (organization8.size() == 0) {
				Criteria criteria8 = session
						.createCriteria(EmployeeLeaveEligibility.class);
				criteria8.add(Restrictions.eq("deleteFlag", false));
				criteria8
						.add(Restrictions.eq("organizationId", organizationId));
				organization9 = criteria8.list();
			} else if (organization9.size() == 0) {
				Criteria criteria9 = session
						.createCriteria(EmployeeMarital.class);
				criteria9.add(Restrictions.eq("deleteFlag", false));
				criteria9
						.add(Restrictions.eq("organizationId", organizationId));
				organization10 = criteria9.list();
			} else if (organization10.size() == 0) {
				Criteria criteria10 = session
						.createCriteria(EmployeePersonal.class);
				criteria10.add(Restrictions.eq("deleteFlag", false));
				criteria10.add(Restrictions
						.eq("organizationId", organizationId));
				organization11 = criteria10.list();
			} else if (organization11.size() == 0) {
				Criteria criteria11 = session
						.createCriteria(EmployeeVisa.class);
				criteria11.add(Restrictions.eq("deleteFlag", false));
				criteria11.add(Restrictions
						.eq("organizationId", organizationId));
				organization12 = criteria11.list();
			} else if (organization12.size() == 0) {
				Criteria criteria12 = session.createCriteria(Holiday.class);
				criteria12.add(Restrictions.eq("deleteFlag", false));
				criteria12.add(Restrictions
						.eq("organizationId", organizationId));
				organization13 = criteria12.list();
			} else if (organization13.size() == 0) {
				Criteria criteria13 = session.createCriteria(Idtype.class);
				criteria13.add(Restrictions.eq("deleteFlag", false));
				criteria13.add(Restrictions
						.eq("organizationId", organizationId));
				organization14 = criteria13.list();
			} else if (organization14.size() == 0) {
				Criteria criteria14 = session.createCriteria(LeaveType.class);
				criteria14.add(Restrictions.eq("deleteFlag", false));
				criteria14.add(Restrictions
						.eq("organizationId", organizationId));
				organization15 = criteria14.list();
			} else if (organization15.size() == 0) {
				Criteria criteria15 = session.createCriteria(Role.class);
				criteria15.add(Restrictions.eq("deleteFlag", false));
				criteria15.add(Restrictions
						.eq("organizationId", organizationId));
				organization16 = criteria15.list();
			} else if (organization16.size() == 0) {
				Criteria criteria16 = session.createCriteria(VisaType.class);
				criteria16.add(Restrictions.eq("deleteFlag", false));
				criteria16.add(Restrictions
						.eq("organizationId", organizationId));
				organization17 = criteria16.list();
			}

			if (organization1.size() != 0 || organization2.size() != 0
					|| organization3.size() != 0 || organization4.size() != 0
					|| organization5.size() != 0 || organization6.size() != 0
					|| organization7.size() != 0 || organization8.size() != 0
					|| organization9.size() != 0 || organization10.size() != 0
					|| organization11.size() != 0 || organization12.size() != 0
					|| organization13.size() != 0 || organization14.size() != 0
					|| organization15.size() != 0 || organization16.size() != 0
					|| organization17.size() != 0) {
				status = 2;
				return status;
			}
			if (organization != null && organization1.size() == 0
					&& organization2.size() == 0 && organization3.size() == 0
					&& organization4.size() == 0 && organization5.size() == 0
					&& organization6.size() == 0 && organization7.size() == 0
					&& organization8.size() == 0 && organization9.size() == 0
					&& organization10.size() == 0 && organization11.size() == 0
					&& organization12.size() == 0 && organization13.size() == 0
					&& organization14.size() == 0 && organization15.size() == 0
					&& organization16.size() == 0 && organization17.size() == 0) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				organization.setUpdateDate(date.toString());
				organization.setUpdateBy(null);
				organization.setDeleteFlag(true);

				session.update(organization);
				session.getTransaction().commit();
				session.beginTransaction();

				Login check = (Login) session
						.createCriteria(Login.class)
						.add(Restrictions.eq("loginName",
								organization.getOrganizationEmail()))
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

	public int deleteOrganizationHard(int organizationId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			Organization organization = getOrganizationById(organizationId);
			if (organization != null) {
				organization.setDeleteFlag(true);
				organization.setUpdateBy(null);
				status = 1;
				return status;
			} else {
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

}
