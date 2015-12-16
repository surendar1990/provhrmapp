package com.prov.hrm.employeeleave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.Mail.LeaveRequestSend;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleaveeligibility.EmployeeLeaveEligibility;
import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.utility.LeaveRequestMail;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeLeaveDAOImpl implements EmployeeLeaveDAO {

	// GET ALL RECORD FROM tblempleave
	@Override
	public List<EmployeeLeave> getAllEmployeeLeave(int organizationId)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		
		List<EmployeeLeave> employeeLeaves=new ArrayList<EmployeeLeave>();
		
		List<EmployeeLeave> empLeaves=new ArrayList<EmployeeLeave>();
		
		try {
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(EmployeeLeave.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.addOrder(Order.desc("fromDate"));
			employeeLeaves=criteria.list();
			session.getTransaction().commit();
			Iterator<EmployeeLeave> ite = employeeLeaves.iterator();
			 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			 EmployeeLeave leave=null;
			while(ite.hasNext()) {
				leave=new EmployeeLeave();
				leave=(EmployeeLeave)ite.next();
				Date formDate = sdinput.parse(leave.getFromDate());
				Date toDate = sdinput.parse(leave.getToDate());
				leave.setFromDate(sdfOut.format(formDate));
				leave.setToDate(sdfOut.format(toDate));
				String leaveStat=leave.getStatus();
				if(leaveStat.matches("P|p"))
				{
					
					empLeaves.add(leave);
				}
			}ite = employeeLeaves.iterator();
			while(ite.hasNext()) {
				leave=new EmployeeLeave();
				leave=(EmployeeLeave)ite.next();
				String leaveStat=leave.getStatus();
				if(leaveStat.matches("A|a"))
				{
					
					empLeaves.add(leave);
				}
			}
			ite = employeeLeaves.iterator();
			while(ite.hasNext()) {
				leave=new EmployeeLeave();
				leave=(EmployeeLeave)ite.next();
				String leaveStat=leave.getStatus();
				if(leaveStat.matches("R|r"))
				{
					
					empLeaves.add(leave);
				}
			}
			
			return empLeaves;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}
	
	//Get list of employee leave detail for corresponding Approval Authority
		public List<EmployeeLeave> getAllEmployeeLeaveForEmployeeApproval(int employeeId,int organizationId)
				throws HibernateException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			
			List<EmployeeLeave> employeeLeaves=new ArrayList<EmployeeLeave>();
			
			List<EmployeeLeave> empLeaves=new ArrayList<EmployeeLeave>();
			Criterion lhs=Restrictions.or(Restrictions.eq("leaveReportingTo", employeeId),Restrictions.eq("leaveReportingHead", employeeId));
			try {
				session.beginTransaction();
				Criteria criteria1=session.createCriteria(EmployeeLeaveEligibility.class)
						.add(Restrictions.eq("organizationId", organizationId))
						.add(Restrictions.or(lhs,Restrictions.eq("leaveReportingHr", employeeId)))
						.add(Restrictions.eq("deleteFlag", false));
				System.out.println(criteria1.list());
				Criteria criteria = session.createCriteria(EmployeeLeave.class);
				criteria.add(Restrictions.eq("organizationId", organizationId));
				criteria.add(Restrictions.eq("deleteFlag", false));
				criteria.addOrder(Order.desc("fromDate"));
				employeeLeaves=criteria.list();
				session.getTransaction().commit();
				Iterator<EmployeeLeave> ite = employeeLeaves.iterator();
				 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
				 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
				 EmployeeLeave leave=null;
					while(ite.hasNext()) {
						leave=new EmployeeLeave();
						leave=(EmployeeLeave)ite.next();
						Date formDate = sdinput.parse(leave.getFromDate());
						Date toDate = sdinput.parse(leave.getToDate());
						leave.setFromDate(sdfOut.format(formDate));
						leave.setToDate(sdfOut.format(toDate));
						String leaveStat=leave.getStatus();
						if(leaveStat.matches("P|p"))
						{
							leave.setStatus("Pending");
							empLeaves.add(leave);
						}
					}ite = employeeLeaves.iterator();
					while(ite.hasNext()) {
						leave=new EmployeeLeave();
						leave=(EmployeeLeave)ite.next();
						String leaveStat=leave.getStatus();
						if(leaveStat.matches("A|a"))
						{
							leave.setStatus("Approved");
							empLeaves.add(leave);
						}
					}
					ite = employeeLeaves.iterator();
					while(ite.hasNext()) {
						leave=new EmployeeLeave();
						leave=(EmployeeLeave)ite.next();
						String leaveStat=leave.getStatus();
						if(leaveStat.matches("R|r"))
						{
							leave.setStatus("Rejected");
							empLeaves.add(leave);
						}
					}
				return employeeLeaves;
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
				return null;
			} finally {
				session.close();
				SessionFactoryUtil.getSessionFactory().close();
			}

		}
	@Override
	public List<EmployeeLeave> getEmployeebyEmployeeId(int employeeId,
			int organizationId) throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();

		List<EmployeeLeave> employeeLeaves=new ArrayList<EmployeeLeave>();
		
		List<EmployeeLeave> empLeaves=new ArrayList<EmployeeLeave>();
		
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeLeave.class);
			
			criteria.add(Restrictions.eq("employee",new Employee(employeeId)));
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.addOrder(Order.asc("insertDate"));
			criteria.setMaxResults(10);
			employeeLeaves=criteria.list();
			session.getTransaction().commit();
			Iterator<EmployeeLeave> ite = employeeLeaves.iterator();
			 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			
			 while(ite.hasNext()) {
				EmployeeLeave leave=(EmployeeLeave)ite.next();
				Date formDate = sdinput.parse(leave.getFromDate());
				Date toDate = sdinput.parse(leave.getToDate());
				leave.setFromDate(sdfOut.format(formDate));
				leave.setToDate(sdfOut.format(toDate));
				empLeaves.add(leave);
			}
			return empLeaves;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}

	}

	// RETURN A RECORD FROM tblempleave USING empleaveId
	@Override
	public EmployeeLeave getEmployeeLeaveById(int empleaveId)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			EmployeeLeave employeeleave =(EmployeeLeave) session.get(EmployeeLeave.class, empleaveId); 
			if (employeeleave != null) {
				if (!employeeleave.isDeleteFlag()) {
					return employeeleave;
				} else {
					return null;
				}
			} else {
				return null;
			}
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			
		}
	}
	// INSERT A EMPLOYEE LEAVE REQUST INTO tblempleave
	@Override
	public int addEmployeeLeave(EmployeeLeave employeeleave)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			 SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
			 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Timestamp date = new java.sql.Timestamp(
					new java.util.Date().getTime());
			employeeleave.setInsertDate(date.toString());
			employeeleave.setUpdateDate(date.toString());
			Date fromdate = indate.parse(employeeleave.getFromDate());
			Date todate = indate.parse(employeeleave.getToDate());
			employeeleave.setFromDate(outdate.format(fromdate));
			employeeleave.setToDate(outdate.format(todate));
			session.save(employeeleave);
			status=1;if(status==1)
			{
				
				//Creating the obj for send mail config
				LeaveRequestMail lrm =new LeaveRequestMail();
				
				//Get the format form the table using getLeaveUpdateFormat from tblleavemail
				LeaveRequestSend getformat = lrm.getLeaveRequestFormat().get(0);
				
				//Get the ccmail directly from database table
				String ccEmails1=getformat.getCcMail();
				
				//Get the mailid for the leave applier by using the employee id
					
				Integer fromUser=employeeleave.getEmployee().getEmployeeId();
				Employee fromrequester = (Employee)session.get(Employee.class, fromUser);
				String ccEmails2=fromrequester.getEmailId();
				
				//Bind the two ccemail into the string
				String ccEmails = new StringBuilder(ccEmails1).append(ccEmails2).toString();	
				
				//Get the to email id by using the format (getformat) from the table tblleavemail
				//By EmployeeId in the table and get the toEmail of the employee from tblemployee using the employeeeId
				Integer to=getformat.getToMail().getEmployeeId();
				Employee tomail = (Employee)session.get(Employee.class, to);
				String toemail=tomail.getEmailId();					
				
				//Create the Hash Map to put all the data which replace in the HTML file
				HashMap<String, String> data=new HashMap<String,String>();
				
				//Get the name from tblemployee using empid which is an approver(Manager)
				
				String fname= tomail.getFirstName();
				String lname= tomail.getLastName();
				String name=new StringBuilder(fname+" ").append(lname).toString();
				//Get the UserName from the employee using employee id (Leave Requester)
				
				String ffromUserName=fromrequester.getFirstName();
				String lfromUserName=fromrequester.getLastName();
				String fromUserName=new StringBuilder(ffromUserName+" ").append(lfromUserName).toString();
				
				
				//get the leave type by using the leavetypeid
				Integer leave=employeeleave.getLeaveType().getLeavetypeId();
				LeaveType lvtype = (LeaveType)session.get(LeaveType.class,leave);
				String leavetype=lvtype.getLeavetype();
				
				//get the employee leave reason
				String reason=employeeleave.getEmpComment();
				
				//get the employee leave from date
				
				 SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
				 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
				 
				 Date fDate = sdinput.parse(employeeleave.getFromDate());
				 String fromDate=sdfOut.format(fDate);
				 
			
				//get the employee leave to date
				 Date tDate=sdinput.parse(employeeleave.getToDate());
				 String toDate=sdfOut.format(tDate);
				
				//get the no of days from employeeleave
				String Days=Float.toString(employeeleave.getTotalDays());
				
				//put all the data in to the Hash map
				data.put("name",name);
				data.put("fromUserName",fromUserName);
				data.put("leavetype",leavetype);
				data.put("reason",reason);
				data.put("fromDate",fromDate.toString());
				data.put("toDate",toDate.toString());
				data.put("Days",Days);
		
				//get the leave request person name for subject
				String fusername=fromrequester.getFirstName();
				String lusername=fromrequester.getLastName();
				String username=new StringBuilder(fusername+" ").append(lusername).toString();
	
				if(LeaveRequestMail.send(toemail,ccEmails,getformat, data,username))
				 {
					System.out.println("Mail Sent Successfully");
				} else {
					System.out.println("Try again");
				}
			}
			
			return status;
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

	// UPDATE A EMPLOYEE LEAVE REQUEST IN tblempleave
		@Override
		public int updateEmployeeLeave(EmployeeLeave employeeleave)
				throws HibernateException, ConstraintViolationException {
			Session session = SessionFactoryUtil.getSessionFactory().openSession();
			int status = 0;
			try {
				session.beginTransaction();
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				employeeleave.setUpdateDate(date.toString());
				EmployeeLeave empleave = getEmployeeLeaveById(employeeleave.getEmpleaveId());
				 SimpleDateFormat outdate = new SimpleDateFormat("yyyy-MM-dd");
				 SimpleDateFormat indate = new SimpleDateFormat("dd-MM-yyyy");
				
				if (empleave != null) {
					employeeleave.setInsertBy(empleave.getInsertBy());
					employeeleave.setInsertDate(empleave.getInsertDate());
					Date fromDate = indate.parse(employeeleave.getFromDate());
					Date toDate = indate.parse(employeeleave.getToDate());
					employeeleave.setFromDate(outdate.format(fromDate));
					employeeleave.setToDate(outdate.format(toDate));
					session.update(employeeleave);
				} else {
					return status;
				}
				status = 1;

				if(status==1)
				{
				
					//Creating the obj for send mail config
					LeaveRequestMail lrm =new LeaveRequestMail();
					String sta=employeeleave.getStatus();
					
					//Get the format form the table using getLeaveUpdateFormat from tblleavemail
					if(sta.matches("p|P"))
					{
						
					LeaveRequestSend getformat = lrm.getLeaveUpdateFormat().get(0);
					
					//Get the ccmail directly from database table
					String ccEmails1=getformat.getCcMail();
					
					//Get the mailid for the leave applier by using the employee id
					Integer fromUser=employeeleave.getEmployee().getEmployeeId();
					Employee fromrequester = (Employee)session.get(Employee.class, fromUser);
					String ccEmails2=fromrequester.getEmailId();
					
					//Bind the two ccemail into the string
					String ccEmails = new StringBuilder(ccEmails1).append(ccEmails2).toString();	
					
					//Get the to email id by using the format (getformat) from the table tblleavemail
					//By EmployeeId in the table and get the toEmail of the employee from tblemployee using the employeeeId
					Integer to=getformat.getToMail().getEmployeeId();
					Employee tomail = (Employee)session.get(Employee.class, to);
					
					//get the data and save it as a string
					String toemail=tomail.getEmailId();					
					
					//Create the Hash Map to put all the data which replace in the HTML file
					HashMap<String, String> data=new HashMap<String,String>();
					
					//Get the name from tblemployee using empid which is an approver(Manager)
					String fname= tomail.getFirstName();
					String lname= tomail.getLastName();
					String name=new StringBuilder(fname+" ").append(lname).toString();
					
					//Get the UserName from the employee using employee id (Leave Requester)
					
					String ffromUserName=fromrequester.getFirstName();
					String lfromUserName=fromrequester.getLastName();
					String fromUserName=new StringBuilder(ffromUserName+" ").append(lfromUserName).toString();
					
					//get the leave type by using the leavetypeid
					Integer leave=employeeleave.getLeaveType().getLeavetypeId();
					LeaveType lvtype = (LeaveType)session.get(LeaveType.class,leave);
					String leavetype=lvtype.getLeavetype();
					
					//get the employee leave reason
					String reason=employeeleave.getEmpComment();
					/*
					//get the employee leave from date
					SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
					 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
					 
					 Date fDate = sdinput.parse(employeeleave.getFromDate());
					 String fromDate=sdfOut.format(fDate);
					 
				
					//get the employee leave to date
					 Date tDate=sdinput.parse(employeeleave.getToDate());
					 String toDate=sdfOut.format(tDate);*/
					
					//get the no of days from employeeleave
					String Days=Float.toString(employeeleave.getTotalDays());
					
					String fromDate= employeeleave.getFromDate();
					String toDate= employeeleave.getToDate();
					
					//put all the data in to the Hash map
					data.put("name",name);
					data.put("fromUserName",fromUserName);
					data.put("leavetype",leavetype);
					data.put("reason",reason);
					data.put("fromDate",fromDate);
					data.put("toDate",toDate);
					data.put("Days",Days);
					
					
					//get the leave request person name for subject
					
					String fusername=fromrequester.getFirstName();
					String lusername=fromrequester.getLastName();
					String username=new StringBuilder(fusername+" ").append(lusername).toString();
					
					System.out.println(username);
					if(LeaveRequestMail.send(toemail,ccEmails,getformat, data,username))
					 {
						System.out.println("Mail sent Successfully");
					} else {
						System.out.println("Try again");
					}}
					//If status is "a" or "r"
					else if(sta.matches("A|a|R|r"))
					{
						LeaveRequestSend getformat = lrm.getLeaveapproveFormat().get(0);
						
						//Get the ccmail directly from database table
						String ccEmails1=getformat.getCcMail();
						
						//Get the mailid for the leave applier by using the employee id
						Integer cc2=getformat.getToMail().getEmployeeId();
						Employee leavemanager=(Employee)session.get(Employee.class, cc2);
						String ccEmails2=leavemanager.getEmailId();	
						
						//Bind the two ccemail into the string
						String ccEmails = new StringBuilder(ccEmails1).append(ccEmails2).toString();	
						
						//Get the to email id by using the format (getformat) from the table tblleavemail
						//By EmployeeId in the table and get the toEmail of the employee from tblemployee using the employeeeId
						Integer to=employeeleave.getEmployee().getEmployeeId();
						Employee tomail = (Employee)session.get(Employee.class,to);
						String toemail=tomail.getEmailId();				
						
						//Create the Hash Map to put all the data which replace in the HTML file
						HashMap<String, String> data=new HashMap<String,String>();
						
						//Get the name from tblemployee using empid which is an approver(Manager)
						String fname= tomail.getFirstName();
						String lname=tomail.getLastName();
						String name=new StringBuilder(fname+" ").append(lname).toString();
						
						//Get the UserName from the employee using employee id (Leave Manager)
						
						String ffromUserName=leavemanager.getFirstName();
						String lfromUserName=leavemanager.getLastName();
						String fromUserName=new StringBuilder(ffromUserName+" ").append(lfromUserName).toString();
						
						//get the leave type by using the leavetypeid
						Integer leave=employeeleave.getLeaveType().getLeavetypeId();
						LeaveType lvtype = (LeaveType)session.get(LeaveType.class,leave);
						String leavetype=lvtype.getLeavetype();
						
						//get the employee leave reason
						String reason=employeeleave.getEmpComment();
						
						//get the employee leave from date
						SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
						 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
						 
						 Date fDate = sdinput.parse(employeeleave.getFromDate());
						 String fromDate=sdfOut.format(fDate);
						 
					
						//get the employee leave to date
						 Date tDate=sdinput.parse(employeeleave.getToDate());
						 String toDate=sdfOut.format(tDate);
						
						//get the no of days from employeeleave
						String Days=Float.toString(employeeleave.getTotalDays());
						
						//get the employee leave Status
						String Status=employeeleave.getStatus();
						
						if(employeeleave.getApprovalComment()==null)
						{
							String approvalcmt="null";
							data.put("approvalcmt",approvalcmt);
						}else{
							String approvalcmt=employeeleave.getApprovalComment();
							data.put("approvalcmt",approvalcmt);
						}
						if(Status.matches("a|A")){
							String LStatus="Approved";
							data.put("LStatus",LStatus);
						}else if (Status.matches("r|R")) {
							String LStatus="Rejected";
							data.put("LStatus",LStatus);
							
						}
						//put all the data in to the Hash map
						data.put("name",name);
						data.put("fromUserName",fromUserName);
						data.put("leavetype",leavetype);
						data.put("reason",reason);
						data.put("fromDate",fromDate);
						data.put("toDate",toDate);
						data.put("Days",Days);
						
						//get the leave request person name for subject
						
						String funame=leavemanager.getFirstName();
						String luname=leavemanager.getLastName();
						String uname=new StringBuilder(funame+" ").append(luname).toString();
						
						if(Status.matches("a|A")){
							String LStatus="Approved";
							String st=LStatus+" by ";
							String username=new StringBuilder(st).append(uname).toString();
							if(LeaveRequestMail.send(toemail,ccEmails,getformat, data,username))
							 {
								System.out.println("Mail sent Successfully");
							} else {
								System.out.println("Try again");
							}
						}else if (Status.matches("r|R")) {
							String LStatus="Rejected";
							String st=LStatus+" by ";
							String username=new StringBuilder(st).append(uname).toString();
							if(LeaveRequestMail.send(toemail,ccEmails,getformat, data,username))
							 {
								System.out.println("Mail sent Successfully");
							} else {
								System.out.println("Try again");
							}
						}		
						}
					}
				return status;	
			}catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
				return status;
			} finally {
				session.getTransaction().commit();
				
			}
		}
	// Soft Delete a record from tblempleave for specific empleaveid
	public int deleteEmployeeLeave(int employeeleaveId) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeLeave employeeleave = getEmployeeLeaveById(employeeleaveId);
			if(employeeleave!=null)
			{
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
			employeeleave.setUpdateDate(date.toString());
			employeeleave.setUpdateBy(null);
			employeeleave.setDeleteFlag(true);
			employeeleave.setStatus("C");
			updateEmployeeLeave(employeeleave);
			status=1;
			if(status==1)
			{
				//Creating the obj for send mail config
				LeaveRequestMail lrm =new LeaveRequestMail();
				
				//Get the format form the table using getLeaveUpdateFormat from tblleavemail
				LeaveRequestSend getformat = lrm.getLeaveDeleteFormat().get(0);
				
				//Get the ccmail directly from database table
				String ccEmails1=getformat.getCcMail();
				
				//Get the mailid for the leave applier by using the employee id
				Integer fromUser=employeeleave.getEmployee().getEmployeeId();
				Employee leaverequester = (Employee)session.get(Employee.class, fromUser);
				String ccEmails2=leaverequester.getEmailId();
				
				//Bind the two ccemail into the string
				String ccEmails = new StringBuilder(ccEmails1).append(ccEmails2).toString();	
				
				//Get the to email id by using the format (getformat) from the table tblleavemail
				//By EmployeeId in the table and get the toEmail of the employee from tblemployee using the employeeeId
				Integer to=getformat.getToMail().getEmployeeId();
				Employee tomail = (Employee)session.get(Employee.class, to);
				String toemail=tomail.getEmailId();					
				
				//Create the Hash Map to put all the data which replace in the HTML file
				HashMap<String, String> data=new HashMap<String,String>();
				
				//Get the name from tblemployee using empid which is an approver(Manager)
				String fname= tomail.getFirstName();
				String lname= tomail.getLastName();
				String name= new StringBuilder(fname+" ").append(lname).toString();
				
				//Get the UserName from the employee using employee id (Leave Requester)
				
				String ffromUserName=leaverequester.getFirstName();
				String lfromUserName=leaverequester.getLastName();
				String fromUserName=new StringBuilder(ffromUserName+" ").append(lfromUserName).toString();
				
				
				//get the leave type by using the leavetypeid
				Integer leave=employeeleave.getLeaveType().getLeavetypeId();
				LeaveType lvtype = (LeaveType)session.get(LeaveType.class,leave);
				String leavetype=lvtype.getLeavetype();
				
				//get the employee leave reason
				String reason=employeeleave.getEmpComment();
				
				SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
				 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
				 
				 Date fDate = sdinput.parse(employeeleave.getFromDate());
				 String fromDate=sdfOut.format(fDate);
				 
			
				//get the employee leave to date
				 Date tDate=sdinput.parse(employeeleave.getToDate());
				 String toDate=sdfOut.format(tDate);
				
				//get the no of days from employeeleave
				String Days=Float.toString(employeeleave.getTotalDays());
				
				//get the employee leave Status
				String Status=employeeleave.getStatus();
				if(Status.matches("C|c"))
				{String LStatus="Cancelled";
				data.put("LStatus",LStatus);
				}
				//put all the data in to the Hash map
				data.put("name",name);
				data.put("fromUserName",fromUserName);
				data.put("leavetype",leavetype);
				data.put("reason",reason);
				data.put("fromDate",fromDate);
				data.put("toDate",toDate);
				data.put("Days",Days);
				
				//get the leave request person name for subject
				
				String fusername=leaverequester.getFirstName();
				String lusername=leaverequester.getLastName();
				String username=new StringBuilder(fusername+" ").append(lusername).toString();
				
				if(LeaveRequestMail.send(toemail,ccEmails,getformat, data,username))
				 {
					System.out.println("Mail sent Successfully");
				} else {
					System.out.println("Try again");
				}}
			return status;
		}
			else {
				return status;
			}
		}
			catch (Exception e) {
			e.printStackTrace();
			return status;
		} 
		finally
		{
			session.getTransaction().commit();
			session.close();
		}
	}
		

	// Hard Delete a record from tblempleave for specific empleaveid
	public int deleteEmployeeLeaveHard(int employeeleaveid)
			throws HibernateException, ConstraintViolationException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeLeave employeeLeave = getEmployeeLeaveById(employeeleaveid);
			session.delete(employeeLeave);
			status = 1;
			return status;
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			
		}
	}

}
