package com.prov.hrm.employee;

import java.util.List;

public interface EmployeeDAO {
	public List<Employee> getAllEmployee(int organizationId);

	public Employee getEmployeeById(int employeeId);

	public int addEmployee(Employee employee);

	public int updateEmployee(Employee employee);

	public int deleteEmployee(int employeeId);

}
