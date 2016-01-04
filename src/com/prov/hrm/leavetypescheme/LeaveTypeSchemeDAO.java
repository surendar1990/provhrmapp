package com.prov.hrm.leavetypescheme;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


public interface LeaveTypeSchemeDAO 
{
	public int addLeaveTypeScheme(LeaveTypeScheme leaveTypescheme) throws HibernateException, ConstraintViolationException, MySQLIntegrityConstraintViolationException;

	public int updateLeaveTypeScheme(LeaveTypeScheme leaveTypescheme) throws HibernateException, ConstraintViolationException, MySQLIntegrityConstraintViolationException;

	public int deleteLeaveTypeScheme(int leaveTypeSchemeId);

	public LeaveTypeScheme getLeaveTypeSchemeById(int leaveTypeSchemeId);
	
	public List<LeaveTypeScheme> getAllLeaveTypeScheme(int organizationId);

}
