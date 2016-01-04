package com.prov.hrm.leaveconfiguration;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.employeeleaveeligibility.EmployeeLeaveEligibility;
import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.leavetypescheme.LeaveTypeScheme;
import com.prov.hrm.utility.SessionFactoryUtil;

public class LeaveConfigurationDAOImpl implements LeaveConfigurationDAO
{

	@Override
	public List<LeaveConfiguration> getAllLeaveConfigurations(int organizationId) 
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			List<LeaveConfiguration> leav=new ArrayList<>();
			Criteria criteria = session.createCriteria(LeaveConfiguration.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.addOrder(Order.desc("leaveEffectiveFrom"));
			session.getTransaction().commit();
			 Iterator<LeaveConfiguration> iterator= criteria.list().iterator();
			 while(iterator.hasNext()){
				 LeaveConfiguration leaveconf= iterator.next();
				Date efrom=sdinput.parse(leaveconf.getLeaveEffectiveFrom());
				leaveconf.setLeaveEffectiveFrom(sdfOut.format(efrom));
				leaveconf.setLeaveEffectiveTo(null); 
				leav.add(leaveconf);
			 }
			return leav;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			
			session.close();			
		}
	}
	
	
	/*@Override
	public List<LeaveConfiguration> getAllLeaveConfigurationsRecord(int organizationId) 
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();

		try {
			session.beginTransaction();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			List<LeaveConfiguration> leav=new ArrayList<>();
			Criteria criteria = session.createCriteria(LeaveConfiguration.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.isNotNull("leaveEffectiveTo"));
			session.getTransaction().commit();
			 Iterator<LeaveConfiguration> iterator= criteria.list().iterator();
			 while(iterator.hasNext()){
				 LeaveConfiguration leaveconf= iterator.next();
				Date efrom=sdinput.parse(leaveconf.getLeaveEffectiveFrom());
				Date eto=sdinput.parse(leaveconf.getLeaveEffectiveTo());
				leaveconf.setLeaveEffectiveFrom(sdfOut.format(efrom));
				leaveconf.setLeaveEffectiveTo(sdfOut.format(eto));
				
				leav.add(leaveconf);
			 }
			return leav;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			
			session.close();			
		}
	}*/
	
	
	@Override
	public LeaveConfiguration getLeaveConfigurationsById(int leaveconfigurationId)throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			LeaveConfiguration leaveconfig = (LeaveConfiguration) session.get(LeaveConfiguration.class,leaveconfigurationId);
			System.out.println(leaveconfig);
			session.getTransaction().commit();
			if (leaveconfig != null) {
				if (leaveconfig.isDeleteFlag()==false) {
					Date efrom = sdinput.parse(leaveconfig.getLeaveEffectiveFrom());
					leaveconfig.setLeaveEffectiveFrom(sdfOut.format(efrom));
					
					return leaveconfig;
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
	

	@Override
	public int addLeaveConfiguration(LeaveConfiguration leaveconfiguration)
			throws HibernateException, ConstraintViolationException,
			MySQLIntegrityConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			Criteria criteria=session.createCriteria(LeaveConfiguration.class);
			criteria.add(Restrictions.eq("leaveTypeScheme",new LeaveTypeScheme(leaveconfiguration.getLeaveTypeScheme().getLeaveTypeSchemeId())));
			criteria.add(Restrictions.eq("organizationId",leaveconfiguration.getOrganizationId()));
			criteria.add(Restrictions.eq("leavetype",new LeaveType(leaveconfiguration.getLeavetype().getLeavetypeId())));
			criteria.add(Restrictions.isNull("leaveEffectiveTo"));
			criteria.add(Restrictions.eq("deleteFlag", false));
			
			if(criteria.list().size()!=0){
				
				Iterator<LeaveConfiguration> ite=criteria.list().iterator();
				while(ite.hasNext()){
					Date effectivefrom = sdfOut.parse(leaveconfiguration.getLeaveEffectiveFrom());
					LeaveConfiguration leaveconf=new LeaveConfiguration();
					LeaveConfiguration leaveconfig2=ite.next();
					System.out.println(leaveconfig2.getLeaveEffectiveFrom()+"equals"+sdinput.format(effectivefrom));
					if(leaveconfig2.getLeaveEffectiveFrom().equals(sdinput.format(effectivefrom)))
					{
						
						status=3;
					}else{
					leaveconf.setOrganizationId(leaveconfig2.getOrganizationId());
					leaveconf.setLeaveTypeScheme(new LeaveTypeScheme(leaveconfig2.getLeaveTypeScheme().getLeaveTypeSchemeId()));
					leaveconf.setLeavetype(new LeaveType(leaveconfig2.getLeavetype().getLeavetypeId()));
					leaveconf.setLeavePeriod(leaveconfig2.getLeavePeriod());
					leaveconf.setLeaveEligibledays(leaveconfig2.getLeaveEligibledays());
					leaveconf.setLeaveEffectiveFrom(leaveconfig2.getLeaveEffectiveFrom());
					
					
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(sdfOut.parse(leaveconfiguration.getLeaveEffectiveFrom()));
					calendar.set(Calendar.DATE, -1);
					System.out.println("todate "+sdinput.format(calendar.getTime()));
					leaveconf.setLeaveEffectiveTo( sdinput.format(calendar.getTime()));
					leaveconf.setInsertBy(leaveconfig2.getInsertBy());
					leaveconf.setInsertDate(leaveconfig2.getInsertDate());
					leaveconf.setUpdateBy(leaveconfig2.getUpdateBy());
					leaveconf.setUpdateDate(leaveconfig2.getUpdateDate());
					System.out.println("Save " +leaveconf);
					session.save(leaveconf);
					session.getTransaction().commit();
					
					
					session.beginTransaction();
					leaveconfig2.setLeavePeriod(leaveconfiguration.getLeavePeriod());
					leaveconfig2.setLeaveEligibledays(leaveconfiguration.getLeaveEligibledays());
					leaveconfig2.setLeaveEffectiveFrom(sdinput.format(effectivefrom));
					leaveconfig2.setLeaveEffectiveTo(null);
					leaveconfig2.setInsertBy(leaveconfiguration.getInsertBy());
					leaveconfig2.setUpdateBy(leaveconfiguration.getUpdateBy());
					leaveconfig2.setInsertDate(date.toString());
					leaveconfig2.setUpdateDate(date.toString());
					System.out.println("Update "+leaveconfig2);
					session.update(leaveconfig2);
			status = 1;
			
				}}				
			}
			else{
				leaveconfiguration.setUpdateDate(date.toString());
				leaveconfiguration.setInsertDate(date.toString());
	
				Date effectivefrom = sdfOut.parse(leaveconfiguration.getLeaveEffectiveFrom());
				leaveconfiguration.setLeaveEffectiveFrom(sdinput.format(effectivefrom));
				
				session.save(leaveconfiguration);
				status = 1;
			}

			return status;
		}catch (Exception e) {
			e.printStackTrace();
			return status;
		}finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	@Override
	public int updateLeaveConfiguration(LeaveConfiguration leaveconfiguration)
			throws HibernateException, ConstraintViolationException,
			MySQLIntegrityConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			Criteria criteria=session.createCriteria(LeaveConfiguration.class);
			criteria.add(Restrictions.eq("leaveTypeScheme",new LeaveTypeScheme	(leaveconfiguration.getLeaveTypeScheme().getLeaveTypeSchemeId())));
			criteria.add(Restrictions.eq("organizationId",leaveconfiguration.getOrganizationId()));
			criteria.add(Restrictions.eq("leavetype",new LeaveType(leaveconfiguration.getLeavetype().getLeavetypeId())));
			criteria.add(Restrictions.isNull("leaveEffectiveTo"));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.ne("leaveconfigurationId", leaveconfiguration.getLeaveconfigurationId()));
			if(criteria.list().size()==0){
				session.clear();
				LeaveConfiguration leaveconfig = getLeaveConfigurationsById(leaveconfiguration.getLeaveconfigurationId());
			System.out.println(leaveconfig);
			if (leaveconfig != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				leaveconfiguration.setUpdateDate(date.toString());
				Date efrom=sdfOut.parse(leaveconfiguration.getLeaveEffectiveFrom());
				leaveconfiguration.setLeaveEffectiveFrom(sdinput.format(efrom));
				leaveconfiguration.setInsertDate(leaveconfig.getInsertDate());
				leaveconfiguration.setInsertBy(leaveconfig.getInsertBy());
				leaveconfiguration.setUpdateBy(leaveconfiguration.getUpdateBy());
				session.update(leaveconfiguration);
				
				status = 1;
				return status;
			} else {
				return status;
			}}

			else
			{
				return 5;
			}
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationException(e.getMessage(), e.getSQLException(),e.getCause().getMessage());
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return status;
		}  finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	@Override
	public int deleteLeaveConfiguration(int leaveconfigurationId) {
int status = 0;
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			LeaveConfiguration leaveconfig = getLeaveConfigurationsById(leaveconfigurationId);
			System.out.println(leaveconfig);
			List<LeaveConfiguration> leavetypelist=new ArrayList<>();
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("organizationId", leaveconfig.getOrganizationId()));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.eq("leaveTypeScheme", new LeaveTypeScheme(leaveconfig.getLeaveTypeScheme().getLeaveTypeSchemeId())));
			leavetypelist=criteria.list();
			
			System.out.println(leavetypelist);
			if(leavetypelist.size()!=0 )
			{
				status=2;
				return status;
			}
			if (leaveconfig != null &&  leavetypelist.size()==0 ) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
			System.out.println(leaveconfig.getLeaveEffectiveFrom());
//				leaveconfig.setLeaveEffectiveFrom(sdinput.format(effectivefrom));
				leaveconfig.setUpdateDate(date.toString());
				leaveconfig.setUpdateBy(null);
				leaveconfig.setDeleteFlag(true);
				Date effectivefrom = sdfOut.parse(leaveconfig.getLeaveEffectiveFrom());
				leaveconfig.setLeaveEffectiveFrom(sdinput.format(effectivefrom));
				session.update(leaveconfig);
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

	}}
