package com.prov.hrm.holidaylist;

import java.util.List;

public interface HolidayListDAO {

	public List<HolidayList> getAllHolidayList(int organizationId);
	
	public HolidayList getHolidayListById(int holidaylistId);
	
	public int addHolidayList(HolidayList holidaylist);
	
	public int updateHolidayList(HolidayList holidaylist);
	
	public int deleteHolidayList(int holidaylistId);
}
