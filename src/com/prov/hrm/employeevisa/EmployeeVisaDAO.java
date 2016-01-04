package com.prov.hrm.employeevisa;

import java.util.List;

public interface EmployeeVisaDAO {

	public List<EmployeeVisa> getAllEmployeeVisa(int organizationid);

	public int addEmployeeVisa(EmployeeVisa employeeVisa);

	public int updateEmployeeVisa(EmployeeVisa employeeVisa);

	public int deleteEmployeeVisa(int employeeVisaId);

	public EmployeeVisa getEmployeeVisaById(int employeeVisaId);
	
	public List<EmployeeVisa> getEmployeeVisaByEmployeeId(int employeeId, int organizationId);
}
