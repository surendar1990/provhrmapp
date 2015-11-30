package com.prov.hrm.employeeidproof;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.idtype.Idtype;

public class EmployeeIdproof {

	private Integer empidproofId;
	private Employee employee;
	private Idtype idType;
	@JsonIgnore
	private int organizationId;
	private String idproofNumber;
	private String validUpto;
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

	public EmployeeIdproof() {
		super();
		
	}

	public EmployeeIdproof(Integer empidproofId) {
		super();
		this.empidproofId = empidproofId;
	}

	public Integer getEmpidproofId() {
		return this.empidproofId;
	}

	public void setEmpidproofId(Integer empidproofId) {
		this.empidproofId = empidproofId;
	}


	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getIdproofNumber() {
		return this.idproofNumber;
	}

	public void setIdproofNumber(String idproofNumber) {
		this.idproofNumber = idproofNumber;
	}

	public String getValidUpto() {
		return this.validUpto;
	}

	public void setValidUpto(String validUpto) {
		this.validUpto = validUpto;
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

	public boolean isDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "{empidproofId=" + empidproofId + ", employee=" + employee
				+ ", idType=" + idType + ", organizationId=" + organizationId
				+ ", idproofNumber=" + idproofNumber + ", validUpto="
				+ validUpto + ", insertBy=" + insertBy + ", insertDate="
				+ insertDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", deleteFlag=" + deleteFlag + "}";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Idtype getIdType() {
		return idType;
	}

	public void setIdType(Idtype idType) {
		this.idType = idType;
	}

}
