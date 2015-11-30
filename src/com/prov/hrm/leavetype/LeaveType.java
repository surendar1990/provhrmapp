package com.prov.hrm.leavetype;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LeaveType {

	private Integer leavetypeId;
	@JsonIgnore
	private int organizationId;
	private String leavetype;
	private String fromDate;
	private String toDate;
	private int eligibleDays;
	private String leaveDescription;
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

	public LeaveType() {
		super();
	}

	public LeaveType(Integer leavetypeId) {
		super();
		this.leavetypeId = leavetypeId;
	}

	public Integer getLeavetypeId() {
		return this.leavetypeId;
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

	public void setLeavetypeId(Integer leavetypeId) {
		this.leavetypeId = leavetypeId;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getLeavetype() {
		return this.leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	public int getEligibleDays() {
		return this.eligibleDays;
	}

	public void setEligibleDays(int eligibleDays) {
		this.eligibleDays = eligibleDays;
	}

	public String getLeaveDescription() {
		return this.leaveDescription;
	}

	public void setLeaveDescription(String leaveDescription) {
		this.leaveDescription = leaveDescription;
	}

	public Integer getInsertBy() {
		return this.insertBy;
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

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "{leavetypeId=" + leavetypeId + ", organizationId="
				+ organizationId + ", leavetype=" + leavetype
				+ ", eligibleDays=" + eligibleDays + ", leaveDescription="
				+ leaveDescription + ", insertBy=" + insertBy + ", insertDate="
				+ insertDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", deleteFlag=" + deleteFlag + "}";
	}

}
