package com.prov.hrm.leavetype;

import java.util.List;

public interface LeaveTypeDAO {
	public List<LeaveType> getAllLeaveType(int organizationId,String fromDate,String toDate);

	public int addLeaveType(LeaveType leaveType);

	public int updateLeaveType(LeaveType leaveType);

	public int deleteLeaveType(int leaveTypeId);

	public LeaveType getLeaveTypeById(int leaveTypeId);

}
