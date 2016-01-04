package com.prov.hrm.employeevisa;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.visatype.VisaType;

public class EmployeeVisa{

	public EmployeeVisa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeVisa(Integer empvisaId) {
		super();
		this.empvisaId = empvisaId;
	}

	private Integer empvisaId;
	private Employee employee;
	private VisaType visaType;
	@JsonIgnore
	private int organizationId;
	private String visaValidity;
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
	public Integer getEmpvisaId() {
		return this.empvisaId;
	}

	public void setEmpvisaId(Integer empvisaId) {
		this.empvisaId = empvisaId;
	}

	
	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getVisaValidity() {
		return this.visaValidity;
	}

	public void setVisaValidity(String visaValidity) {
		this.visaValidity = visaValidity;
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

	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "{empvisaId=" + empvisaId + ", employee=" + employee
				+ ", visaType=" + visaType + ", organizationId="
				+ organizationId + ", visaValidity=" + visaValidity
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag + "}";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public VisaType getVisaType() {
		return visaType;
	}

	public void setVisaType(VisaType visaType) {
		this.visaType = visaType;
	}

}
