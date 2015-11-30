package com.prov.hrm.role;

import java.util.List;

public interface RoleDAO
{
	public List<Role>getAllRole(int organizationId);
	
	public Role getRoleById(int roleId);
	
	public int addRole(Role role);
	
	public int updateRole(Role role);
	
	public int deleteRole(int roleId);

}
