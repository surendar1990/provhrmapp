package com.prov.hrm.employeeeducation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.prov.hrm.educationlevel.EducationLevel;
import com.prov.hrm.employee.Employee;

public class EmployeeEducation {

	private Integer empeducationId;
	private Employee employee;
	private EducationLevel educationLevel;
	@JsonIgnore
	private int organizationId;
	private String education;
	private String passingYear;
	private float percentage;
	private String institutionName;
	private String institutionPlace;
	private String institutionUniversity;
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



	public EmployeeEducation(Integer empeducationId) {
		super();
		this.empeducationId = empeducationId;
	}

	public EmployeeEducation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getEmpeducationId() {
		return this.empeducationId;
	}

	public void setEmpeducationId(Integer empeducationId) {
		this.empeducationId = empeducationId;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getEducation() {
		return this.education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public float getPercentage() {
		return this.percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getInstitutionPlace() {
		return this.institutionPlace;
	}

	public void setInstitutionPlace(String institutionPlace) {
		this.institutionPlace = institutionPlace;
	}

	public String getInstitutionUniversity() {
		return this.institutionUniversity;
	}

	public void setInstitutionUniversity(String institutionUniversity) {
		this.institutionUniversity = institutionUniversity;
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
		return "{empeducationId=" + empeducationId + ", employee=" + employee
				+ ", educationLevel=" + educationLevel + ", organizationId="
				+ organizationId + ", education=" + education
				+ ", passingYear=" + passingYear + ", percentage=" + percentage
				+ ", institutionName=" + institutionName
				+ ", institutionPlace=" + institutionPlace
				+ ", institutionUniversity=" + institutionUniversity
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

	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getPassingYear() {
		return passingYear;
	}

	public void setPassingYear(String passingYear) {
		this.passingYear = passingYear;
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
	

}
