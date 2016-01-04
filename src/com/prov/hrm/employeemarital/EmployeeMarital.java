package com.prov.hrm.employeemarital;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.prov.hrm.employee.Employee;

public class EmployeeMarital {

	public EmployeeMarital() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer empmaritalId;
	@JsonUnwrapped
	private Employee employee;
	@JsonIgnore
	private int organizationId;
	private String spouseName;
	private int age;
	private Integer noOfChildren;
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

	public EmployeeMarital(Integer empmaritalId) {
		super();
		this.empmaritalId = empmaritalId;
	}

	public Integer getEmpmaritalId() {
		return this.empmaritalId;
	}

	public void setEmpmaritalId(Integer empmaritalId) {
		this.empmaritalId = empmaritalId;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
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

	public String getSpouseName() {
		return this.spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Integer getNoOfChildren() {
		return this.noOfChildren;
	}

	public void setNoOfChildren(Integer noOfChildren) {
		this.noOfChildren = noOfChildren;
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

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "{empmaritalId=" + empmaritalId + ", employee=" + employee
				+ ", organizationId=" + organizationId + ", spouseName="
				+ spouseName + ", age=" + age + ", noOfChildren="
				+ noOfChildren + ", insertBy=" + insertBy + ", insertDate="
				+ insertDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", deleteFlag=" + deleteFlag + "}";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
