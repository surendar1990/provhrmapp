package com.prov.hrm.visatype;

import java.util.List;

public interface VisaTypeDAO {
	
	public List<VisaType> getAllVisaType(int organizationid);
	public int addVisaType(VisaType visatype);
	public int updateVisaType(VisaType visatype);
	public int deleteVisaType(int visatypeId);
	public VisaType getVisaTypeById(int visatypeId);

}
