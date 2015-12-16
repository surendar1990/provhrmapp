package com.prov.hrm.organization;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.login.Login;

public class Organization {

	private Integer organizationId;
	private String organizationName;
	private String organizationLocation;
	private String organizationAddress;
	private String organizationContactNumber;
	private String organizationContactPerson;
	private String organizationUrl;
	private String organizationEmail;
	private String organizationLeaveAccountingMethod;
	@JsonIgnore
	private Set<Login> login;
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

	public Organization(Integer organizationId) {
		super();
		this.organizationId = organizationId;
	}

	public Organization() {
		super();
	}

	public String getOrganizationLeaveAccountingMethod() {
		return organizationLeaveAccountingMethod;
	}

	public void setOrganizationLeaveAccountingMethod(String organizationLeaveAccountingMethod) {
		this.organizationLeaveAccountingMethod = organizationLeaveAccountingMethod;
	}
	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return this.organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationLocation() {
		return this.organizationLocation;
	}

	public void setOrganizationLocation(String organizationLocation) {
		this.organizationLocation = organizationLocation;
	}

	public String getOrganizationAddress() {
		return this.organizationAddress;
	}

	public void setOrganizationAddress(String organizationAddress) {
		this.organizationAddress = organizationAddress;
	}

	public String getOrganizationContactNumber() {
		return this.organizationContactNumber;
	}

	public void setOrganizationContactNumber(String organizationContactNumber) {
		this.organizationContactNumber = organizationContactNumber;
	}

	public String getOrganizationContactPerson() {
		return this.organizationContactPerson;
	}

	public void setOrganizationContactPerson(String organizationContactPerson) {
		this.organizationContactPerson = organizationContactPerson;
	}

	public String getOrganizationUrl() {
		return this.organizationUrl;
	}

	public void setOrganizationUrl(String organizationUrl) {
		this.organizationUrl = organizationUrl;
	}

	public String getOrganizationEmail() {
		return this.organizationEmail;
	}

	public void setOrganizationEmail(String organizationEmail) {
		this.organizationEmail = organizationEmail;
	}

	
	public Set<Login> getLogin() {
		return login;
	}

	public void setLogin(Set<Login> login) {
		this.login = login;
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
		return "{organizationId=" + organizationId + ", organizationName="
				+ organizationName + ", organizationLocation="
				+ organizationLocation + ", organizationAddress="
				+ organizationAddress + ", organizationContactNumber="
				+ organizationContactNumber + ", organizationContactPerson="
				+ organizationContactPerson + ", organizationUrl="
				+ organizationUrl + ", organizationEmail=" + organizationEmail
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag + "}";
	}

}
