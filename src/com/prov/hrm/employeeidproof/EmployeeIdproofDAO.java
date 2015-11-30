package com.prov.hrm.employeeidproof;

import java.util.List;

public interface EmployeeIdproofDAO 
{
	public List<EmployeeIdproof> getAllIdProof(int organizationid);

	public int addIdProof(EmployeeIdproof idproof);

	public int updateIdProof(EmployeeIdproof idproof);

	public int deleteIdProof(int idproofId);

	public EmployeeIdproof getIdProofById(int idproofId);
}
