package com.prov.hrm.designation;

import java.util.List;

public interface DesignationDAO 
{
	public List<Designation> getAllDesignation(int organizationid);
	
	public Designation getDesignationById(int designation_id);
	
	public int addDesignation(Designation designation);
	
	public int updateDesignation(Designation designation);
	
	public int deleteDesignation(int designation_id);
	

}
