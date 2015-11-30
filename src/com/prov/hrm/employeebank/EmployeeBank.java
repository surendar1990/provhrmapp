package com.prov.hrm.employeebank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import com.prov.hrm.bank.Bank;
import com.prov.hrm.employee.Employee;

public class EmployeeBank {

	private Integer empbankId;
	@JsonUnwrapped
	@JsonView
	private Employee employee;
	@JsonUnwrapped
	private Bank bank;
	@JsonIgnore
	private int organizationId;
	private String beneficiaryName;
	private long accountNumber;
	private String branchName;
	private String ifscCode;
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

	public EmployeeBank(Integer empbankId) {
		super();
		this.empbankId = empbankId;
	}

	public EmployeeBank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getEmpbankId() {
		return this.empbankId;
	}

	public void setEmpbankId(Integer empbankId) {
		this.empbankId = empbankId;
	}

	
	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getBeneficiaryName() {
		return this.beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public long getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfscCode() {
		return this.ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Integer getInsertBy() {
		return this.insertBy;
	}

	public void setInsertBy(Integer insertBy) {
		this.insertBy = insertBy;
	}

	public Integer getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getUpdateDate() {
		return updateDate;
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
		return "{empbankId=" + empbankId + ", employee=" + employee + ", bank="
				+ bank + ", organizationId=" + organizationId
				+ ", beneficiaryName=" + beneficiaryName + ", accountNumber="
				+ accountNumber + ", branchName=" + branchName + ", ifscCode="
				+ ifscCode + ", insertBy=" + insertBy + ", insertDate="
				+ insertDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", deleteFlag=" + deleteFlag + "}";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
