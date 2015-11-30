package com.prov.hrm.role;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.department.Department;
import com.prov.hrm.designation.Designation;
import com.prov.hrm.screenauths.ScreenAuth;

public class Role  {

	private Integer roleId;
	private Department department;
	private Designation designation;
	@JsonIgnore
	private int organizationId;
	private String role;
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
	private Set<ScreenAuth> screenauths = new HashSet<ScreenAuth>(0);

	public Role(Integer roleId) {
		super();
		this.roleId = roleId;
	}

	public Role() {
		super();
	}
	public Set<ScreenAuth> getScreenauths() {
		return screenauths;
	}

	public void setScreenauths(Set<ScreenAuth> screenauths) {
		this.screenauths = screenauths;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Department getTbldepartment() {
		return this.department;
	}

	public void setTbldepartment(Department department) {
		this.department = department;
	}

	public Designation getTbldesignation() {
		return this.designation;
	}

	public void setTbldesignation(Designation designation) {
		this.designation = designation;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
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
		return "{roleId=" + roleId + ", department=" + department
				+ ", designation=" + designation + ", organizationId="
				+ organizationId + ", role=" + role + ", insertBy=" + insertBy
				+ ", insertDate=" + insertDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", deleteFlag=" + deleteFlag
				+ ", screenauths=" + screenauths + "}";
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	
	
}
