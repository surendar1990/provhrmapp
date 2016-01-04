package com.prov.hrm.leavetype;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public interface LeaveTypeDAO {
	/*public List<LeaveType> getAllLeaveType(int organizationId,String fromDate,String toDate);*/

	public int addLeaveType(LeaveType leaveType) throws HibernateException, ConstraintViolationException, MySQLIntegrityConstraintViolationException;

	public int updateLeaveType(LeaveType leaveType) throws HibernateException, ConstraintViolationException, MySQLIntegrityConstraintViolationException;

	public int deleteLeaveType(int leaveTypeId);

	public LeaveType getLeaveTypeById(int leaveTypeId);
	
	public List<LeaveType> getAllLeaveTypenodate(int organizationId);

}
