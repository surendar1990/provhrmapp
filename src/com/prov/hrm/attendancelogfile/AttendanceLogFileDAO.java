package com.prov.hrm.attendancelogfile;

import java.util.List;

public interface AttendanceLogFileDAO {

	public List<AttendanceLogFile> getAllAttendanceLogFile(int organizationId);

	public int addAttendanceLogFile(AttendanceLogFile attendanceLogFile);

	public int updateAttendanceLogFile(AttendanceLogFile attendanceLogFile);

	public int deleteAttendanceLogFile(int attendanceLogFileId);

	public AttendanceLogFile getAttendanceLogFileById(int attendanceLogFileId);

}
