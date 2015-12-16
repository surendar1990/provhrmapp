package com.prov.hrm.employeeleave;
import java.util.Date;




import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.leavetype.LeaveType;

public class EmployeeLeave {

	private Integer empleaveId;
	
	private Employee employee;
	
	private LeaveType leaveType;
	
	private Employee approvedBy;
	@JsonIgnore
	private int organizationId;

	private String fromDate;
	private Boolean fromHfflag;

	private String toDate;
	private Boolean toHfflag;
	private float totalDays;
	private String empComment;
	private String approvalComment;
	private String status;
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

	public EmployeeLeave() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmployeeLeave(Integer empleaveId) {
		super();
		this.empleaveId = empleaveId;
	}

	public Integer getEmpleaveId() {
		return empleaveId;
	}

	public void setEmpleaveId(Integer empleaveId) {
		this.empleaveId = empleaveId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public Employee getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Employee approvedBy) {
		this.approvedBy = approvedBy;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public Boolean getFromHfflag() {
		return fromHfflag;
	}

	public void setFromHfflag(Boolean fromHfflag) {
		this.fromHfflag = fromHfflag;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Boolean getToHfflag() {
		return toHfflag;
	}

	public void setToHfflag(Boolean toHfflag) {
		this.toHfflag = toHfflag;
	}

	public float getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(float totalDays) {
		this.totalDays = totalDays;
	}

	public String getEmpComment() {
		return empComment;
	}

	public void setEmpComment(String empComment) {
		this.empComment = empComment;
	}

	public String getApprovalComment() {
		return approvalComment;
	}

	public void setApprovalComment(String approvalComment) {
		this.approvalComment = approvalComment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return "EmployeeLeave [empleaveId=" + empleaveId + ", employee="
				+ employee + ", leaveType=" + leaveType + ", approvedBy="
				+ approvedBy + ", organizationId=" + organizationId
				+ ", fromDate=" + fromDate + ", fromHfflag=" + fromHfflag
				+ ", toDate=" + toDate + ", toHfflag=" + toHfflag
				+ ", totalDays=" + totalDays + ", empComment=" + empComment
				+ ", approvalComment=" + approvalComment + ", status=" + status
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag + "]";
	}

	

}
