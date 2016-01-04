package com.prov.hrm.employeesalary;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.employee.Employee;

public class EmployeeSalary {

	public EmployeeSalary(Integer empsalaryId) {
		super();
		this.empsalaryId = empsalaryId;
	}

	public EmployeeSalary() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer empsalaryId;
	private Employee employee;
	@JsonIgnore
	private int organizationId;
	private String effectiveFrom;
	private String effectiveTo;
	private float stipend;
	private float basic;
	private Float hra;
	private Float convenyanceAllowance;
	private Float vehicleAllowance;
	private Float medicalAllowance;
	private Float specialAllowance;
	private Float sodexoAllowance;
	private Float educationAllowance;
	private Float pfDeduction;
	private Float voluntarypfDeduction;
	private Float proftaxDeduction;
	private Float esicDeduction;
	private Float incometaxDeduction;
	private Float totalEarning;
	private Float totalDeduction;
	private Float grossSalary;
	private Float netSalary;
	@JsonIgnore
	private Integer insertBy;
	@JsonIgnore
	private Date insertDate;
	@JsonIgnore
	private Integer updateBy;
	@JsonIgnore
	private Date updateDate;
	@JsonIgnore
	private boolean deleteFlag;


	public Integer getEmpsalaryId() {
		return this.empsalaryId;
	}

	public void setEmpsalaryId(Integer empsalaryId) {
		this.empsalaryId = empsalaryId;
	}

	public Employee getTblemployee() {
		return this.employee;
	}

	public void setTblemployee(Employee employee) {
		this.employee = employee;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getEffectiveFrom() {
		return this.effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo() {
		return this.effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public float getStipend() {
		return this.stipend;
	}

	public void setStipend(float stipend) {
		this.stipend = stipend;
	}

	public float getBasic() {
		return this.basic;
	}

	public void setBasic(float basic) {
		this.basic = basic;
	}

	public Float getHra() {
		return this.hra;
	}

	public void setHra(Float hra) {
		this.hra = hra;
	}

	public Float getConvenyanceAllowance() {
		return this.convenyanceAllowance;
	}

	public void setConvenyanceAllowance(Float convenyanceAllowance) {
		this.convenyanceAllowance = convenyanceAllowance;
	}

	public Float getVehicleAllowance() {
		return this.vehicleAllowance;
	}

	public void setVehicleAllowance(Float vehicleAllowance) {
		this.vehicleAllowance = vehicleAllowance;
	}

	public Float getMedicalAllowance() {
		return this.medicalAllowance;
	}

	public void setMedicalAllowance(Float medicalAllowance) {
		this.medicalAllowance = medicalAllowance;
	}

	public Float getSpecialAllowance() {
		return this.specialAllowance;
	}

	public void setSpecialAllowance(Float specialAllowance) {
		this.specialAllowance = specialAllowance;
	}

	public Float getSodexoAllowance() {
		return this.sodexoAllowance;
	}

	public void setSodexoAllowance(Float sodexoAllowance) {
		this.sodexoAllowance = sodexoAllowance;
	}

	public Float getEducationAllowance() {
		return this.educationAllowance;
	}

	public void setEducationAllowance(Float educationAllowance) {
		this.educationAllowance = educationAllowance;
	}

	public Float getPfDeduction() {
		return this.pfDeduction;
	}

	public void setPfDeduction(Float pfDeduction) {
		this.pfDeduction = pfDeduction;
	}

	public Float getVoluntarypfDeduction() {
		return this.voluntarypfDeduction;
	}

	public void setVoluntarypfDeduction(Float voluntarypfDeduction) {
		this.voluntarypfDeduction = voluntarypfDeduction;
	}

	public Float getProftaxDeduction() {
		return this.proftaxDeduction;
	}

	public void setProftaxDeduction(Float proftaxDeduction) {
		this.proftaxDeduction = proftaxDeduction;
	}

	public Float getEsicDeduction() {
		return this.esicDeduction;
	}

	public void setEsicDeduction(Float esicDeduction) {
		this.esicDeduction = esicDeduction;
	}

	public Float getIncometaxDeduction() {
		return this.incometaxDeduction;
	}

	public void setIncometaxDeduction(Float incometaxDeduction) {
		this.incometaxDeduction = incometaxDeduction;
	}

	public Float getTotalEarning() {
		return this.totalEarning;
	}

	public void setTotalEarning(Float totalEarning) {
		this.totalEarning = totalEarning;
	}

	public Float getTotalDeduction() {
		return this.totalDeduction;
	}

	public void setTotalDeduction(Float totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public Float getGrossSalary() {
		return this.grossSalary;
	}

	public void setGrossSalary(Float grossSalary) {
		this.grossSalary = grossSalary;
	}

	public Float getNetSalary() {
		return this.netSalary;
	}

	public void setNetSalary(Float netSalary) {
		this.netSalary = netSalary;
	}

	public Integer getInsertBy() {
		return this.insertBy;
	}

	public void setInsertBy(Integer insertBy) {
		this.insertBy = insertBy;
	}

	public Date getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public Integer getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
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
		return "{empsalaryId=" + empsalaryId + ", employee=" + employee
				+ ", organizationId=" + organizationId + ", effectiveFrom="
				+ effectiveFrom + ", effectiveTo=" + effectiveTo + ", stipend="
				+ stipend + ", basic=" + basic + ", hra=" + hra
				+ ", convenyanceAllowance=" + convenyanceAllowance
				+ ", vehicleAllowance=" + vehicleAllowance
				+ ", medicalAllowance=" + medicalAllowance
				+ ", specialAllowance=" + specialAllowance
				+ ", sodexoAllowance=" + sodexoAllowance
				+ ", educationAllowance=" + educationAllowance
				+ ", pfDeduction=" + pfDeduction + ", voluntarypfDeduction="
				+ voluntarypfDeduction + ", proftaxDeduction="
				+ proftaxDeduction + ", esicDeduction=" + esicDeduction
				+ ", incometaxDeduction=" + incometaxDeduction
				+ ", totalEarning=" + totalEarning + ", totalDeduction="
				+ totalDeduction + ", grossSalary=" + grossSalary
				+ ", netSalary=" + netSalary + ", insertBy=" + insertBy
				+ ", insertDate=" + insertDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", deleteFlag=" + deleteFlag
				+ "}";
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
