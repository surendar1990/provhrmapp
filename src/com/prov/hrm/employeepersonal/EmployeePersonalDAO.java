package com.prov.hrm.employeepersonal;

import java.util.List;

public interface EmployeePersonalDAO
{

	public List<EmployeePersonal> getAllEmpPersonal(int organizationId);
	
	public List<EmployeePersonal> getEmpPersonal(int emppersonalId,int organizationId);
	
	public int addEmpPersonal(EmployeePersonal employeepersonal);
	
	public int updateEmpPersonal(EmployeePersonal employeepersonal);
	
	public int deleteEmpPersonal(int emppersonalId);

	

	

}
