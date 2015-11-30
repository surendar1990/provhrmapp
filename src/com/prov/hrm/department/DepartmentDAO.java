package com.prov.hrm.department;

import java.util.List;

public interface DepartmentDAO {

	public List<Department> getAllDepartment(int organizationId);

	public int addDepartment(Department department);

	public int updateDepartment(Department department);

	public int deleteDepartment(int departmentId);

	public Department getDepartmentById(int departmentId);

}
