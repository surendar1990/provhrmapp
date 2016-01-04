package com.prov.hrm.leaveconfiguration;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.leavetypescheme.LeaveTypeScheme;

public class LeaveConfiguration
{
	private Integer leaveconfigurationId;
	private Integer organizationId;
	private LeaveTypeScheme leaveTypeScheme;
	private LeaveType leavetype;
	private String leavePeriod;
	private Integer leaveEligibledays;
	private String leaveEffectiveFrom;
	private String leaveEffectiveTo;
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
	
	public Integer getLeaveconfigurationId() {
		return leaveconfigurationId;
	}
	public void setLeaveconfigurationId(Integer leaveconfigurationId) {
		this.leaveconfigurationId = leaveconfigurationId;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	public String getLeavePeriod() {
		return leavePeriod;
	}
	public void setLeavePeriod(String leavePeriod) {
		this.leavePeriod = leavePeriod;
	}
	public Integer getLeaveEligibledays() {
		return leaveEligibledays;
	}
	public void setLeaveEligibledays(Integer leaveEligibledays) {
		this.leaveEligibledays = leaveEligibledays;
	}
	public String getLeaveEffectiveFrom() {
		return leaveEffectiveFrom;
	}
	public void setLeaveEffectiveFrom(String leaveEffectiveFrom) {
		this.leaveEffectiveFrom = leaveEffectiveFrom;
	}
	public String getLeaveEffectiveTo() {
		return leaveEffectiveTo;
	}
	public void setLeaveEffectiveTo(String leaveEffectiveTo) {
		this.leaveEffectiveTo = leaveEffectiveTo;
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
	public LeaveTypeScheme getLeaveTypeScheme() {
		return leaveTypeScheme;
	}
	public void setLeaveTypeScheme(LeaveTypeScheme leaveTypeScheme) {
		this.leaveTypeScheme = leaveTypeScheme;
	}
	public LeaveType getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(LeaveType leavetype) {
		this.leavetype = leavetype;
	}
	@Override
	public String toString() {
		return "LeaveConfiguration [leaveconfigurationId="
				+ leaveconfigurationId + ", organizationId=" + organizationId
				+ ", leaveTypeScheme=" + leaveTypeScheme + ", leavetype="
				+ leavetype + ", leavePeriod=" + leavePeriod
				+ ", leaveEligibledays=" + leaveEligibledays
				+ ", leaveEffectiveFrom=" + leaveEffectiveFrom
				+ ", leaveEffectiveTo=" + leaveEffectiveTo + ", insertBy="
				+ insertBy + ", insertDate=" + insertDate + ", updateBy="
				+ updateBy + ", updateDate=" + updateDate + ", deleteFlag="
				+ deleteFlag + "]";
	}
	
}
