package com.prov.hrm.attendancelog;

import java.util.List;

public interface AttendanceLogDAO {

	public List<AttendanceLog> getAllAttendanceLog(int organizationId);

	public int addAttendanceLog(AttendanceLog attendanceLog);

	public int updateAttendanceLog(AttendanceLog attendanceLog);

	public int deleteAttendanceLog(int attendanceLogId);

	public AttendanceLog getAttendanceLogById(int attendanceLogId);

}
