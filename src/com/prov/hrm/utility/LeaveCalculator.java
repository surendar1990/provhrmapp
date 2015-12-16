package com.prov.hrm.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.prov.hrm.department.Department;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.employeeleaveeligibility.EmployeeLeaveEligibility;
import com.prov.hrm.leavetype.LeaveType;

public class LeaveCalculator {

	public List<LeaveCalculation> LeaveCalculator(int organizationId,
			int employeeId) throws ParseException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		// List<LeaveType> leave1 = new ArrayList<>();
		session.beginTransaction();
		HashMap<String, String> list = Getaccountyear.getaccountingyear(
				organizationId, "C");
		List<LeaveCalculation> ls = new ArrayList<LeaveCalculation>();
		SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
		Date datefrom = sdfOut.parse(list.get("fromdate"));
		Date dateto = sdfOut.parse(list.get("todate"));
		String fromDate = sdinput.format(datefrom);
		String toDate = sdinput.format(dateto);
		System.out.println(fromDate + "" + toDate);
		Criteria criteria = session
				.createCriteria(EmployeeLeaveEligibility.class)
				.add(Restrictions.eq("employee", new Employee(employeeId)))
				.add(Restrictions.eq("organizationId", organizationId))
				.add(Restrictions.between("fromDate", fromDate, toDate))
				.add(Restrictions.between("toDate", fromDate, toDate))
				.add(Restrictions.eq("deleteFlag", false));
		Iterator<EmployeeLeaveEligibility> ite1 = criteria.list().iterator();
		LeaveCalculation leaveCalc = null;
		while (ite1.hasNext()) {
			EmployeeLeaveEligibility empl = (EmployeeLeaveEligibility) ite1
					.next();
			Criterion rhs = Restrictions.eq("status", "p");
			Criterion lhs = Restrictions.eq("status", "a");
			Criteria crteria1 = session
					.createCriteria(EmployeeLeave.class)
					.add(Restrictions.eq("employee", new Employee(employeeId)))
					.add(Restrictions.eq("organizationId", organizationId))
					.add(Restrictions.or(lhs, rhs))
					.add(Restrictions.eq("leaveType", new LeaveType(empl
							.getLeaveType().getLeavetypeId())))
					.add(Restrictions.between("fromDate", empl.getFromDate(),
							empl.getToDate()))
					.add(Restrictions.between("toDate", empl.getFromDate(),
							empl.getToDate()))
					.add(Restrictions.eq("deleteFlag", false))
					.setProjection(Projections.sum("totalDays"));
			leaveCalc = new LeaveCalculation();
			leaveCalc.setLeavetype(empl.getLeaveType().getLeavetype());
			leaveCalc.setLeavetypeId(empl.getLeaveType().getLeavetypeId());
			Float calc = (Float) crteria1.list().get(0);
			leaveCalc.setAvailabledays(calc != null ? empl.getEligibilitydays()
					- calc : empl.getEligibilitydays());
			leaveCalc.setEligibledays(empl.getEligibilitydays());
			ls.add(leaveCalc);

		}

		return ls;
	}

	public List<LeaveCalculation> LeaveCalculatorEdit(int employeeleaveId)
			throws ParseException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		// List<LeaveType> leave1 = new ArrayList<>();
		session.beginTransaction();
		EmployeeLeave empleave = (EmployeeLeave) session.get(
				EmployeeLeave.class, employeeleaveId);
		HashMap<String, String> list = Getaccountyear.getaccountingyear(
				empleave.getOrganizationId(), "C");
		List<LeaveCalculation> ls = new ArrayList<LeaveCalculation>();
		SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
		Date datefrom = sdfOut.parse(list.get("fromdate"));
		Date dateto = sdfOut.parse(list.get("todate"));
		String fromDate = sdinput.format(datefrom);
		String toDate = sdinput.format(dateto);
		System.out.println(fromDate + "" + toDate);
		Criteria criteria = session
				.createCriteria(EmployeeLeaveEligibility.class)
				.add(Restrictions.eq("employee", new Employee(empleave
						.getEmployee().getEmployeeId())))
				.add(Restrictions.eq("organizationId",
						empleave.getOrganizationId()))
				.add(Restrictions.between("fromDate", fromDate, toDate))
				.add(Restrictions.between("toDate", fromDate, toDate))
				.add(Restrictions.eq("deleteFlag", false));
		Iterator<EmployeeLeaveEligibility> ite1 = criteria.list().iterator();
		LeaveCalculation leaveCalc = null;

		while (ite1.hasNext()) {
			EmployeeLeaveEligibility empl = (EmployeeLeaveEligibility) ite1
					.next();
			Criterion rhs = Restrictions.eq("status", "p");
			Criterion lhs = Restrictions.eq("status", "a");
			Criteria crteria1 = session
					.createCriteria(EmployeeLeave.class)
					.add(Restrictions.eq("employee", new Employee(empleave
							.getEmployee().getEmployeeId())))
					.add(Restrictions.eq("organizationId",
							empleave.getOrganizationId()))
					.add(Restrictions.or(lhs, rhs))
					.add(Restrictions.eq("leaveType", new LeaveType(empl
							.getLeaveType().getLeavetypeId())))
					.add(Restrictions.between("fromDate", empl.getFromDate(),
							empl.getToDate()))
					.add(Restrictions.between("toDate", empl.getFromDate(),
							empl.getToDate()))
					.add(Restrictions.eq("deleteFlag", false))
					.setProjection(Projections.sum("totalDays"));
			leaveCalc = new LeaveCalculation();
			leaveCalc.setLeavetype(empl.getLeaveType().getLeavetype());
			leaveCalc.setLeavetypeId(empl.getLeaveType().getLeavetypeId());
			if (empleave.getLeaveType().getLeavetypeId() == empl.getLeaveType()
					.getLeavetypeId()) {
				Float calc = (Float) crteria1.list().get(0);
				leaveCalc.setAvailabledays(calc != null ? (empl
						.getEligibilitydays() - calc) + empleave.getTotalDays()
						: empl.getEligibilitydays());
			} else {
				Float calc = (Float) crteria1.list().get(0);
				leaveCalc.setAvailabledays(calc != null ? (empl
						.getEligibilitydays() - calc) : empl
						.getEligibilitydays());
			}
			leaveCalc.setEligibledays(empl.getEligibilitydays());
			ls.add(leaveCalc);
		}

		return ls;
	}

}
