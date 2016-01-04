package com.prov.hrm.leaveconfiguration;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


public interface LeaveConfigurationDAO 
{
	public List<LeaveConfiguration> getAllLeaveConfigurations(int organizationId);
		
	public LeaveConfiguration getLeaveConfigurationsById(int leaveconfigurationId);
	
	public int addLeaveConfiguration(LeaveConfiguration leaveconfiguration) throws HibernateException, ConstraintViolationException, MySQLIntegrityConstraintViolationException;

	public int updateLeaveConfiguration(LeaveConfiguration leaveconfiguration) throws HibernateException, ConstraintViolationException, MySQLIntegrityConstraintViolationException;

	public int deleteLeaveConfiguration(int leaveconfigurationId);

}
