package com.prov.hrm.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.organization.Organization;



public class Login 
{
	private int loginId;
	@JsonUnwrapped
	@JsonIgnoreProperties({"organizationLocation","organizationAddress","organizationContactNumber","organizationContactPerson","organizationUrl","organizationEmail"})
	private Organization organization;
	@JsonUnwrapped
	private Employee employee;
	private String loginName;
	@JsonIgnore
	private String loginPassword;
	@JsonIgnore
	private String encryptName;
	@JsonIgnore
	private String encryptPassword;
	@JsonIgnore
	private boolean superadminFlag;
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
	
	public Login()
	{
		
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getEncryptName() {
		return encryptName;
	}

	public void setEncryptName(String encryptName) {
		this.encryptName = encryptName;
	}

	public String getEncryptPassword() {
		return encryptPassword;
	}

	public void setEncryptPassword(String encryptPassword) {
		this.encryptPassword = encryptPassword;
	}

	public boolean isSuperadminFlag() {
		return superadminFlag;
	}

	public void setSuperadminFlag(boolean superadminFlag) {
		this.superadminFlag = superadminFlag;
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
		return "Login [loginId=" + loginId + ", organization="
				+ organization + ", loginName=" + loginName + ", loginPassword="
				+ loginPassword + ", encryptName=" + encryptName
				+ ", encryptPassword=" + encryptPassword + ", superadminFlag="
				+ superadminFlag + ", insertBy=" + insertBy + ", insertDate="
				+ insertDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", deleteFlag=" + deleteFlag + "]";
	}
	


}
