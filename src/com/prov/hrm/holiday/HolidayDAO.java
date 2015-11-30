package com.prov.hrm.holiday;

import java.util.List;

public interface HolidayDAO {

	public List<Holiday> getAllHoliday(int organizationId);

	public int addHoliday(Holiday holiday);

	public int updateHoliday(Holiday holiday);

	public int deleteHoliday(int holidayId);

	public Holiday getHolidayById(int holidayId);

}
