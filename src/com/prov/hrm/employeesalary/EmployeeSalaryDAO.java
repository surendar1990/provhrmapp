package com.prov.hrm.employeesalary;

import java.util.List;

public interface EmployeeSalaryDAO {

	public List<EmployeeSalary> getAllEmployeeSalary(int organizationid);

	public int addEmployeeSalary(EmployeeSalary employeeSalary);

	public int updateEmployeeSalary(EmployeeSalary employeeSalary);

	public int deleteEmployeeSalary(int employeeSalaryId);

	public EmployeeSalary getEmployeeSalaryById(int employeeSalaryId);
}
