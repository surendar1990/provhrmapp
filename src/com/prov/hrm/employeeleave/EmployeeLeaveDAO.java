package com.prov.hrm.employeeleave;

import java.util.List;

public interface EmployeeLeaveDAO {

	public List<EmployeeLeave> getAllEmployeeLeave(int organizationId);

	public int addEmployeeLeave(EmployeeLeave employeeLeave);

	public int updateEmployeeLeave(EmployeeLeave employeeLeave);

	public int deleteEmployeeLeave(int empleaveId);

	public EmployeeLeave getEmployeeLeaveById(int empleaveId);
	
	public List<EmployeeLeave> getEmployeebyEmployeeId(int employeeId,int organizationId);
}
