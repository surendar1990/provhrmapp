package com.prov.hrm.employeebank;

import java.util.List;

public interface EmployeeBankDAO 
{
	public List<EmployeeBank>getAllBankDetails(int organizationId);
	
	public List<EmployeeBank> getEmployeeBankById(int emppersonalId,int organizationId);
	
	public int addEmployeeBank(EmployeeBank employeebank);
	
	public int updateEmployeeBank(EmployeeBank employeebank);
	
	public int deleteEmployeebank(int empbankId);

}
