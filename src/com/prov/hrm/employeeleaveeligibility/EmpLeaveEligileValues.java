package com.prov.hrm.employeeleaveeligibility;

import java.sql.Date;

public class EmpLeaveEligileValues
{
	int empleaveeligibility_id =0;
	int organization_id=0;
    int employee_id=0;
	String from_date=null;
	String to_date=null;
	int eligibilitydays=0;
	int leavetype_id=0;
    String leavetype=null;
	int totaleligible_days=0;
	String leave_description=null;
	String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getEmpleaveeligibility_id() {
		return empleaveeligibility_id;
	}
	public void setEmpleaveeligibility_id(int empleaveeligibility_id) {
		this.empleaveeligibility_id = empleaveeligibility_id;
	}
	public int getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(int organization_id) {
		this.organization_id = organization_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public int getEligibilitydays() {
		return eligibilitydays;
	}
	public void setEligibilitydays(int eligibilitydays) {
		this.eligibilitydays = eligibilitydays;
	}
	public int getLeavetype_id() {
		return leavetype_id;
	}
	public void setLeavetype_id(int leavetype_id) {
		this.leavetype_id = leavetype_id;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public int getTotaleligible_days() {
		return totaleligible_days;
	}
	public void setTotaleligible_days(int totaleligible_days) {
		this.totaleligible_days = totaleligible_days;
	}
	public String getLeave_description() {
		return leave_description;
	}
	public void setLeave_description(String leave_description) {
		this.leave_description = leave_description;
	}
	@Override
	public String toString() {
		return "EmpLeaveEligileValues [empleaveeligibility_id="
				+ empleaveeligibility_id + ", organization_id="
				+ organization_id + ", employee_id=" + employee_id
				+ ", from_date=" + from_date + ", to_date=" + to_date
				+ ", eligibilitydays=" + eligibilitydays + ", leavetype_id="
				+ leavetype_id + ", leavetype=" + leavetype
				+ ", totaleligible_days=" + totaleligible_days
				+ ", leave_description=" + leave_description + "]";
	}	
	
}
