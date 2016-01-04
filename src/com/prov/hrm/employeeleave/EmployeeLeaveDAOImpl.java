package com.prov.hrm.employeeleave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import com.prov.hrm.Mail.LeaveRequestSend;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.employeeleaveeligibility.EmployeeLeaveEligibility;
import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.utility.Getaccountyear;
import com.prov.hrm.utility.LeaveRequestMail;
import com.prov.hrm.utility.SessionFactoryUtil;

public class EmployeeLeaveDAOImpl implements EmployeeLeaveDAO {

	// GET ALL RECORD FROM tblempleave
	@Override
	public List<EmployeeLeave> getAllEmployeeLeave(int organizationId)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();

		List<EmployeeLeave> employeeLeaves = new ArrayList<EmployeeLeave>();

		List<EmployeeLeave> empLeaves = new ArrayList<EmployeeLeave>();

		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(EmployeeLeave.class);
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.addOrder(Order.desc("fromDate"));
			employeeLeaves = criteria.list();
			session.getTransaction().commit();
			Iterator<EmployeeLeave> ite = employeeLeaves.iterator();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			EmployeeLeave leave = null;
			while (ite.hasNext()) {
				leave = new EmployeeLeave();
				leave = (EmployeeLeave) ite.next();
				Date formDate = sdinput.parse(leave.getFromDate());
				Date toDate = sdinput.parse(leave.getToDate());
				leave.setFromDate(sdfOut.format(formDate));
				leave.setToDate(sdfOut.format(toDate));
				String leaveStat = leave.getStatus();
				if (leaveStat.matches("P|p")) {
					leave.setStatus("Pending");
					empLeaves.add(leave);
				}
			}
			ite = employeeLeaves.iterator();
			while (ite.hasNext()) {
				leave = new EmployeeLeave();
				leave = (EmployeeLeave) ite.next();
				String leaveStat = leave.getStatus();
				if (leaveStat.matches("A|a")) {
					leave.setStatus("Approved");
					empLeaves.add(leave);
				}
			}
			ite = employeeLeaves.iterator();
			while (ite.hasNext()) {
				leave = new EmployeeLeave();
				leave = (EmployeeLeave) ite.next();
				String leaveStat = leave.getStatus();
				if (leaveStat.matches("R|r")) {
					leave.setStatus("Rejected");
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
		Employee emp=new Employee(employeeId);
		Criterion lhs=Restrictions.or(Restrictions.eq("reportingTo", emp),Restrictions.eq("reportingHead", emp));
		try {
			session.beginTransaction();
			//HashMap<String,String> hs=Getaccountyear.getaccountingyear(organizationId, "C");
			Criteria criteria1=session.createCriteria(Employee.class)
					.add(Restrictions.eq("organizationId", organizationId))
					.add(Restrictions.or(lhs,Restrictions.eq("reportingHr", emp)))
					.add(Restrictions.eq("deleteFlag", false));
			Iterator<Employee> iteempleave=criteria1.list().iterator();
			Employee empelgi=null;
			while(iteempleave.hasNext()){
				empelgi=new Employee();
				empelgi=iteempleave.next();
			Criteria criteria = session.createCriteria(EmployeeLeave.class)
			.add(Restrictions.eq("organizationId", organizationId))
			.add(Restrictions.eq("employee",new Employee( empelgi.getEmployeeId())))
			.add(Restrictions.eq("deleteFlag", false));
			Iterator<EmployeeLeave> iteemp=criteria.list().iterator();
			while(iteemp.hasNext())
			{
				employeeLeaves.add(iteemp.next());
			}
			}
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
				
			return empLeaves;
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}

	@Override
	public List<EmployeeLeave> getEmployeebyEmployeeId(int employeeId,
			int organizationId) throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();

		List<EmployeeLeave> employeeLeaves = new ArrayList<EmployeeLeave>();

		List<EmployeeLeave> empLeaves = new ArrayList<EmployeeLeave>();

		try {
			session.beginTransaction();

			Criteria criteria = session.createCriteria(EmployeeLeave.class);

			criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.addOrder(Order.desc("updateDate"));
			employeeLeaves = criteria.list();
			session.getTransaction().commit();
			Iterator<EmployeeLeave> ite = employeeLeaves.iterator();
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

			while (ite.hasNext()) {

				EmployeeLeave leave = (EmployeeLeave) ite.next();
				Date formDate = sdinput.parse(leave.getFromDate());
				Date toDate = sdinput.parse(leave.getToDate());
				leave.setFromDate(sdfOut.format(formDate));
				leave.setToDate(sdfOut.format(toDate));

				HashMap<String, String> hs = Getaccountyear.getaccountingyear(
						organizationId, "C");

				Date fdate = sdfOut.parse(hs.get("fromdate"));
				Date tdate = sdfOut.parse(hs.get("todate"));

				Criteria criteria1 = session
						.createCriteria(EmployeeLeave.class)
						.add(Restrictions.eq("organizationId", organizationId))
						.add(Restrictions.eq("empleaveId",
								leave.getEmpleaveId()))
						.add(Restrictions.between("fromDate",
								sdinput.format(fdate), sdinput.format(tdate)))
						.add(Restrictions.between("toDate",
								sdinput.format(fdate), sdinput.format(tdate)));
				if (criteria1.list().size() != 0) {
					leave.setYearFlag("C");

				} else {
					leave.setYearFlag("F");

				}
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
			EmployeeLeave employeeleave = (EmployeeLeave) session.get(
					EmployeeLeave.class, empleaveId);
			if (employeeleave != null) {
				if (!employeeleave.isDeleteFlag()) {
					return employeeleave;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.getTransaction().commit();
			session.close();
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

			Date fromdates = indate.parse(employeeleave.getFromDate());
			Date todates = indate.parse(employeeleave.getToDate());
			String fdate = outdate.format(fromdates);
			String tdate = outdate.format(todates);
			System.out.println("from date" + fdate + "To Date" + tdate);

			Criteria criteria = session.createCriteria(EmployeeLeave.class);
			criteria.add(Restrictions.eq("organizationId",
					employeeleave.getOrganizationId()));
			criteria.add(Restrictions.eq("employee", new Employee(employeeleave
					.getEmployee().getEmployeeId())));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.eq("fromDate", fdate));
			criteria.add(Restrictions.eq("toDate", tdate));
			criteria.add(Restrictions.or(Restrictions.ne("status", "P"),
					Restrictions.ne("status", "R")));
			if (criteria.list().size() == 0) {
				employeeleave.setInsertDate(date.toString());
				employeeleave.setUpdateDate(date.toString());
				Date fromdate = indate.parse(employeeleave.getFromDate());
				Date todate = indate.parse(employeeleave.getToDate());
				employeeleave.setFromDate(outdate.format(fromdate));
				employeeleave.setToDate(outdate.format(todate));
				session.save(employeeleave);
				status = 1;
			} else {
				return 2;
			}
			if (status == 1) {

				// Creating the obj for send mail config
				LeaveRequestMail lrm = new LeaveRequestMail();

				// Get the format form the table using getLeaveUpdateFormat from
				// tblleavemail
				LeaveRequestSend getformat = lrm.getLeaveRequestFormat().get(0);

				// Get the mailid for the leave applier by using the employee id
				
				Employee fromrequester = (Employee) getEmployeebyEmployeeId(employeeleave.getEmployee().getEmployeeId(), employeeleave.getOrganizationId());
				String leaverequester = fromrequester.getEmailId();

				// Get the ccmail directly from database table

				Employee tomail = (Employee) getEmployeebyEmployeeId(employeeleave.getEmployee().getReportingTo().getEmployeeId(),
						employeeleave.getOrganizationId());
				String toemail = tomail.getEmailId();

				Employee ccmail1 = (Employee) getEmployeebyEmployeeId(employeeleave.getEmployee().getReportingHead().getEmployeeId(),
						employeeleave.getOrganizationId());

				Employee ccmail2 = (Employee) getEmployeebyEmployeeId(employeeleave.getEmployee().getReportingHr().getEmployeeId(),
						employeeleave.getOrganizationId());

				System.out.println("tomail  " + toemail + "ccmail1  " + ccmail1
						+ "ccmail2  " + ccmail2);
				// Bind the two ccemail into the string
				String ccEmails = ccmail1.getEmailId() + ","
						+ ccmail2.getEmailId() + "," + leaverequester;

				System.out.println("ccEmails " + ccEmails);
				// Create the Hash Map to put all the data which replace in the
				// HTML file
				HashMap<String, String> data = new HashMap<String, String>();

				// Get the name from tblemployee using empid which is an
				// approver(Manager)

				String name = tomail.getName();
				// Get the UserName from the employee using employee id (Leave
				// Requester)

				String fromUserName = fromrequester.getName();

				// get the leave type by using the leavetypeid
				Integer leave = employeeleave.getLeaveType().getLeavetypeId();
				LeaveType lvtype = (LeaveType) session.get(LeaveType.class,
						leave);
				String leavetype = lvtype.getLeavetype();

				// get the employee leave reason
				String reason = employeeleave.getEmpComment();

				// get the employee leave from date

				SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");

				Date fDate = sdinput.parse(employeeleave.getFromDate());
				String fromDate = sdfOut.format(fDate);

				// get the employee leave to date
				Date tDate = sdinput.parse(employeeleave.getToDate());
				String toDate = sdfOut.format(tDate);

				// get the no of days from employeeleave
				String Days = Float.toString(employeeleave.getTotalDays());

				// put all the data in to the Hash map
				data.put("name", name);
				data.put("fromUserName", fromUserName);
				data.put("leavetype", leavetype);
				data.put("reason", reason);
				data.put("fromDate", fromDate.toString());
				data.put("toDate", toDate.toString());
				data.put("Days", Days);
				
				 if(LeaveRequestMail.send(toemail,ccEmails,getformat,data,fromUserName)) {
				 System.out.println("Mail Sent Successfully");
				 } else {
				 System.out.println("Try again"); }
				 
			}
			return status;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	public List<EmployeeLeave> getEmployeeLeaveByDate(int employeeId,
			int organizationId, String fromDt, String toDt)
			throws HibernateException {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();

		List<EmployeeLeave> employeeLeaves = new ArrayList<EmployeeLeave>();

		List<EmployeeLeave> empLeaves = new ArrayList<EmployeeLeave>();

		try {
			SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
			Date fromDate = sdfOut.parse(fromDt);
			Date toDate = sdfOut.parse(toDt);

			session.beginTransaction();
			Criteria criteria = session.createCriteria(EmployeeLeave.class);
			criteria.add(Restrictions.eq("employee", new Employee(employeeId)));
			criteria.add(Restrictions.eq("organizationId", organizationId));
			criteria.add(Restrictions.eq("deleteFlag", false));
			criteria.add(Restrictions.between("fromDate",
					sdinput.format(fromDate), sdinput.format(toDate)));
			criteria.add(Restrictions.between("toDate",
					sdinput.format(fromDate), sdinput.format(toDate)));
			criteria.addOrder(Order.desc("insertBy"));
			employeeLeaves = criteria.list();
			session.getTransaction().commit();
			Iterator<EmployeeLeave> ite = employeeLeaves.iterator();
			while (ite.hasNext()) {
				EmployeeLeave leave = (EmployeeLeave) ite.next();
				fromDate = sdinput.parse(leave.getFromDate());
				toDate = sdinput.parse(leave.getToDate());
				leave.setFromDate(sdfOut.format(fromDate));
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
			EmployeeLeave empleave = getEmployeeLeaveById(employeeleave
					.getEmpleaveId());
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
				
				//Get the mailid for the leave applier by using the employee id
				Integer fromUser=employeeleave.getEmployee().getEmployeeId();
				Employee fromrequester = (Employee)session.get(Employee.class, fromUser);
				String ccEmailsapply=fromrequester.getEmailId();
				
				//Get the ccmail directly from database table
				
				Employee tomail = (Employee)session.get(Employee.class, fromrequester.getReportingTo().getEmployeeId());
				String toemail=tomail.getEmailId();
				
				Employee ccmail1 = (Employee)session.get(Employee.class,fromrequester.getReportingHead().getEmployeeId());
				
				
				Employee ccmail2 = (Employee)session.get(Employee.class, fromrequester.getReportingHr().getEmployeeId());
				
				
				//Bind the two ccemail into the string
				String ccEmails = ccmail1.getEmailId()+","+ccmail2.getEmailId()+","+ccEmailsapply;
				
				//Create the Hash Map to put all the data which replace in the HTML file
				HashMap<String, String> data=new HashMap<String,String>();
				
				//Get the name from tblemployee using empid which is an approver(Manager)
				
				String name=tomail.getName();
				
				//Get the UserName from the employee using employee id (Leave Requester)
				
				
				String fromUserName=fromrequester.getName();
				
				//get the leave type by using the leavetypeid
				Integer leave=employeeleave.getLeaveType().getLeavetypeId();
				LeaveType lvtype = (LeaveType)session.get(LeaveType.class,leave);
				String leavetype=lvtype.getLeavetype();
				
				//get the employee leave reason
				String reason=employeeleave.getEmpComment();
				
				//get the no of days from employeeleave
				String Days=Float.toString(employeeleave.getTotalDays());
				
				SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
				 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
				 
				 Date fDate = sdinput.parse(employeeleave.getFromDate());
				 String fromDate=sdfOut.format(fDate);
				 
			
				//get the employee leave to date
				 Date tDate=sdinput.parse(employeeleave.getToDate());
				 String toDate=sdfOut.format(tDate);
				
				//put all the data in to the Hash map
				data.put("name",name);
				data.put("fromUserName",fromUserName);
				data.put("leavetype",leavetype);
				data.put("reason",reason);
				data.put("fromDate",fromDate);
				data.put("toDate",toDate);
				data.put("Days",Days);
				
				if(LeaveRequestMail.send(toemail,ccEmails,getformat, data,fromUserName))
				 {
					System.out.println("Mail sent Successfully");
				} else {
					System.out.println("Try again");
				}
				}
				//If status is "a" or "r"
				else if(sta.matches("A|a|R|r"))
				{
					LeaveRequestSend getformat = lrm.getLeaveapproveFormat().get(0);
					
					
					Integer fromUser=employeeleave.getEmployee().getEmployeeId();
					Employee tomail = (Employee)session.get(Employee.class, fromUser);
					String toemail=tomail.getEmailId();
					
					Employee fromreplay = (Employee)session.get(Employee.class, tomail.getReportingTo().getEmployeeId());
					String ccEmailsreplay=fromreplay.getEmailId();

					Employee ccmail1 = (Employee)session.get(Employee.class, tomail.getReportingHead().getEmployeeId());
					
					Employee ccmail2 = (Employee)session.get(Employee.class, tomail.getReportingHr().getEmployeeId());
					
					
					//Bind the two ccemail into the string
					String ccEmails = ccmail1.getEmailId()+","+ccmail2.getEmailId()+","+ccEmailsreplay;	
					
					//Create the Hash Map to put all the data which replace in the HTML file
					HashMap<String, String> data=new HashMap<String,String>();
					
					String name=tomail.getName();
					
					//Get the UserName from the employee using employee id (Leave Manager)
				
					String fromUserName=fromreplay.getName();
					
					//get the leave type by using the leavetypeid
					Integer leave=employeeleave.getLeaveType().getLeavetypeId();
					LeaveType lvtype = (LeaveType)session.get(LeaveType.class,leave);
					String leavetype=lvtype.getLeavetype();
					
					//get the Approver name
					Integer app=employeeleave.getApprovedBy().getEmployeeId();
					Employee approve = (Employee)session.get(Employee.class,app);
					String approveby=approve.getName();
					
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
					data.put("approveby",approveby);
					data.put("fromDate",fromDate);
					data.put("toDate",toDate);
					data.put("Days",Days);
					
					//get the leave request person name for subject
					
					if(Status.matches("a|A")){
						String LStatus="Approved";
						String st=LStatus+" by ";
						String username=new StringBuilder(st).append(approveby).toString();
						if(LeaveRequestMail.send(toemail,ccEmails,getformat, data,username))
						 {
							System.out.println("Mail sent Successfully");
						} else {
							System.out.println("Try again");
						}
					}else if (Status.matches("r|R")) {
						String LStatus="Rejected";
						String st=LStatus+" by ";
						String username=new StringBuilder(st).append(approveby).toString();
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
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return status;
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	// Soft Delete a record from tblempleave for specific empleaveid
	public int deleteEmployeeLeave(int employeeleaveId) {
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		int status = 0;
		try {
			session.beginTransaction();
			EmployeeLeave employeeleave = getEmployeeLeaveById(employeeleaveId);
			if (employeeleave != null) {
				java.sql.Timestamp date = new java.sql.Timestamp(
						new java.util.Date().getTime());
				employeeleave.setUpdateDate(date.toString());
				employeeleave.setUpdateBy(null);
				employeeleave.setDeleteFlag(true);
				employeeleave.setStatus("C");
				session.update(employeeleave);
				int status1 = 1;
				if(status1==1)
				{
					
					//Creating the obj for send mail config
					LeaveRequestMail lrm =new LeaveRequestMail();
					
					//Get the format form the table using getLeaveUpdateFormat from tblleavemail
					LeaveRequestSend getformat = lrm.getLeaveDeleteFormat().get(0);
					
					//Get the mailid for the leave applier by using the employee id
					Integer fromUser=employeeleave.getEmployee().getEmployeeId();
					Employee fromrequester = (Employee)session.get(Employee.class, fromUser);
					String ccEmailsapply=fromrequester.getEmailId();
					
					//Get the ccmail directly from database table
					
					Employee tomail = (Employee)session.get(Employee.class,fromrequester.getReportingTo().getEmployeeId() );
					String toemail=tomail.getEmailId();
					
					Employee ccmail1 = (Employee)session.get(Employee.class, fromrequester.getReportingHead().getEmployeeId() );
				
					Employee ccmail2 = (Employee)session.get(Employee.class, fromrequester.getReportingHr().getEmployeeId());
					
					//Bind the two ccemail into the string
					String ccEmails = ccmail1.getEmailId()+","+ccmail2.getEmailId()+","+ccEmailsapply;	
					
					//Create the Hash Map to put all the data which replace in the HTML file
					HashMap<String, String> data=new HashMap<String,String>();
					
					//Get the name from tblemployee using empid which is an approver(Manager)
					String name=tomail.getName();
					
					//Get the UserName from the employee using employee id (Leave Requester)
					String fromUserName=fromrequester.getName();
					
					//get the leave type by using the leavetypeid
					Integer leave=employeeleave.getLeaveType().getLeavetypeId();
					LeaveType lvtype = (LeaveType)session.get(LeaveType.class,leave);
					String leavetype=lvtype.getLeavetype();
					
					//get the employee leave reason
					String reason=employeeleave.getEmpComment();
					
					SimpleDateFormat sdinput = new SimpleDateFormat("yyyy-MM-dd");
					 SimpleDateFormat sdfOut = new SimpleDateFormat("dd-MM-yyyy");
					 System.out.println(employeeleave.getFromDate()+" and "+ employeeleave.getToDate());
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
				
					if(LeaveRequestMail.send(toemail,ccEmails,getformat, data,fromUserName))
					 {
						System.out.println("Mail sent Successfully");
					} else {
						System.out.println("Try again");
					}
					
				}
				return status1;
			} else {
				return status;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
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
			session.close();
		}
	}

}
