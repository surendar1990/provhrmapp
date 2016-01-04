package com.prov.hrm.employeeleaveeligibility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.leaveconfiguration.LeaveConfiguration;
import com.prov.hrm.leavetype.LeaveType;

public class EmployeeLeaveEligibility {

	private int empleaveeligibilityId;
	private int organizationId;
	private LeaveConfiguration leaveConfiguration;
	private Employee employee;
	private LeaveType leaveType;
	private Integer emonth;
	private Integer eyear;
	private Float eligibilitydays;
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

	public int getEmpleaveeligibilityId() {
		return empleaveeligibilityId;
	}

	public void setEmpleaveeligibilityId(int empleaveeligibilityId) {
		this.empleaveeligibilityId = empleaveeligibilityId;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public LeaveConfiguration getLeaveConfiguration() {
		return leaveConfiguration;
	}

	public void setLeaveConfiguration(LeaveConfiguration leaveConfiguration) {
		this.leaveConfiguration = leaveConfiguration;
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

	public Integer getEmonth() {
		return emonth;
	}

	public void setEmonth(Integer emonth) {
		this.emonth = emonth;
	}

	public Integer getEyear() {
		return eyear;
	}

	public void setEyear(Integer eyear) {
		this.eyear = eyear;
	}

	public Float getEligibilitydays() {
		return eligibilitydays;
	}

	public void setEligibilitydays(Float eligibilitydays) {
		this.eligibilitydays = eligibilitydays;
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
				+ ", organizationId=" + organizationId
				+ ", leaveConfiguration=" + leaveConfiguration + ", employee="
				+ employee + ", leaveType=" + leaveType + ", emonth=" + emonth
				+ ", eyear=" + eyear + ", eligibilitydays=" + eligibilitydays
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag + "}";
	}


	

	

}
