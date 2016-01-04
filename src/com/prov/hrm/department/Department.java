package com.prov.hrm.department;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Department {

	private Integer departmentId;
	@JsonIgnore
	private int organizationId;
	private String departmentName;
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
	
	
	public Department() {
		super();
	}
	@JsonIgnore
	public Department(Integer departmentId) {
		super();
		this.departmentId = departmentId;
	}
	
	public Integer getDepartmentId() {
		return this.departmentId;
	}
	
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	
	public String getDepartmentName() {
		return this.departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getInsertBy() {
		return this.insertBy;
	}

	public void setInsertBy(Integer insertBy) {
		this.insertBy = insertBy;
	}

	public String getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
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

	public boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	@Override
	public String toString() {
		return "{departmentId=" + departmentId + ", organizationId="
				+ organizationId + ", departmentName=" + departmentName
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag + ", tblroles=" + "}";
	}

}
