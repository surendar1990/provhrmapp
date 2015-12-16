package com.prov.hrm.employeeleaveeligibility;

import java.sql.Date;

public class EmpLeaveEligileValues
{
	Integer empleaveeligibility_id;
	Integer organization_id;
	Integer employee_id;
	String from_date;
	String to_date;
	Integer eligibilitydays;
	Integer leavetype_id;
    String leavetype;
    Integer totaleligible_days;
	String leave_description;
	String flag;
	Integer leave_reporting_head;
	Integer leave_reporting_to;
	Integer leave_reporting_hr;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Integer getEmpleaveeligibility_id() {
		return empleaveeligibility_id;
	}
	public void setEmpleaveeligibility_id(Integer empleaveeligibility_id) {
		this.empleaveeligibility_id = empleaveeligibility_id;
	}
	public Integer getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
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
	public Integer getEligibilitydays() {
		return eligibilitydays;
	}
	public void setEligibilitydays(Integer eligibilitydays) {
		this.eligibilitydays = eligibilitydays;
	}
	public Integer getLeavetype_id() {
		return leavetype_id;
	}
	public void setLeavetype_id(Integer leavetype_id) {
		this.leavetype_id = leavetype_id;
	}
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public Integer getTotaleligible_days() {
		return totaleligible_days;
	}
	public void setTotaleligible_days(Integer totaleligible_days) {
		this.totaleligible_days = totaleligible_days;
	}
	
	public String getLeave_description() {
		return leave_description;
	}
	public void setLeave_description(String leave_description) {
		this.leave_description = leave_description;
	}
	public Integer getLeave_reporting_head() {
		return leave_reporting_head;
	}
	public void setLeave_reporting_head(Integer leave_reporting_head) {
		this.leave_reporting_head = leave_reporting_head;
	}
	public Integer getLeave_reporting_to() {
		return leave_reporting_to;
	}
	public void setLeave_reporting_to(Integer leave_reporting_to) {
		this.leave_reporting_to = leave_reporting_to;
	}
	public Integer getLeave_reporting_hr() {
		return leave_reporting_hr;
	}
	public void setLeave_reporting_hr(Integer leave_reporting_hr) {
		this.leave_reporting_hr = leave_reporting_hr;
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
