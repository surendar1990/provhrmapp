package com.prov.hrm.employeepersonal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.prov.hrm.country.Country;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.stateprovince.StateProvince;

public class EmployeePersonal {

	private Integer emppersonalId;
	
	private Employee employee;
	
	private StateProvince currentStateprovince;
	
	private Country permanentCountry;
	
	private Country currentCountry;
	
	private StateProvince permanentStateprovince;
	@JsonIgnore
	private int organizationId;
	private String fatherName;
	private String motherName;
	private String dateOfBirth;
	private String gender;
	private String maritalStatus;
	private String bloodGroup;
	private String alternateContactNumber;
	private String alternateEmailId;
	private String currentAddress1;
	private String currentAddress2;
	private String currentPlace;
	private int currentPincode;
	private String permanentAddress1;
	private String permanentAddress2;
	private String permanentPlace;
	private int permanentPincode;
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
	
	public EmployeePersonal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeePersonal(Integer emppersonalId) {
		super();
		this.emppersonalId = emppersonalId;
	}

	

	
	public Integer getEmppersonalId() {
		return this.emppersonalId;
	}

	public void setEmppersonalId(Integer emppersonalId) {
		this.emppersonalId = emppersonalId;
	}

	

	public StateProvince getCurrentStateprovince() {
		return this.currentStateprovince;
	}

	public void setCurrentStateprovince(
			StateProvince currentStateprovince) {
		this.currentStateprovince = currentStateprovince;
	}

	public Country getPermanentCountry() {
		return this.permanentCountry;
	}

	public void setPermanentCountry(
			Country permanentCountry) {
		this.permanentCountry = permanentCountry;
	}

	public Country getCurrentCountry() {
		return this.currentCountry;
	}

	public void setCurrentCountry(
			Country currentCountry) {
		this.currentCountry = currentCountry;
	}

	public StateProvince getPermanentStateprovince() {
		return this.permanentStateprovince;
	}

	public void setPermanentStateprovince(
			StateProvince permanentStateprovince) {
		this.permanentStateprovince = permanentStateprovince;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getAlternateContactNumber() {
		return this.alternateContactNumber;
	}

	public void setAlternateContactNumber(String alternateContactNumber) {
		this.alternateContactNumber = alternateContactNumber;
	}

	public String getAlternateEmailId() {
		return this.alternateEmailId;
	}

	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}

	public String getCurrentAddress1() {
		return this.currentAddress1;
	}

	public void setCurrentAddress1(String currentAddress1) {
		this.currentAddress1 = currentAddress1;
	}

	public String getCurrentAddress2() {
		return this.currentAddress2;
	}

	public void setCurrentAddress2(String currentAddress2) {
		this.currentAddress2 = currentAddress2;
	}

	public String getCurrentPlace() {
		return this.currentPlace;
	}

	public void setCurrentPlace(String currentPlace) {
		this.currentPlace = currentPlace;
	}

	public int getCurrentPincode() {
		return this.currentPincode;
	}

	public void setCurrentPincode(int currentPincode) {
		this.currentPincode = currentPincode;
	}

	public String getPermanentAddress1() {
		return this.permanentAddress1;
	}

	public void setPermanentAddress1(String permanentAddress1) {
		this.permanentAddress1 = permanentAddress1;
	}

	public String getPermanentAddress2() {
		return this.permanentAddress2;
	}

	public void setPermanentAddress2(String permanentAddress2) {
		this.permanentAddress2 = permanentAddress2;
	}

	public String getPermanentPlace() {
		return this.permanentPlace;
	}

	public void setPermanentPlace(String permanentPlace) {
		this.permanentPlace = permanentPlace;
	}

	public int getPermanentPincode() {
		return this.permanentPincode;
	}

	public void setPermanentPincode(int permanentPincode) {
		this.permanentPincode = permanentPincode;
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
		return "{emppersonalId=" + emppersonalId + ", employee=" + employee
				+ ", currentStateprovince="
				+ currentStateprovince
				+ ", permanentCountry="
				+ permanentCountry
				+ ", currentCountry="
				+ currentCountry
				+ ", permanentStateprovince="
				+ permanentStateprovince
				+ ", organizationId=" + organizationId + ", fatherName="
				+ fatherName + ", motherName=" + motherName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", maritalStatus="
				+ maritalStatus + ", bloodGroup=" + bloodGroup
				+ ", alternateContactNumber=" + alternateContactNumber
				+ ", alternateEmailId=" + alternateEmailId
				+ ", currentAddress1=" + currentAddress1 + ", currentAddress2="
				+ currentAddress2 + ", currentPlace=" + currentPlace
				+ ", currentPincode=" + currentPincode + ", permanentAddress1="
				+ permanentAddress1 + ", permanentAddress2="
				+ permanentAddress2 + ", permanentPlace=" + permanentPlace
				+ ", permanentPincode=" + permanentPincode + ", insertBy="
				+ insertBy + ", insertDate=" + insertDate + ", updateBy="
				+ updateBy + ", updateDate=" + updateDate + ", deleteFlag="
				+ deleteFlag + "}";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
