package com.prov.hrm.holidaylist;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class HolidayList {
	
	private int holidayListId;
	private int organizationId;
	private String holidayListName;
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
	
	
	public HolidayList(int holidayListId) {
		super();
		this.holidayListId = holidayListId;
	}
	
	public HolidayList() {
	
	}

	public int getHolidayListId() {
		return holidayListId;
	}
	
	public void setHolidayListId(int holidayListId) {
		this.holidayListId = holidayListId;
	}
	public int getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	
	public String getHolidayListName() {
		return holidayListName;
	}

	public void setHolidayListName(String holidayListName) {
		this.holidayListName = holidayListName;
	}

	public Integer getInsertBy() {
		return insertBy;
	}
	public void setInsertBy(Integer insertBy) {
		this.insertBy = insertBy;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "HolidayList [holidayListId=" + holidayListId
				+ ", organizationId=" + organizationId + ", holidayListName="
				+ holidayListName + ", insertBy=" + insertBy + ", insertDate="
				+ insertDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + "]";
	}
	

}
