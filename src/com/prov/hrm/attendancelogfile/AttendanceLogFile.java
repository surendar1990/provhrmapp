package com.prov.hrm.attendancelogfile;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class AttendanceLogFile {

	private Integer attendancelogfileId;
	@JsonIgnore
	private int organizationId;
	private byte[] attendancelogFile;
	private int month;
	private Date year;
	private String uploadTimeStamp;
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
	
	public AttendanceLogFile() {
		super();
	}

	public AttendanceLogFile(Integer attendancelogfileId) {
		super();
		this.attendancelogfileId = attendancelogfileId;
	}


	public Integer getAttendancelogfileId() {
		return attendancelogfileId;
	}

	public void setAttendancelogfileId(Integer attendancelogfileId) {
		this.attendancelogfileId = attendancelogfileId;
	}


	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public byte[] getAttendancelogFile() {
		return this.attendancelogFile;
	}

	public void setAttendancelogFile(byte[] attendancelogFile) {
		this.attendancelogFile = attendancelogFile;
	}

	public int getMonth() {
		return this.month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Date getYear() {
		return this.year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public String getUploadTimeStamp() {
		return this.uploadTimeStamp;
	}

	public void setUploadTimeStamp(String uploadTimeStamp) {
		this.uploadTimeStamp = uploadTimeStamp;
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
		return "{attendancelogfileId=" + attendancelogfileId
				+ ", organizationId=" + organizationId + ", attendancelogFile="
				+ Arrays.toString(attendancelogFile) + ", month=" + month
				+ ", year=" + year + ", uploadTimeStamp=" + uploadTimeStamp
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag +"}";
	}

}
