package com.prov.hrm.employeeleaveeligibility;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.leavetype.LeaveType;

public class EmployeeLeaveEligibility {

	private Integer empleaveeligibilityId;
	private Integer organizationId;
	
	private Employee employee;
	
	private LeaveType leaveType;
	private String fromDate;
	private String toDate;
	private int eligibilitydays;
	private Integer leaveReportingHead;
	private Integer leaveReportingTo;
	private Integer leaveReportingHr;

	@JsonIgnore
	private Integer insertBy;
	@JsonIgnore
	private String insertDate;
	@JsonIgnore
	private Integer updateBy;
	@JsonIgnore
	private String updateDate;
	@JsonIgnore
	private boolean deleteFlag;

	public EmployeeLeaveEligibility() {
	}

	public Integer getEmpleaveeligibilityId() {
		return empleaveeligibilityId;
	}

	public void setEmpleaveeligibilityId(Integer empleaveeligibilityId) {
		this.empleaveeligibilityId = empleaveeligibilityId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public int getEligibilitydays() {
		return eligibilitydays;
	}

	
	public void setEligibilitydays(int eligibilitydays) {
		this.eligibilitydays = eligibilitydays;
	}
	public Integer getLeaveReportingHead() {
		return leaveReportingHead;
	}

	public void setLeaveReportingHead(Integer leaveReportingHead) {
		this.leaveReportingHead = leaveReportingHead;
	}

	public Integer getLeaveReportingTo() {
		return leaveReportingTo;
	}

	public void setLeaveReportingTo(Integer leaveReportingTo) {
		this.leaveReportingTo = leaveReportingTo;
	}

	public Integer getLeaveReportingHr() {
		return leaveReportingHr;
	}

	public void setLeaveReportingHr(Integer leaveReportingHr) {
		this.leaveReportingHr = leaveReportingHr;
	}
	public Integer getInsertBy() {
		return insertBy;
	}

	public void setInsertBy(Integer insertBy) {
		this.insertBy = insertBy;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "{empleaveeligibilityId=" + empleaveeligibilityId
				+ ", organizationId=" + organizationId + ", employee="
				+ employee + ", leaveType=" + leaveType + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", eligibilitydays="
				+ eligibilitydays + ", insertBy=" + insertBy + ", insertDate="
				+ insertDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", deleteFlag=" + deleteFlag + "}\n";
	}

	

	

}
