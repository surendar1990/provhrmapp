package com.prov.hrm.screenauths;

import java.util.List;

public interface ScreenAuthDAO {
	public List<ScreenAuth> getAllScreenAuth(int organizationId);

	public ScreenAuth getScreenAuthById(int screenAuthId);

	public int addScreenAuth(ScreenAuth screenAuth);

	public int updateScreenAuth(ScreenAuth screenAuth);

	public int deleteScreenAuth(int screenAuthId);
}
