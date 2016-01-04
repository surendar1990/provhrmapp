package com.prov.hrm.employeemarital;

import java.util.List;

public interface EmployeeMaritalDAO {

	public List<EmployeeMarital> getAllEmployeeMarital(int organizationId);

	public int addEmployeeMarital(EmployeeMarital employeeMarital);

	public int updateEmployeeMarital(EmployeeMarital employeeMarital);

	public int deleteEmployeeMarital(int empmaritalId);

	public List<EmployeeMarital> getEmployeeMaritalById(int emppersonalId,int organizationId);
}
