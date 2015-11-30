package com.prov.hrm.attendancelog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.attendancelogfile.AttendanceLogFile;
import com.prov.hrm.employee.Employee;

public class AttendanceLog {

	private Integer attendancelogId;
	private Employee employee;
	private AttendanceLogFile attendanceLogFile;
	@JsonIgnore
	private int organizationId;
	private String attendanceDate;
	private String inTime;
	private String outTime;
	private String lateHours;
	private String workingHours;
	private String breakTime;
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

	public AttendanceLog() {
		super();
	}

	public AttendanceLog(Integer attendancelogId) {
		super();
		this.attendancelogId = attendancelogId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public AttendanceLogFile getAttendanceLogFile() {
		return attendanceLogFile;
	}

	public void setAttendanceLogFile(AttendanceLogFile attendanceLogFile) {
		this.attendanceLogFile = attendanceLogFile;
	}

	public Integer getAttendancelogId() {
		return this.attendancelogId;
	}

	public void setAttendancelogId(Integer attendancelogId) {
		this.attendancelogId = attendancelogId;
	}

	
	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getAttendanceDate() {
		return this.attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getInTime() {
		return this.inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return this.outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getLateHours() {
		return this.lateHours;
	}

	public void setLateHours(String lateHours) {
		this.lateHours = lateHours;
	}

	public String getWorkingHours() {
		return this.workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public String getBreakTime() {
		return this.breakTime;
	}

	public void setBreakTime(String breakTime) {
		this.breakTime = breakTime;
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
		return "{attendancelogId=" + attendancelogId + ", employee=" + employee
				+ ", attendanceLogFile=" + attendanceLogFile
				+ ", organizationId=" + organizationId + ", attendanceDate="
				+ attendanceDate + ", inTime=" + inTime + ", outTime="
				+ outTime + ", lateHours=" + lateHours + ", workingHours="
				+ workingHours + ", breakTime=" + breakTime + ", insertBy="
				+ insertBy + ", insertDate=" + insertDate + ", updateBy="
				+ updateBy + ", updateDate=" + updateDate + ", deleteFlag="
				+ deleteFlag + "}";
	}

}
