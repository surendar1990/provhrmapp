package com.prov.hrm.employeeeducation;

import java.util.List;



public interface EmployeeEducationDAO {

	public List<EmployeeEducation> getAllEmployeeEducation(int organizationId);

	public int addEmployeeEducation(EmployeeEducation employeeEducation);

	public int updateEmployeeEducation(EmployeeEducation employeeEducation);

	public int deleteEmployeeEducation(int empeducationId);


	
	public List<EmployeeEducation> getEmployeeEducationById(int emppersonalId,int organizationId);
}
