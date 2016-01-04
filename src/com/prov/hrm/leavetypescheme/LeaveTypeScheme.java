package com.prov.hrm.leavetypescheme;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class LeaveTypeScheme 
{
	
	private Integer leaveTypeSchemeId;
	private Integer organizationId;
	private String leavetypeSchemeName;
	private String leavetypeSchemeDescription;
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
	
	
	public LeaveTypeScheme(Integer leaveTypeSchemeId) {
		super();
		this.leaveTypeSchemeId = leaveTypeSchemeId;
	}
	public LeaveTypeScheme() {
		super();
		
	}
	public Integer getLeaveTypeSchemeId() {
		return leaveTypeSchemeId;
	}
	public void setLeaveTypeSchemeId(Integer leaveTypeSchemeId) {
		this.leaveTypeSchemeId = leaveTypeSchemeId;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public String getLeavetypeSchemeName() {
		return leavetypeSchemeName;
	}
	public void setLeavetypeSchemeName(String leavetypeSchemeName) {
		this.leavetypeSchemeName = leavetypeSchemeName;
	}
	public String getLeavetypeSchemeDescription() {
		return leavetypeSchemeDescription;
	}
	public void setLeavetypeSchemeDescription(String leavetypeSchemeDescription) {
		this.leavetypeSchemeDescription = leavetypeSchemeDescription;
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
		return "LeaveTypeScheme [leaveTypeSchemeId=" + leaveTypeSchemeId
				+ ", organizationId=" + organizationId
				+ ", leavetypeSchemeName=" + leavetypeSchemeName
				+ ", leavetypeSchemeDescription=" + leavetypeSchemeDescription
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag + "]";
	}
	
	
	
	

}
