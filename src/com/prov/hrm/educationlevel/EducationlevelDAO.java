package com.prov.hrm.educationlevel;

import java.util.List;



public interface EducationlevelDAO 
{
	public List<EducationLevel> getAllEducationlevel(int organizationid);

	public int addEducationlevel(EducationLevel educationlevel);

	public int updateEducationlevel(EducationLevel educationlevel);

	public int deleteEducationlevel(int ducationlevelId);

	public EducationLevel getEducationlevelById(int educationlevelId);
}
