package com.prov.hrm.employeevisa;

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
import com.prov.hrm.utility.SessionFactoryUtil;
import com.prov.hrm.visatype.VisaType;

public class EmployeeVisaDAOImpl implements EmployeeVisaDAO {

	// Return all record from tblempvisa
	@Override
	public List<EmployeeVisa> getAllEmployeeVisa(int organizationid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			List<EmployeeVisa> employeevisa=new ArrayList<EmployeeVisa>();
			List<EmployeeVisa> employeevisa1=new ArrayList<EmployeeVisa>();
			
			Criteria criteria = session.createCriteria(EmployeeVisa.class);
			criteria.add(Restrictions.eq("organizationId", organizationid));
			criteria.add(Restrictions.eq("deleteFlag", false));
			employeevisa1=criteria.list();
			session.getTransaction().commit();
			Iterator<EmployeeVisa> ite = employeevisa1.iterator();
			 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			while(ite.hasNext()) {
				EmployeeVisa employee=(EmployeeVisa)ite.next();
				Date passport = sdinput.parse(employee.getVisaValidity());	
				employee.setVisaValidity(sdfOut.format(passport));
				employeevisa.add(employee);
			}
			
			return employeevisa;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
			
		}

	}

	// Return a record from tblempvisa for specific empvisaid
	@Override
	public EmployeeVisa getEmployeeVisaById(int employeeVisaId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();

			return (EmployeeVisa) session.get(EmployeeVisa.class,
					employeeVisaId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	// Insert a record into tblempvisa
	@Override
	public int addEmployeeVisa(EmployeeVisa employeevisa)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			 SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			 
				Criteria criteria=session.createCriteria(EmployeeVisa.class);
				criteria.add(Restrictions.eq("organizationId",employeevisa.getOrganizationId()));
				criteria.add(Restrictions.eq("employee", new Employee(employeevisa.getEmployee().getEmployeeId())));
				criteria.add(Restrictions.eq("visaType", new VisaType(employeevisa.getVisaType().getVisaTypeId())));
				
				if(criteria.list().size()==0){
			 employeevisa.setUpdateDate(date.toString());
			 employeevisa.setInsertDate(date.toString());
			 Date toDate = indate.parse(employeevisa.getVisaValidity());
			 employeevisa.setVisaValidity(outdate.format(toDate));
			session.save(employeevisa);
			status = 1;
				}else {
					Iterator<EmployeeVisa> iterator=criteria.list().iterator();
					while(iterator.hasNext()){
						EmployeeVisa empvisa=iterator.next();
						if(empvisa.getDeleteFlag()==true){
							empvisa.setDeleteFlag(false);
							empvisa.setUpdateDate(date.toString());
							empvisa.setInsertDate(date.toString());
							 Date toDate = indate.parse(employeevisa.getVisaValidity());
							 empvisa.setVisaValidity(outdate.format(toDate));
							 session.update(empvisa);
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

	// Update a record in tblempvisa for specific emplvisaid
	@Override
	public int updateEmployeeVisa(EmployeeVisa employeevisa)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			 SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			
			 Criteria criteria=session.createCriteria(EmployeeVisa.class);
				criteria.add(Restrictions.eq("organizationId",employeevisa.getOrganizationId()));
				criteria.add(Restrictions.ne("empvisaId",employeevisa.getEmpvisaId()));
				criteria.add(Restrictions.eq("employee", new Employee(employeevisa.getEmployee().getEmployeeId())));
				criteria.add(Restrictions.eq("visaType", new VisaType(employeevisa.getVisaType().getVisaTypeId())));
				
				if(criteria.list().size()==0){
			employeevisa.setUpdateDate(date.toString());
			Date toDate = indate.parse(employeevisa.getVisaValidity());
			 employeevisa.setVisaValidity(outdate.format(toDate));
			session.update(employeevisa);
			status = 1;
				}else{
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

	// Soft Delete a record from tblempvisa for specific empvisaid
	public int deleteEmployeeVisa(int employeevisaid) {
		int status = 0;
		try {
			EmployeeVisa employeemarital = getEmployeeVisaById(employeevisaid);
			employeemarital.setUpdateBy(null);
			employeemarital.setDeleteFlag(true);
			updateEmployeeVisa(employeemarital);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		}
	}

	// Hard Delete a record from tblempvisa for specific empvisaid

	public int deleteEmployeeVisaHard(int employeevisaid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeVisa employeemarital = getEmployeeVisaById(employeevisaid);
			session.delete(employeemarital);
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

	@Override
	public List<EmployeeVisa> getEmployeeVisaByEmployeeId(int employeeId, int organizationId) 
		throws HibernateException, ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			try {
				session.beginTransaction();
				List<EmployeeVisa> employeevisa=new ArrayList<EmployeeVisa>();
				List<EmployeeVisa> employeevisa1=new ArrayList<EmployeeVisa>();
				Criteria criteria = session.createCriteria(EmployeeVisa.class);
				criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
				criteria.add(Restrictions.eq("organizationId", organizationId));
				criteria.add(Restrictions.eq("deleteFlag", false));
				employeevisa1=criteria.list();
				session.getTransaction().commit();
				Iterator<EmployeeVisa> ite = employeevisa1.iterator();
				 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
				 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
				while(ite.hasNext()) {
					EmployeeVisa employee=(EmployeeVisa)ite.next();
					Date passport = sdinput.parse(employee.getVisaValidity());	
					employee.setVisaValidity(sdfOut.format(passport));
					employeevisa.add(employee);
				}
				
				return employeevisa;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				session.close();
				
			}
		}

	}


