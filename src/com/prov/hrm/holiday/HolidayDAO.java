package com.prov.hrm.holiday;

import java.util.List;

public interface HolidayDAO {

	public List<Holiday> getAllHoliday(int organizationId, int year);
	
	public List<Holiday> getHolidayByListName(int holidayListId,int organizationId,int year);

	public int addHoliday(List<Holiday> holiday);

	public int updateHoliday(Holiday holiday);

	public int deleteHoliday(int holidayId);

	public Holiday getHolidayById(int holidayId);

}
