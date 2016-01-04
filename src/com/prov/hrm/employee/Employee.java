package com.prov.hrm.employee;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.prov.hrm.department.Department;
import com.prov.hrm.designation.Designation;
import com.prov.hrm.holiday.Holiday;
import com.prov.hrm.holidaylist.HolidayList;
import com.prov.hrm.leavetypescheme.LeaveTypeScheme;
import com.prov.hrm.login.Login;


public class Employee{

	private Integer employeeId;
	
	private Department department;
	
	private Designation designation;
	
	private LeaveTypeScheme leaveTypeScheme;
	
	@JsonIgnoreProperties({"department","designation","leaveTypeScheme","reportingTo","reportingHead","reportingHr","empType","dateOfJoining",
		"firstName","middleName","lastName","contactNumber","emailId","panNumber","passportNumber","passportValidity",
		"visaAvailablity","holidayList","workexpYears"})
	private Employee reportingTo;
	@JsonIgnoreProperties({"department","designation","leaveTypeScheme","reportingTo","reportingHead","reportingHr","empType","dateOfJoining",
		"firstName","middleName","lastName","contactNumber","emailId","panNumber","passportNumber","passportValidity",
		"visaAvailablity","holidayList","workexpYears"})
	private Employee reportingHead;
	@JsonIgnoreProperties({"department","designation","leaveTypeScheme","reportingTo","reportingHead","reportingHr","empType","dateOfJoining",
		"firstName","middleName","lastName","contactNumber","emailId","panNumber","passportNumber","passportValidity",
		"visaAvailablity","holidayList","workexpYears"})
	private Employee reportingHr;
	
	private HolidayList holidayList;
	
	//private Holiday holiday;
	@JsonIgnore
	private int organizationId;
	private int roleId;
	private String empType;
	private String empNo;
	private String dateOfJoining;
	private String firstName;
	private String middleName;
	private String lastName;
	private String contactNumber;
	private String emailId;
	private String panNumber;
	private String passportNumber;
	private String passportValidity;
	private String visaAvailablity;
	private Float workexpYears;
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
	@JsonIgnore
	private Set<Login> login;
	private String name;

	public String getName() {
		return firstName+" "+lastName;
	}

	public void setName(String name) {
		this.name = firstName+" "+lastName;
	}

	public Employee() {
		super();
		
	}

	public Employee(Integer employeeId) {
		super();
		this.employeeId = employeeId;
	}
	
	public Employee(String emailId) {
		super();
		this.emailId = emailId;
	}

	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public LeaveTypeScheme getLeaveTypeScheme() {
		return leaveTypeScheme;
	}

	public void setLeaveTypeScheme(LeaveTypeScheme leaveTypeScheme) {
		this.leaveTypeScheme = leaveTypeScheme;
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

	public Employee getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(Employee reportingTo) {
		this.reportingTo = reportingTo;
	}

	public Employee getReportingHead() {
		return reportingHead;
	}

	public void setReportingHead(Employee reportingHead) {
		this.reportingHead = reportingHead;
	}

	public Employee getReportingHr() {
		return reportingHr;
	}

	public void setReportingHr(Employee reportingHr) {
		this.reportingHr = reportingHr;
	}

	

	public HolidayList getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(HolidayList holidayList) {
		this.holidayList = holidayList;
	}

	public String getEmpType() {
		return this.empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNumber() {
		return this.contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPanNumber() {
		return this.panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getPassportNumber() {
		return this.passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	
	public String getVisaAvailablity() {
		return this.visaAvailablity;
	}

	public void setVisaAvailablity(String visaAvailablity) {
		this.visaAvailablity = visaAvailablity;
	}

	public Float getWorkexpYears() {
		return this.workexpYears;
	}

	public void setWorkexpYears(Float workexpYears) {
		this.workexpYears = workexpYears;
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

	public boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getPassportValidity() {
		return passportValidity;
	}

	public void setPassportValidity(String passportValidity) {
		this.passportValidity = passportValidity;
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

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	

	public Set<Login> getLogin() {
		return login;
	}

	public void setLogin(Set<Login> login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", department="
				+ department + ", designation=" + designation
				+ ", leaveTypeScheme=" + leaveTypeScheme + ", reportingTo="
				+ reportingTo + ", reportingHead=" + reportingHead
				+ ", reportingHr=" + reportingHr + ", holidayList="
				+ holidayList + ", organizationId=" + organizationId
				+ ", roleId=" + roleId + ", empType=" + empType + ", empNo="
				+ empNo + ", dateOfJoining=" + dateOfJoining + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", contactNumber=" + contactNumber + ", emailId="
				+ emailId + ", panNumber=" + panNumber + ", passportNumber="
				+ passportNumber + ", passportValidity=" + passportValidity
				+ ", visaAvailablity=" + visaAvailablity + ", workexpYears="
				+ workexpYears + ", insertBy=" + insertBy + ", insertDate="
				+ insertDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", deleteFlag=" + deleteFlag + ", login="
				+ login + ", name=" + name + "]";
	}


	
}
