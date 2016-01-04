package com.prov.hrm.organization;

import java.util.List;

public interface OrganizationDAO {

	public List<Organization> getAllOrganization();

	public Organization getOrganizationById(int organizationId);

	public int addOrganization(Organization organization);

	public int updateOrganization(Organization organization);

	public int deleteOrganization(int organizationId);

}
