package com.prov.hrm.stateprovince;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.country.Country;

public class StateProvince implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer stateprovinceId;
	private Country country;
	private String stateprovince;
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

	public StateProvince() {
		super();
	}

	public StateProvince(Integer stateprovinceId) {
		super();
		this.stateprovinceId = stateprovinceId;
	}

	public Integer getStateprovinceId() {
		return this.stateprovinceId;
	}

	public void setStateprovinceId(Integer stateprovinceId) {
		this.stateprovinceId = stateprovinceId;
	}

	
	public String getStateprovince() {
		return this.stateprovince;
	}

	public void setStateprovince(String stateprovince) {
		this.stateprovince = stateprovince;
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
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "{stateprovinceId=" + stateprovinceId + ", country=" + country
				+ ", stateprovince=" + stateprovince + ", insertBy=" + insertBy
				+ ", insertDate=" + insertDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", deleteFlag=" + deleteFlag
				+ "}";
	}

	

}
