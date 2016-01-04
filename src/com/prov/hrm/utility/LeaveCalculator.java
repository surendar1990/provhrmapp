package com.prov.hrm.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.employeeleaveeligibility.EmployeeLeaveEligibility;
import com.prov.hrm.leaveconfiguration.LeaveConfiguration;
import com.prov.hrm.leavetypescheme.LeaveTypeScheme;

public class LeaveCalculator {

	public List<LeaveCalculation> LeaveCalculation(int organizationId,
			int employeeId) throws ParseException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		LeaveCalculation leaveCalc = null;
		 List<LeaveCalculation> ls=new ArrayList<LeaveCalculation>();
		Calendar now = Calendar.getInstance();
		int eyear = now.get(Calendar.YEAR);
		now.set(Calendar.YEAR,eyear);
		now.set(Calendar.DAY_OF_YEAR, 1);
		Calendar endyear=Calendar.getInstance();
		endyear.set(Calendar.YEAR, eyear);
		endyear.set(Calendar.MONTH, 11); 
		endyear.set(Calendar.DAY_OF_MONTH, 31); 
		Employee getempleavetype = (Employee) session.get(Employee.class,
					employeeId);
			Criteria leaveconfig = session
					.createCriteria(LeaveConfiguration.class);
			leaveconfig.add(Restrictions.eq("organizationId", organizationId));
			leaveconfig.add(Restrictions.eq("leaveTypeScheme",
					new LeaveTypeScheme(getempleavetype.getLeaveTypeScheme()
							.getLeaveTypeSchemeId())));
			leaveconfig.add(Restrictions.eq("deleteFlag", false));
			System.out.println(leaveconfig.list().size());
			Iterator<LeaveConfiguration> ite1 = leaveconfig.list().iterator();
			while (ite1.hasNext()) {
				LeaveConfiguration config = (LeaveConfiguration) ite1.next();
				// This below coding belongs to Employee leave eligibility Month basics
				if(config.getLeavePeriod().equals("M"))
				{
				SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
				String startdate=sdinput.format(now.getTime());
				String enddate=sdinput.format(endyear.getTime());
				Criteria availeddays = session
						.createCriteria(EmployeeLeave.class)
						.add(Restrictions.eq("employee", new Employee(employeeId)))
						.add(Restrictions.eq("organizationId", organizationId))
						.add(Restrictions.eq("leaveType", config.getLeavetype()))
						.add(Restrictions.eq("status", "a"))
						.add(Restrictions.between("fromDate", startdate,
								enddate))
						.add(Restrictions.between("toDate",  startdate,
								enddate))
						.add(Restrictions.eq("deleteFlag", false))
						.setProjection(Projections.sum("totalDays"));
				Float availed = (Float)availeddays.list().get(0);
				session.clear();
				Criteria accrualdays = session
						.createCriteria(EmployeeLeaveEligibility.class)
						.add(Restrictions.eq("employee", new Employee(
								employeeId)))
						.add(Restrictions.eq("organizationId", organizationId))
						.add(Restrictions.eq("leaveType", config.getLeavetype()))
						.add(Restrictions.eq("eyear", eyear))
						.add(Restrictions.eq("deleteFlag", false))
						.setProjection(Projections.sum("eligibilitydays"));
				session.clear();
				
				Criteria totalDays=session.createCriteria(LeaveConfiguration.class)
						.add(Restrictions.eq("organizationId", organizationId))
						.add(Restrictions.eq("leaveTypeScheme",
					new LeaveTypeScheme(getempleavetype.getLeaveTypeScheme()
							.getLeaveTypeSchemeId())))
							.add(Restrictions.eq("leavetype", config.getLeavetype()));
				LeaveConfiguration totaldays=(LeaveConfiguration) totalDays.list().get(0);
				System.out.println(totaldays.toString());
				System.out.println(totalDays.list().size());
				leaveCalc = new LeaveCalculation();
				Float total = null;
				System.out.println(totaldays.getLeavePeriod());
				if(totaldays.getLeavePeriod().equals("M"))
				{
					total= (float) (totaldays.getLeaveEligibledays() * 12);
				}
				else
				{
					total = (float)(totaldays.getLeaveEligibledays() * 1);
				}
				
				
				Criteria pendingdays = session
						.createCriteria(EmployeeLeave.class)
						.add(Restrictions.eq("employee", new Employee(employeeId)))
						.add(Restrictions.eq("organizationId", organizationId))
						.add(Restrictions.eq("leaveType", config.getLeavetype()))
						.add(Restrictions.eq("status", "p"))
						.add(Restrictions.between("fromDate", startdate,
								enddate))
						.add(Restrictions.between("toDate",  startdate,
								enddate))
						.add(Restrictions.eq("deleteFlag", false))
						.setProjection(Projections.sum("totalDays"));
				Float pending = (Float)pendingdays.list().get(0);
				
				Float accrualed = (Float) accrualdays.list().get(0);
				leaveCalc = new LeaveCalculation();
				leaveCalc.setTotalDays(total);
				leaveCalc.setLeaveType(config.getLeavetype().getLeavetype());
				leaveCalc
						.setLeavetypeId(config.getLeavetype().getLeavetypeId());
				leaveCalc.setAvailedDays(availed);
				leaveCalc.setAccrualDays(accrualed);
				leaveCalc.setPendingDays(pending);
				ls.add(leaveCalc);
			}
				// This below coding belongs to Employee leave eligibility YEAR basics
				else
				{
					SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
					String startdate=sdinput.format(now.getTime());
					String enddate=sdinput.format(endyear.getTime());
					Criteria availeddays = session
							.createCriteria(EmployeeLeave.class)
							.add(Restrictions.eq("employee", new Employee(employeeId)))
							.add(Restrictions.eq("organizationId", organizationId))
							.add(Restrictions.eq("leaveType", config.getLeavetype()))
							.add(Restrictions.eq("status", "a"))
							.add(Restrictions.between("fromDate", startdate,
									enddate))
							.add(Restrictions.between("toDate",  startdate,
									enddate))
							.add(Restrictions.eq("deleteFlag", false))
							.setProjection(Projections.sum("totalDays"));
					Float availed = (Float)availeddays.list().get(0);
					session.clear();
					Criteria accrualdays = session
							.createCriteria(EmployeeLeaveEligibility.class)
							.add(Restrictions.eq("employee", new Employee(
									employeeId)))
							.add(Restrictions.eq("organizationId", organizationId))
							.add(Restrictions.eq("leaveType", config.getLeavetype()))
							.add(Restrictions.eq("eyear", eyear))
							.add(Restrictions.eq("deleteFlag", false))
							.setProjection(Projections.sum("eligibilitydays"));
					session.clear();
					
					Criteria totalDays=session.createCriteria(LeaveConfiguration.class)
							.add(Restrictions.eq("organizationId", organizationId))
							.add(Restrictions.eq("leaveTypeScheme",
						new LeaveTypeScheme(getempleavetype.getLeaveTypeScheme()
								.getLeaveTypeSchemeId())))
								.add(Restrictions.eq("leavetype", config.getLeavetype()));
					LeaveConfiguration totaldays=(LeaveConfiguration) totalDays.list().get(0);
					System.out.println(totaldays.toString());
					System.out.println(totalDays.list().size());
					leaveCalc = new LeaveCalculation();
					Float total = null;
					System.out.println(totaldays.getLeavePeriod());
					if(totaldays.getLeavePeriod().equals("M"))
					{
						total= (float) (totaldays.getLeaveEligibledays() * 12);
					}
					else
					{
						total = (float)(totaldays.getLeaveEligibledays() * 1);
					}
					session.clear();
					Criteria pendingdays = session
							.createCriteria(EmployeeLeave.class)
							.add(Restrictions.eq("employee", new Employee(employeeId)))
							.add(Restrictions.eq("organizationId", organizationId))
							.add(Restrictions.eq("leaveType", config.getLeavetype()))
							.add(Restrictions.eq("status", "p"))
							.add(Restrictions.between("fromDate", startdate,
									enddate))
							.add(Restrictions.between("toDate",  startdate,
									enddate))
							.add(Restrictions.eq("deleteFlag", false))
							.setProjection(Projections.sum("totalDays"));
					Float pending = (Float)pendingdays.list().get(0);
					Float accrualed = (Float) accrualdays.list().get(0);
					leaveCalc = new LeaveCalculation();
					leaveCalc.setTotalDays(total);
					leaveCalc.setLeaveType(config.getLeavetype().getLeavetype());
					leaveCalc
							.setLeavetypeId(config.getLeavetype().getLeavetypeId());
					leaveCalc.setAvailedDays(availed);
					leaveCalc.setAccrualDays(accrualed);
					leaveCalc.setPendingDays(pending);
					ls.add(leaveCalc);
				}
			}
			
		
	session.getTransaction().commit();
	session.close();
		return ls;

		}
	
}