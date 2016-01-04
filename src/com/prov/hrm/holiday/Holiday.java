package com.prov.hrm.holiday;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.holidaylist.HolidayList;

public class Holiday {

	private Integer holidayId;
	private int organizationId;
	private HolidayList holidayList;
	private String holidayName;
	private String holidayDate;
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

	public Holiday() {
		super();
	}
	
	public Holiday(int organizationId, HolidayList holidayList,
			String holidayName, String holidayDate, Integer insertBy,
			Integer updateBy) {
		super();
		this.organizationId = organizationId;
		this.holidayList = holidayList;
		this.holidayName = holidayName;
		this.holidayDate = holidayDate;
		this.insertBy = insertBy;
		this.updateBy = updateBy;
	}

	public Holiday(Integer holidayId) {
		super();
		this.holidayId = holidayId;
	}

	public Integer getHolidayId() {
		return this.holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public HolidayList getHolidayList() {
		return holidayList;
	}

	public void setHolidayList(HolidayList holidayList) {
		this.holidayList = holidayList;
	}

	public String getHolidayName() {
		return this.holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public String getHolidayDate() {
		return this.holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
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
		return "Holiday [holidayId=" + holidayId + ", organizationId="
				+ organizationId + ", holidayList=" + holidayList
				+ ", holidayName=" + holidayName + ", holidayDate="
				+ holidayDate + ", insertBy=" + insertBy + ", insertDate="
				+ insertDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", deleteFlag=" + deleteFlag + "]";
	}

}
