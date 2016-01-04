package com.prov.hrm.educationlevel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EducationLevel{

	private Integer educationLevelId;
	private int countryId;
	private String educationLevel;
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
	
	public EducationLevel(Integer educationLevelId) {
		super();
		this.educationLevelId = educationLevelId;
	}
	public EducationLevel() {
	}
	public Integer getEducationLevelId() {
		return this.educationLevelId;
	}

	public void setEducationLevelId(Integer educationLevelId) {
		this.educationLevelId = educationLevelId;
	}

	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getEducationLevel() {
		return this.educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
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
		return "{educationLevelId=" + educationLevelId + ", countryId="
				+ countryId + ", educationLevel=" + educationLevel
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag + "}";
	}

}
