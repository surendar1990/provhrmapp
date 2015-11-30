package com.prov.hrm.employeeleaveeligibility;

import java.util.List;

public interface EmployeeLeaveEligibilityDAO {
	
	public List<EmployeeLeaveEligibility> getAllEmployeeLeaveEligibility(int organizationId);

	public int addEmployeeLeaveEligibility(EmployeeLeaveEligibility employeeleaveeligibility);

	public int updateEmployeeLeaveEligibility(EmployeeLeaveEligibility employeeleaveeligibility);

	public int deleteEmployeeLeaveEligibility(int empleaveeligibilityId);

	public List<EmployeeLeaveEligibility> getEmployeeLeaveEligibilityById(int employeeId, int organizationId);

}
