package com.prov.hrm.announcement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.employee.Employee;

public class Announcement {

	private Integer announcementId;
	private Employee employee;
	@JsonIgnore
	private int organizationId;
	private String announcement;
	private int priorityflag;
	private String fromDate;
	private String toDate;
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

	public Announcement() {
		super();
	}

	public Announcement(Integer announcementId) {
		super();
		this.announcementId = announcementId;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public Integer getAnnouncementId() {
		return this.announcementId;
	}

	public void setAnnouncementId(Integer announcementId) {
		this.announcementId = announcementId;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getAnnouncement() {
		return this.announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public int getPriorityflag() {
		return this.priorityflag;
	}

	public void setPriorityflag(int priorityflag) {
		this.priorityflag = priorityflag;
	}

	public String getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return this.toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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
		return "{announcementId=" + announcementId + ", employee=" + employee
				+ ", organizationId=" + organizationId + ", announcement="
				+ announcement + ", priorityflag=" + priorityflag
				+ ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag + "}";
	}

}
