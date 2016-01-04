package com.prov.hrm.leavetype;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LeaveType {

	private Integer leavetypeId;
	@JsonIgnore
	private int organizationId;
	private String leavetype;
	
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
		return "LeaveType [ organizationId="
				+ organizationId + ", leavetype=" + leavetype
				+ ", leaveDescription=" + leaveDescription + ", insertBy="
				+ insertBy + ", insertDate=" + insertDate + ", updateBy="
				+ updateBy + ", updateDate=" + updateDate + ", deleteFlag="
				+ deleteFlag + "]";
	}

	

}
