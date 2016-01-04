package com.prov.hrm.visatype;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class VisaType {

	private Integer visaTypeId;
	@JsonIgnore
	private int organizationId;
	private int countryId;
	private String visaType;
	private String visaTypeDescription;
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

	public VisaType() {
		super();
	
	}

	public VisaType(Integer visaTypeId) {
		super();
		this.visaTypeId = visaTypeId;
	}

	public Integer getVisaTypeId() {
		return this.visaTypeId;
	}

	public void setVisaTypeId(Integer visaTypeId) {
		this.visaTypeId = visaTypeId;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public int getCountryId() {
		return this.countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getVisaType() {
		return this.visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getVisaTypeDescription() {
		return this.visaTypeDescription;
	}

	public void setVisaTypeDescription(String visaTypeDescription) {
		this.visaTypeDescription = visaTypeDescription;
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

	public boolean isDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	
	@Override
	public String toString() {
		return "{visaTypeId=" + visaTypeId + ", organizationId="
				+ organizationId + ", countryId=" + countryId + ", visaType="
				+ visaType + ", visaTypeDescription=" + visaTypeDescription
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag +  "}";
	}

}
