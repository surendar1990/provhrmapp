package com.prov.hrm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;







import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.prov.hrm.announcement.Announcement;
import com.prov.hrm.announcement.AnnouncementDAOImpl;
import com.prov.hrm.attendancelog.AttendanceLog;
import com.prov.hrm.attendancelog.AttendanceLogDAOImpl;
import com.prov.hrm.attendancelogfile.AttendanceLogFile;
import com.prov.hrm.attendancelogfile.AttendanceLogFileDAOImpl;
import com.prov.hrm.bank.Bank;
import com.prov.hrm.bank.BankDAOImpl;
import com.prov.hrm.country.Country;
import com.prov.hrm.country.CountryDAOImpl;
import com.prov.hrm.department.DepartmenDAOImpl;
import com.prov.hrm.department.Department;
import com.prov.hrm.designation.Designation;
import com.prov.hrm.designation.DesignationDAOImpl;
import com.prov.hrm.educationlevel.EducationLevel;
import com.prov.hrm.educationlevel.EducationlevelDAOImpl;
import com.prov.hrm.employee.Employee;
import com.prov.hrm.employee.EmployeeDAOImpl;
import com.prov.hrm.employeebank.EmployeeBank;
import com.prov.hrm.employeebank.EmployeeBankDAOImpl;
import com.prov.hrm.employeeeducation.EmployeeEducation;
import com.prov.hrm.employeeeducation.EmployeeEducationDAOImpl;
import com.prov.hrm.employeeidproof.EmployeeIdproof;
import com.prov.hrm.employeeidproof.EmployeeIdproofDAOImpl;
import com.prov.hrm.employeeleave.EmployeeLeave;
import com.prov.hrm.employeeleave.EmployeeLeaveDAOImpl;
import com.prov.hrm.employeeleaveeligibility.EmpLeaveEligileValues;
import com.prov.hrm.employeeleaveeligibility.EmployeeLeaveEligibility;
import com.prov.hrm.employeeleaveeligibility.EmployeeLeaveEligibilityDAOImpl;
import com.prov.hrm.employeemarital.EmployeeMarital;
import com.prov.hrm.employeemarital.EmployeeMaritalDAOImpl;
import com.prov.hrm.employeepersonal.EmployeePersonal;
import com.prov.hrm.employeepersonal.EmployeePersonalDAOImpl;
import com.prov.hrm.employeesalary.EmployeeSalary;
import com.prov.hrm.employeesalary.EmployeeSalaryDAOImpl;
import com.prov.hrm.employeevisa.EmployeeVisa;
import com.prov.hrm.employeevisa.EmployeeVisaDAOImpl;
import com.prov.hrm.holiday.Holiday;
import com.prov.hrm.holiday.HolidayDAOImpl;
import com.prov.hrm.idtype.Idtype;
import com.prov.hrm.idtype.IdtypeDAOImpl;
import com.prov.hrm.leavetype.LeaveType;
import com.prov.hrm.leavetype.LeaveTypeDAOImpl;
import com.prov.hrm.login.Encryption;
import com.prov.hrm.login.Login;
import com.prov.hrm.login.LoginDAOImpl;
import com.prov.hrm.organization.Organization;
import com.prov.hrm.organization.OrganizationDAOImpl;
import com.prov.hrm.role.Role;
import com.prov.hrm.role.RoleDAOImpl;
import com.prov.hrm.screen.Screen;
import com.prov.hrm.screen.ScreenDAOImpl;
import com.prov.hrm.screenauths.ScreenAuth;
import com.prov.hrm.screenauths.ScreenAuthDAOImpl;
import com.prov.hrm.stateprovince.StateProvince;
import com.prov.hrm.stateprovince.StateProvinceDAOImpl;
import com.prov.hrm.utility.LeaveCalculator;
import com.prov.hrm.visatype.VisaType;
import com.prov.hrm.visatype.VisaTypeDAOImpl;

@RestController
@RequestMapping("service/")
public class ProvHrmController {
	AnnouncementDAOImpl announcementdao = null;
	AttendanceLogDAOImpl attendancelogdao = null;
	AttendanceLogFileDAOImpl attendancelogfiledao = null;
	BankDAOImpl bankdao = null;
	CountryDAOImpl countrydao = null;
	StateProvinceDAOImpl stateprovincedao = null;
	DepartmenDAOImpl departmentdao = null;
	DesignationDAOImpl designationdao = null;
	EducationlevelDAOImpl educationleveldao = null;
	HolidayDAOImpl holidaydao = null;
	OrganizationDAOImpl organizationdao = null;
	RoleDAOImpl roledao = null;
	IdtypeDAOImpl idtypedao = null;
	VisaTypeDAOImpl visatypedao = null;
	LeaveTypeDAOImpl leavetypedao = null;
	EmployeeDAOImpl employeedao = null;
	EmployeePersonalDAOImpl employeepersonaldao = null;
	EmployeeEducationDAOImpl employeeeducationdao = null;
	EmployeeLeaveDAOImpl employeeleavedao = null;
	EmployeeLeaveEligibilityDAOImpl employeeleaveeligibilitydao = null;
	EmployeeMaritalDAOImpl employeemaritaldao = null;
	EmployeeSalaryDAOImpl employeesalarydao = null;
	EmployeeVisaDAOImpl employeevisadao = null;
	EmployeeBankDAOImpl employeebankdao = null;
	EmployeeIdproofDAOImpl employeeidproofdao = null;
	ScreenDAOImpl screendao = null;
	ScreenAuthDAOImpl screenauthdao = null;
	LoginDAOImpl logindao = null;
	HttpHeaders responseHeader = new HttpHeaders();

	// Convert Json String to object
	public static Object convertJsonToObject(String data, Class<?> cl)
			throws JsonSyntaxException {

		try {
			Gson gson = new Gson();
			Object object = gson.fromJson(data, cl);
			return object;
		} finally {

		}

	}

	/*
	 * Announcement Table :tblannouncement
	 */

	// Get announcement by announcementId
	@RequestMapping(value = "Announcement/{announcementId}", method = RequestMethod.GET)
	public ResponseEntity<Announcement> getAnnouncementId(
			@PathVariable int announcementId) {
		try {
			announcementdao = new AnnouncementDAOImpl();
			Announcement announcement = announcementdao
					.getAnnouncementById(announcementId);
			if (announcement != null) {
				return new ResponseEntity<Announcement>(announcement,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return new ResponseEntity<Announcement>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all record in announcement
	@RequestMapping(value = "Announcement", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Announcement>> getAllAnnouncement(
			@RequestHeader int data) {
		try {
			announcementdao = new AnnouncementDAOImpl();
			List<Announcement> Announcement = announcementdao
					.getAllAnnouncement(data);
			return new ResponseEntity<List<Announcement>>(Announcement,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record in announcement
	@RequestMapping(value = "Announcement", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addAnnouncement(
			@RequestBody String addAnnouncementId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			Announcement announcement = (Announcement) convertJsonToObject(
					addAnnouncementId, Announcement.class);
			announcementdao = new AnnouncementDAOImpl();
			int status = announcementdao.addAnnouncement(announcement);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), responseHeader,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete announcement by announcementId
	@RequestMapping(value = "Announcement/{announcementId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAnnouncement(
			@PathVariable int announcementId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			announcementdao = new AnnouncementDAOImpl();
			int status = announcementdao.deleteAnnouncement(announcementId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update announcement by announcementId
	@RequestMapping(value = "Announcement/{announcementId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateAnnouncement(
			@RequestBody String updateAnnouncement,
			@PathVariable int announcementId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			Announcement announcement = (Announcement) convertJsonToObject(
					updateAnnouncement, Announcement.class);
			announcementdao = new AnnouncementDAOImpl();
			announcement.setAnnouncementId(announcementId);
			int status = announcementdao.updateAnnouncement(announcement);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * AttendanceLog Table :tblattendancelog
	 */

	// Get attendancelog by attendanceLogId
	@RequestMapping(value = "AttendanceLog/{attendanceLogId}", method = RequestMethod.GET)
	public ResponseEntity<AttendanceLog> getAttendanceLogId(
			@PathVariable int attendanceLogId) {
		try {
			attendancelogdao = new AttendanceLogDAOImpl();
			AttendanceLog attendancelog = attendancelogdao
					.getAttendanceLogById(attendanceLogId);
			if (attendancelog != null) {
				return new ResponseEntity<AttendanceLog>(attendancelog,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return new ResponseEntity<AttendanceLog>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all record in attendancelog
	@RequestMapping(value = "AttendanceLog", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<AttendanceLog>> getAllAttendanceLog(
			@RequestHeader int data) {
		try {
			attendancelogdao = new AttendanceLogDAOImpl();
			List<AttendanceLog> attendanceLog = attendancelogdao
					.getAllAttendanceLog(data);
			return new ResponseEntity<List<AttendanceLog>>(attendanceLog,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record in AttendanceLog
	@RequestMapping(value = "AttendanceLog", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addAttendanceLog(
			@RequestBody String addAttendanceLogId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			AttendanceLog attendanceLog = (AttendanceLog) convertJsonToObject(
					addAttendanceLogId, AttendanceLog.class);
			attendancelogdao = new AttendanceLogDAOImpl();
			int status = attendancelogdao.addAttendanceLog(attendanceLog);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete AttendanceLog by attendanceLogId
	@RequestMapping(value = "AttendanceLog/{attendanceLogId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAttendanceLog(
			@PathVariable int attendanceLogId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			attendancelogdao = new AttendanceLogDAOImpl();
			int status = attendancelogdao.deleteAttendanceLog(attendanceLogId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update AttendanceLog by attendanceLogId
	@RequestMapping(value = "AttendanceLog/{attendanceLogId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateAttendanceLog(
			@RequestBody String updateAttendanceLog,
			@PathVariable int attendanceLogId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			AttendanceLog attendance = (AttendanceLog) convertJsonToObject(
					updateAttendanceLog, AttendanceLog.class);
			attendancelogdao = new AttendanceLogDAOImpl();
			attendance.setAttendancelogId(attendanceLogId);
			int status = attendancelogdao.updateAttendanceLog(attendance);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * AttendanceLogFile Table :tblattendancelogfile
	 */

	// Get attendancelogfile by attendanceLogFileId
	@RequestMapping(value = "AttendanceLogFile/{attendanceLogFileId}", method = RequestMethod.GET)
	public ResponseEntity<AttendanceLogFile> getAttendanceLogFileId(
			@PathVariable int attendanceLogFileId) {
		try {
			attendancelogfiledao = new AttendanceLogFileDAOImpl();
			AttendanceLogFile attendancelog = attendancelogfiledao
					.getAttendanceLogFileById(attendanceLogFileId);
			if (attendancelog != null) {
				return new ResponseEntity<AttendanceLogFile>(attendancelog,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return new ResponseEntity<AttendanceLogFile>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all record in attendancelogfile
	@RequestMapping(value = "AttendanceLogFile", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<AttendanceLogFile>> getAllAttendanceLogFile(
			@RequestHeader int data) {
		try {
			attendancelogfiledao = new AttendanceLogFileDAOImpl();
			List<AttendanceLogFile> attendanceLog = attendancelogfiledao
					.getAllAttendanceLogFile(data);
			return new ResponseEntity<List<AttendanceLogFile>>(attendanceLog,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record in attendancelogfile
	@RequestMapping(value = "AttendanceLogFile", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addAttendanceLogFile(
			@RequestBody String addAttendanceLogFileId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			AttendanceLogFile attendanceLog = (AttendanceLogFile) convertJsonToObject(
					addAttendanceLogFileId, AttendanceLogFile.class);
			attendancelogfiledao = new AttendanceLogFileDAOImpl();
			int status = attendancelogfiledao
					.addAttendanceLogFile(attendanceLog);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete attendancelogfile by attendanceLogFileId
	@RequestMapping(value = "AttendanceLogFile/{attendanceLogFileId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAttendanceLogFile(
			@PathVariable int attendanceLogFileId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			attendancelogfiledao = new AttendanceLogFileDAOImpl();
			int status = attendancelogfiledao
					.deleteAttendanceLogFile(attendanceLogFileId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update attendancelogfile by attendanceLogFileId
	@RequestMapping(value = "AttendanceLogFile/{attendanceLogFileId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateAttendanceLogFile(
			@RequestBody String updateAttendanceLogFile,
			@PathVariable int attendanceLogFileId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			AttendanceLogFile attendance = (AttendanceLogFile) convertJsonToObject(
					updateAttendanceLogFile, AttendanceLogFile.class);
			attendancelogfiledao = new AttendanceLogFileDAOImpl();
			attendance.setAttendancelogfileId(attendanceLogFileId);
			int status = attendancelogfiledao
					.updateAttendanceLogFile(attendance);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Bank Table: tblbank
	 */
	// Get a record from bank for bankId
	@RequestMapping(value = "Bank/{bankId}", method = RequestMethod.GET)
	public ResponseEntity<Bank> getBankId(@PathVariable int bankId) {
		try {
			bankdao = new BankDAOImpl();
			Bank bank = bankdao.getBankById(bankId);
			if (bank != null) {
				return new ResponseEntity<Bank>(bank, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Get all record in bank
	@RequestMapping(value = "Bank", method = RequestMethod.GET)
	public ResponseEntity<List<Bank>> getAllBank(@RequestHeader int data) {
		try {
			bankdao = new BankDAOImpl();
			List<Bank> bank = bankdao.getAllBank(data);

			return new ResponseEntity<List<Bank>>(bank, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record into bank
	@RequestMapping(value = "/Bank", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addBank(@RequestBody String addBank) {
		responseHeader.set("Content-type", "text/plain");
		try {
			Bank bank = (Bank) convertJsonToObject(addBank, Bank.class);
			bankdao = new BankDAOImpl();
			int status = bankdao.addBank(bank);

			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);

			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record in bank by bankId
	@RequestMapping(value = "/Bank/{bankId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBank(@PathVariable int bankId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			bankdao = new BankDAOImpl();
			int status = bankdao.deleteBank(bankId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in bank by bankId
	@RequestMapping(value = "/Bank/{bankId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateBank(@RequestBody String updateBank,
			@PathVariable int bankId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			Bank bank = (Bank) convertJsonToObject(updateBank, Bank.class);
			bankdao = new BankDAOImpl();
			bank.setBankId(bankId);
			int status = bankdao.updateBank(bank);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Country Table: tblcountry
	 */
	// Get a record from country for countryId
	@RequestMapping(value = "Country/{countryId}", method = RequestMethod.GET)
	public ResponseEntity<Country> getCountryId(@PathVariable int countryId) {
		try {
			countrydao = new CountryDAOImpl();
			Country country = countrydao.getCountryById(countryId);
			if (country != null) {
				return new ResponseEntity<Country>(country, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all record in country
	@RequestMapping(value = "Country", method = RequestMethod.GET)
	public ResponseEntity<List<Country>> getAllCountry() {
		try {
			countrydao = new CountryDAOImpl();
			List<Country> country = countrydao.getAllCountry();

			return new ResponseEntity<List<Country>>(country, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Insert a record into country
	@RequestMapping(value = "/Country", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addCountry(@RequestBody String addCountry) {
		responseHeader.set("Content-type", "text/plain");
		try {
			Country country = (Country) convertJsonToObject(addCountry,
					Country.class);
			countrydao = new CountryDAOImpl();
			int status = countrydao.addCountry(country);

			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);

			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record in country
	@RequestMapping(value = "/Country/{countryId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCountry(@PathVariable int countryId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			countrydao = new CountryDAOImpl();
			int status = countrydao.deleteCountry(countryId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in country
	@RequestMapping(value = "/Country/{country_id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateCountry(
			@RequestBody String updateCountry, @PathVariable int country_id)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");

		try {
			Country country = (Country) convertJsonToObject(updateCountry,
					Country.class);
			countrydao = new CountryDAOImpl();
			country.setCountryId(country_id);
			int status = countrydao.updateCountry(country);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Department Table :tbldepartment
	 */

	// Get department by departmentId
	@RequestMapping(value = "Department/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<Department> getDepartmentId(
			@PathVariable int departmentId) {
		try {
			departmentdao = new DepartmenDAOImpl();
			Department department = departmentdao
					.getDepartmentById(departmentId);
			return new ResponseEntity<Department>(department, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all record in department
	@RequestMapping(value = "Department", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> getAllDepartment(
			@RequestHeader int data) {
		try {
			departmentdao = new DepartmenDAOImpl();
			List<Department> department = departmentdao.getAllDepartment(data);
			return new ResponseEntity<List<Department>>(department,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record in department
	@RequestMapping(value = "Department", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addDepartment(
			@RequestBody String adddepartment) {
		responseHeader.set("Content-type", "text/plain");
		try {
			Department department = (Department) convertJsonToObject(
					adddepartment, Department.class);
			departmentdao = new DepartmenDAOImpl();
			int status = departmentdao.addDepartment(department);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data inserted Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (JsonSyntaxException je) {
			// je.printStackTrace();
			return new ResponseEntity<String>("Error in JSON input",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internal Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete department by departmentId
	@RequestMapping(value = "Department/{departmentId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteDepartment(
			@PathVariable int departmentId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			departmentdao = new DepartmenDAOImpl();
			int status = departmentdao.deleteDepartment(departmentId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in Deleting data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data Deleted Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internel Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update department by departmentId
	@RequestMapping(value = "Department/{departmentId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateDepartment(
			@RequestBody String updateDepartment, @PathVariable int departmentId)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			Department department = (Department) convertJsonToObject(
					updateDepartment, Department.class);
			departmentdao = new DepartmenDAOImpl();
			department.setDepartmentId(departmentId);
			int status = departmentdao.updateDepartment(department);
			if (status == 0) {
				return new ResponseEntity<String>("Error in Updating data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data Updated Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (JsonSyntaxException je) {
			// je.printStackTrace();
			return new ResponseEntity<String>("Error in JSON input",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internel Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Designation Table :tbldesignation
	 */
	// Get all record in designation
	@RequestMapping(value = "/Designation", method = RequestMethod.GET)
	public ResponseEntity<List<Designation>> getAllDesignations(
			@RequestHeader int data) {
		try {
			designationdao = new DesignationDAOImpl();
			List<Designation> designation = designationdao
					.getAllDesignation(data);
			return new ResponseEntity<List<Designation>>(designation,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Get a record designation by designationId
	@RequestMapping(value = "/Designation/{designationId}", method = RequestMethod.GET)
	public ResponseEntity<Designation> getDesignationById(
			@PathVariable int designationId) {
		try {
			designationdao = new DesignationDAOImpl();
			Designation designation = designationdao
					.getDesignationById(designationId);
			return new ResponseEntity<Designation>(designation, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record into designation
	@RequestMapping(value = "/Designation", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addDesignation(
			@RequestBody String addDesignation) {
		responseHeader.set("Content-type", "text/plain");
		try {
			Designation designation = (Designation) convertJsonToObject(
					addDesignation, Designation.class);
			designationdao = new DesignationDAOImpl();
			int status = designationdao.addDesignation(designation);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data inserted Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (JsonSyntaxException je) {
			// je.printStackTrace();
			return new ResponseEntity<String>("Error in JSON input",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internal Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record from designation
	@RequestMapping(value = "/Designation/{designationId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteDesignation(
			@PathVariable int designationId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			designationdao = new DesignationDAOImpl();
			int status = designationdao.deleteDesignation(designationId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in Deleting data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data Deleted Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internel Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in designation by id
	@RequestMapping(value = "/Designation/{designationId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateDesignation(
			@RequestBody String updateDesignation,
			@PathVariable int designationId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			Designation designation = (Designation) convertJsonToObject(
					updateDesignation, Designation.class);
			designationdao = new DesignationDAOImpl();
			designation.setDesignationId(designationId);
			int status = designationdao.updateDesignation(designation);
			if (status == 0) {
				return new ResponseEntity<String>("Error in Updating data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data Updated Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (JsonSyntaxException je) {
			// je.printStackTrace();
			return new ResponseEntity<String>("Error in JSON input",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internel Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * EducationLevel table:tbleducationlevel
	 */
	// Get Educationlevel by educationlevelId
	@RequestMapping(value = "EducationLevel/{educationlevelId}", method = RequestMethod.GET)
	public ResponseEntity<EducationLevel> getEducationlevelId(
			@PathVariable int educationlevelId) {

		try {
			educationleveldao = new EducationlevelDAOImpl();
			EducationLevel educationlevel = educationleveldao
					.getEducationlevelById(educationlevelId);
			return new ResponseEntity<EducationLevel>(educationlevel,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all record in Educationlevel
	@RequestMapping(value = "EducationLevel", method = RequestMethod.GET)
	public ResponseEntity<List<EducationLevel>> getAllEducationlevel(
			@RequestHeader int data) {
		try {
			educationleveldao = new EducationlevelDAOImpl();
			List<EducationLevel> educationlevel = educationleveldao
					.getAllEducationlevel(data);
			return new ResponseEntity<List<EducationLevel>>(educationlevel,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record in Educationlevel
	@RequestMapping(value = "/EducationLevel", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addEducationlevel(
			@RequestBody String addEducationlevel) {
		responseHeader.set("Content-type", "text/plain");
		try {

			EducationLevel educationlevel = (EducationLevel) convertJsonToObject(
					addEducationlevel, EducationLevel.class);
			educationleveldao = new EducationlevelDAOImpl();
			int status = educationleveldao.addEducationlevel(educationlevel);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data inserted Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (JsonSyntaxException je) {
			// je.printStackTrace();
			return new ResponseEntity<String>("Error in JSON input",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internal Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete Educationlevel by educationlevelId(hard delete)
	@RequestMapping(value = "EducationLevel/{educationlevelId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEducationlevel(
			@PathVariable int educationlevelId) {
		responseHeader.set("Content-type", "text/plain");

		try {
			educationleveldao = new EducationlevelDAOImpl();
			int status = educationleveldao
					.deleteEducationlevel(educationlevelId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in Deleting data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data Deleted Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internel Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update Educationlevel by educationlevelId
	@RequestMapping(value = "EducationLevel/{educationlevelId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateEducationlevel(
			@RequestBody String updateEducationlevel,
			@PathVariable int educationlevelId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			EducationLevel educationlevel = (EducationLevel) convertJsonToObject(
					updateEducationlevel, EducationLevel.class);
			educationleveldao = new EducationlevelDAOImpl();
			educationlevel.setEducationLevelId(educationlevelId);
			int status = educationleveldao.updateEducationlevel(educationlevel);
			if (status == 0) {
				return new ResponseEntity<String>("Error in Updating data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data Updated Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (JsonSyntaxException je) {
			// je.printStackTrace();
			return new ResponseEntity<String>("Error in JSON input",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internel Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Employee table:tblemployee
	 */
	// Get all record in employee
	@RequestMapping(value = "Employee", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAllEmployee(@RequestHeader int data) {
		try {
			employeedao = new EmployeeDAOImpl();
			List<Employee> employee = employeedao.getAllEmployee(data);
			return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get a record in employee
	@RequestMapping(value = "Employee/{employeeId}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeId(@PathVariable int employeeId) {
		try {
			employeedao = new EmployeeDAOImpl();
			Employee employee = employeedao.getEmployeeById(employeeId);
			if (employee != null) {
				return new ResponseEntity<Employee>(employee, HttpStatus.OK);
			} else {
				return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record in employee
	@RequestMapping(value = "/Employee", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addEmployee(@RequestBody String addEmployee) {
		responseHeader.set("Content-type", "text/plain");
		try {
			Employee employee = (Employee) convertJsonToObject(addEmployee,
					Employee.class);
			employeedao = new EmployeeDAOImpl();
			int status = employeedao.addEmployee(employee);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				if (status == 1) {
					return new ResponseEntity<String>(
							Integer.toString(status),
							responseHeader, HttpStatus.OK);
				} else {
					return new ResponseEntity<String>(
							Integer.toString(status),
							responseHeader, HttpStatus.OK);

				}
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a Employee by employeeId
	@RequestMapping(value = "Employee/{employeeId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			employeedao = new EmployeeDAOImpl();
			int status = employeedao.deleteEmployee(employeeId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update Employee by employeeId
	@RequestMapping(value = "Employee/{employeeId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateEmployee(
			@RequestBody String updateEmployee, @PathVariable int employeeId)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			Employee employee = (Employee) convertJsonToObject(updateEmployee,
					Employee.class);
			employeedao = new EmployeeDAOImpl();
			employee.setEmployeeId(employeeId);
			int status = employeedao.updateEmployee(employee);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * EmployeeBankDetails Table :tblempbank
	 */
	// Get all record in EmployeeBank details from tblempbank
	@RequestMapping(value = "/EmployeeBank", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeBank>> getAllBankDetails(
			@RequestHeader int data) {
		try {
			employeebankdao = new EmployeeBankDAOImpl();
			List<EmployeeBank> empbank = employeebankdao
					.getAllBankDetails(data);
			return new ResponseEntity<List<EmployeeBank>>(empbank,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//  GET A RECORD FROM EMPLOYEEEDUCATION TABLE USING employeeId
		@RequestMapping(value = "/EmployeeBank/{employeeId}", method = RequestMethod.GET)
		public ResponseEntity<List<EmployeeBank>> getEmployeeBankById(
				@PathVariable int employeeId,@RequestHeader int data) {
			try {
				employeebankdao = new EmployeeBankDAOImpl();
				List<EmployeeBank> empbank = employeebankdao
						.getEmployeeBankById(employeeId,data);
				if (empbank != null) {
						return new ResponseEntity<List<EmployeeBank>>(
								empbank, HttpStatus.OK);
						} else {
							return new ResponseEntity<>(HttpStatus.NO_CONTENT);
						}
					} catch (HibernateException e) {
						e.printStackTrace();
						return new ResponseEntity<List<EmployeeBank>>(
								HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}

	// Insert a new record in the EmployeePersonal details in tblemppersonal
	@RequestMapping(value = "/EmployeeBank", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addEmployeeBank(
			@RequestBody String addEmployeebank) {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeBank employeebank = (EmployeeBank) convertJsonToObject(
					addEmployeebank, EmployeeBank.class);
			employeebankdao = new EmployeeBankDAOImpl();
			int status = employeebankdao.addEmployeeBank(employeebank);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record from EmployeeBank table
	@RequestMapping(value = "/EmployeeBank/{empbankId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployeebank(@PathVariable int empbankId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			employeebankdao = new EmployeeBankDAOImpl();
			int status = employeebankdao.deleteEmployeebank(empbankId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in EmlpoyeeBank by empbankId
	@RequestMapping(value = "/EmployeeBank/{empbankId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateEmployeeBank(
			@RequestBody String updateemployeebank, @PathVariable int empbankId)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeBank employeebank = (EmployeeBank) convertJsonToObject(
					updateemployeebank, EmployeeBank.class);
			employeebankdao = new EmployeeBankDAOImpl();
			employeebank.setEmpbankId(empbankId);
			int status = employeebankdao.updateEmployeeBank(employeebank);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * EmployeeEducation Table :tblempeducation
	 */


	//  GET A RECORD FROM EMPLOYEEEDUCATION TABLE USING employeeId
		@RequestMapping(value = "/EmployeeEducation/{employeeId}", method = RequestMethod.GET)
		public ResponseEntity<List<EmployeeEducation>> getEmployeeEducationtId(
				@PathVariable int employeeId,@RequestHeader int data) {
			try {
				employeeeducationdao = new EmployeeEducationDAOImpl();
				List<EmployeeEducation> employeeeducation = employeeeducationdao
						.getEmployeeEducationById(employeeId,data);
				if (employeeeducation != null) {
						return new ResponseEntity<List<EmployeeEducation>>(
								employeeeducation, HttpStatus.OK);
						} else {
							return new ResponseEntity<>(HttpStatus.NO_CONTENT);
						}
					} catch (HibernateException e) {
						e.printStackTrace();
						return new ResponseEntity<List<EmployeeEducation>>(
								HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}


	// GET ALL RECORD FROM EMPLOYEEEDUCATION TABLE
	@RequestMapping(value = "EmployeeEducation", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeEducation>> getAllEmployeeEducation(
			@RequestHeader int data) {
		try {
			employeeeducationdao = new EmployeeEducationDAOImpl();
			List<EmployeeEducation> employeeeducation = employeeeducationdao
					.getAllEmployeeEducation(data);
			return new ResponseEntity<List<EmployeeEducation>>(
					employeeeducation, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// ADD A EMPLOYEE EDUCATION DETAILS IN EMPLOYEE EDUCATION TABLE
	@RequestMapping(value = "/EmployeeEducation", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addEmployeeEducation(
			@RequestBody String addEmployeeEducationId) {
		responseHeader.set("Content-type", "text/plain");
		try {

			EmployeeEducation employeeeducation = (EmployeeEducation) convertJsonToObject(
					addEmployeeEducationId, EmployeeEducation.class);
			employeeeducationdao = new EmployeeEducationDAOImpl();
			int status = employeeeducationdao
					.addEmployeeEducation(employeeeducation);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// DELETE A EMPLOYEE EDUCATION DETAILS BY empeducationId
	@RequestMapping(value = "EmployeeEducation/{empeducationId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployeeEducation(
			@PathVariable int empeducationId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			employeeeducationdao = new EmployeeEducationDAOImpl();
			int status = employeeeducationdao
					.deleteEmployeeEducation(empeducationId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// UPDATE EMPLOYEE EDUCATION DETAILS BY empeducationId
	@RequestMapping(value = "EmployeeEducation/{empeducationId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateEmployeeEducation(
			@RequestBody String updateEmployeeEducation,
			@PathVariable int empeducationId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeEducation employeeeducation = (EmployeeEducation) convertJsonToObject(
					updateEmployeeEducation, EmployeeEducation.class);
			employeeeducationdao = new EmployeeEducationDAOImpl();
			employeeeducation.setEmpeducationId(empeducationId);
			int status = employeeeducationdao
					.updateEmployeeEducation(employeeeducation);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * tableIdproof Table:tblempidproof
	 */
	// Get all the record from tblempidproof
	@RequestMapping(value = "EmployeeIdProof", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeIdproof>> getAllIdProof(
			@RequestHeader int data) {
		try {
			employeeidproofdao = new EmployeeIdproofDAOImpl();
			List<EmployeeIdproof> idproof = employeeidproofdao
					.getAllIdProof(data);
			if (idproof.size() == 0) {
				return new ResponseEntity<List<EmployeeIdproof>>(
						HttpStatus.NO_CONTENT);

			} else {
				return new ResponseEntity<List<EmployeeIdproof>>(idproof,
						HttpStatus.OK);

			}
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get a record from tblempidproof
	@RequestMapping(value = "/EmployeeIdProof/{employeeidproofId}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeIdproof> getIdProofById(
			@PathVariable int employeeidproofId) {
		try {
			employeeidproofdao = new EmployeeIdproofDAOImpl();
			EmployeeIdproof idproof = employeeidproofdao
					.getIdProofById(employeeidproofId);
			if (idproof != null) {
				return new ResponseEntity<EmployeeIdproof>(idproof,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Insert a record into tblempidproof
	@RequestMapping(value = "/EmployeeIdProof", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addIdProof(@RequestBody String addIdProof) {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeIdproof idproof = (EmployeeIdproof) convertJsonToObject(
					addIdProof, EmployeeIdproof.class);
			employeeidproofdao = new EmployeeIdproofDAOImpl();
			int status = employeeidproofdao.addIdProof(idproof);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Update a record in empidproof
	@RequestMapping(value = "/EmployeeIdProof/{employeeidproofId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateIdProof(

	@RequestBody String updateIdProof, @PathVariable int employeeidproofId)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeIdproof idproof = (EmployeeIdproof) convertJsonToObject(
					updateIdProof, EmployeeIdproof.class);
			employeeidproofdao = new EmployeeIdproofDAOImpl();
			idproof.setEmpidproofId(employeeidproofId);
			int status = employeeidproofdao.updateIdProof(idproof);
			if (status == 0) {
				return new ResponseEntity<String>("Error in Updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (JsonSyntaxException je) {
			// je.printStackTrace();
			return new ResponseEntity<String>("Error in JSON data",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Exceptions",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record in Employeeidproof
	@RequestMapping(value = "/EmployeeIdProof/{employeeidproofId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteIdProof(
			@PathVariable int employeeidproofId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			employeeidproofdao = new EmployeeIdproofDAOImpl();
			int status = employeeidproofdao.deleteIdProof(employeeidproofId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting the data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * EMPLOYEE LEAVE TABLE:tblempleave
	 */
/*
	// GET A RECORD FROM EMPLOYEE LEAVE TABLE USING empeducationId
	@RequestMapping(value = "EmployeeLeave/{empleaveId}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeLeave> getEmployeeLeavetId(
			@PathVariable int empleaveId) {
		try {
			employeeleavedao = new EmployeeLeaveDAOImpl();
			EmployeeLeave employeeleave = employeeleavedao
					.getEmployeeLeaveById(empleaveId);
			if (employeeleave != null) {
				return new ResponseEntity<EmployeeLeave>(employeeleave,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<EmployeeLeave>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
	// GET A RECORD FROM EMPLOYEE LEAVE TABLE USING employeeId
			@RequestMapping(value = "EmployeeLeave/{employeeId}", method = RequestMethod.GET)
			public ResponseEntity<List<EmployeeLeave>> getEmployeeLeavetbyEmployeeId(
					@PathVariable int employeeId,@RequestHeader int data) {
				try {
					employeeleavedao = new EmployeeLeaveDAOImpl();
					List<EmployeeLeave> employeeleave = employeeleavedao
							.getEmployeebyEmployeeId(employeeId,data);
					if (employeeleave != null) {
						return new ResponseEntity<List<EmployeeLeave>>(employeeleave,
								HttpStatus.OK);
					} else {
						return new ResponseEntity<List<EmployeeLeave>>(HttpStatus.NO_CONTENT);
					}

				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}


	// GET ALL RECORD FROM EMPLOYEE LEAVE TABLE
	@RequestMapping(value = "EmployeeLeave", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeLeave>> getAllEmployeeLeave(
			@RequestHeader int data) {
		try {
			employeeleavedao = new EmployeeLeaveDAOImpl();
			List<EmployeeLeave> employeeleave = employeeleavedao
					.getAllEmployeeLeave(data);
			return new ResponseEntity<List<EmployeeLeave>>(employeeleave,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// INSERT A RECORD IN EMPLOYEE LEAVE TABLE
	@RequestMapping(value = "EmployeeLeave", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addEmployeeLeave(
			@RequestBody String addEmployeeLeave) {
		responseHeader.set("Content-type", "text/plain");
		try {

			EmployeeLeave EmployeeLeave = (EmployeeLeave) convertJsonToObject(
					addEmployeeLeave, EmployeeLeave.class);
			employeeleavedao = new EmployeeLeaveDAOImpl();
			int status = employeeleavedao.addEmployeeLeave(EmployeeLeave);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// DELETE A RECORD FROM EMPLOYEE LEAVE TABLE
	@RequestMapping(value = "EmployeeLeave/{empleaveId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployeeLeave(
			@PathVariable int empleaveId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			employeeleavedao = new EmployeeLeaveDAOImpl();
			int status = employeeleavedao.deleteEmployeeLeave(empleaveId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// update details for EmployeeLeave
	@RequestMapping(value = "EmployeeLeave/{empleaveId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateEmployeeLeave(
			@RequestBody String updateEmployeeLeave,
			@PathVariable int empleaveId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeLeave employeeleave = (EmployeeLeave) convertJsonToObject(
					updateEmployeeLeave, EmployeeLeave.class);
			employeeleavedao = new EmployeeLeaveDAOImpl();
			employeeleave.setEmpleaveId(empleaveId);
			int status = employeeleavedao.updateEmployeeLeave(employeeleave);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Employee leave Eligibility Table :tblempleaveeligibility
	 */
	// Get all record in tblempleaveeligibility
	@RequestMapping(value = "/Empleaveeligiblity", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeLeaveEligibility>> getAllEmployeeLeaveEligibility(
			@RequestHeader int data) {
		try {
			employeeleaveeligibilitydao = new EmployeeLeaveEligibilityDAOImpl();
			List<EmployeeLeaveEligibility> empeligibility = employeeleaveeligibilitydao
					.getAllEmployeeLeaveEligibility(data);
			return new ResponseEntity<List<EmployeeLeaveEligibility>>(
					empeligibility, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	// Get empleaveeligibility by empleaveeligibilityId
		@RequestMapping(value = "/Empleaveeligiblity/{empleaveeligibilityId}", method = RequestMethod.GET)
		public Object getEmployeeLeaveEligibilityById(@RequestHeader int data,
				@PathVariable int empleaveeligibilityId) {
			try {
				employeeleaveeligibilitydao = new EmployeeLeaveEligibilityDAOImpl();
				List<EmployeeLeaveEligibility> employeeleaveeligibility = (List<EmployeeLeaveEligibility>) employeeleaveeligibilitydao.getEmployeeLeaveEligibilityById(empleaveeligibilityId, data);
				
				List<EmpLeaveEligileValues> ListEmpLeaveEligileValues= new ArrayList<EmpLeaveEligileValues>();
				//JSONArray jsonarray1=new JSONArray();
				Integer empleaveeligibility_id;
				Integer organization_id;
				 Integer employee_id;
				 Date from_date = null;
				 Date to_date = null;
				 Integer eligibilitydays;
				 Integer leavetype_id;
				 String leavetype = null;
				 Integer totaleligible_days;
				 String leave_description = null;
				 String flag;
				 String fd=null;
				 String td=null;
				 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

				 if (employeeleaveeligibility != null) {
				for (Object object1 : employeeleaveeligibility) {
					
					 Object array[] = (Object[]) object1;

					empleaveeligibility_id = (Integer) array[0];
	                organization_id = (Integer) array[1];
	                if(array[2]!=null)
	                {
	                employee_id = (Integer) array[2]; }
	                else
	                {
	                	 employee_id=0;	
	                }
	                from_date = (Date) array[3];
	                to_date = (Date) array[4];
	                if(from_date!=null && to_date!=null){
	                fd=format.format(from_date);
	                td=format.format(to_date);
	                }
					if(array[5]!=null)
					{
					eligibilitydays=(Integer) array[5];
					}
					else
					{
						eligibilitydays=0;
					}

					leavetype_id=(Integer) array[6];
	                leavetype = (String) array[7];
	                totaleligible_days = (Integer) array[8];
	                leave_description = (String) array[9];

					
					if(array[0]==null)
					{
						flag ="L";
					}
					else
					{
						flag="E";
					}
					EmpLeaveEligileValues jsonobject1= new EmpLeaveEligileValues();
					
					
					jsonobject1.setEmpleaveeligibility_id(empleaveeligibilityId);
					jsonobject1.setOrganization_id(organization_id);
					jsonobject1.setEmployee_id(employee_id);
					jsonobject1.setFrom_date(fd);
					jsonobject1.setTo_date(td);
					jsonobject1.setEligibilitydays(eligibilitydays);
					jsonobject1.setLeavetype_id(leavetype_id);
					jsonobject1.setLeavetype(leavetype);
				    jsonobject1.setTotaleligible_days(totaleligible_days);
					jsonobject1.setLeave_description(leave_description);
					jsonobject1.setFlag(flag);
					ListEmpLeaveEligileValues.add(jsonobject1);
					//return jsonobject1;
			}
		
				
				System.out.println(ListEmpLeaveEligileValues);
				
				return  ListEmpLeaveEligileValues;
				
				} else {
					return null;
					//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			return null;
				/*return new ResponseEntity<List<EmployeeLeaveEligibility>>(
						HttpStatus.INTERNAL_SERVER_ERROR);
			*/}
			//return empleaveeligibilityId;
		}

	// Insert a record into empleaveeligibility
	@RequestMapping(value = "Empleaveeligiblity", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addEmpleaveeligiblity(
			@RequestBody String addemployeeleaveeligibility) {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeLeaveEligibility employeeleaveeligibility = (EmployeeLeaveEligibility) convertJsonToObject(
					addemployeeleaveeligibility, EmployeeLeaveEligibility.class);
			employeeleaveeligibilitydao = new EmployeeLeaveEligibilityDAOImpl();
			int status = employeeleaveeligibilitydao
					.addEmployeeLeaveEligibility(employeeleaveeligibility);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in Empleaveeligiblity
	@RequestMapping(value = "/Empleaveeligiblity/{empleaveeligibilityId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateEmpleaveeligiblity(
			@RequestBody String updateEmpleaveeligiblity,
			@PathVariable int empleaveeligibilityId) throws Exception {
		responseHeader.set("Content-type", "text/plain");

		try {
			EmployeeLeaveEligibility employeeleaveeligibility = (EmployeeLeaveEligibility) convertJsonToObject(
					updateEmpleaveeligiblity, EmployeeLeaveEligibility.class);
			employeeleaveeligibilitydao = new EmployeeLeaveEligibilityDAOImpl();
			employeeleaveeligibility
					.setEmpleaveeligibilityId(empleaveeligibilityId);
			int status = employeeleaveeligibilitydao
					.updateEmployeeLeaveEligibility(employeeleaveeligibility);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record from Empleaveeligiblity
	@RequestMapping(value = "/Empleaveeligiblity/{empleaveeligibilityId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmpleaveeligiblity(
			@PathVariable int empleaveeligibilityId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			employeeleaveeligibilitydao = new EmployeeLeaveEligibilityDAOImpl();
			int status = employeeleaveeligibilitydao
					.deleteEmployeeLeaveEligibility(empleaveeligibilityId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Employee Marital Table :tblempmarital
	 */

	//  GET A RECORD FROM EMPLOYEEEDUCATION TABLE USING employeeId
		@RequestMapping(value = "/EmployeeMarital/{employeeId}", method = RequestMethod.GET)
		public ResponseEntity<List<EmployeeMarital>> getEmployeeMaritalId(
				@PathVariable int employeeId,@RequestHeader int data) {
			try {
				employeemaritaldao = new EmployeeMaritalDAOImpl();
				List<EmployeeMarital> employeemarital = employeemaritaldao
						.getEmployeeMaritalById(employeeId,data);
				if (employeemarital != null) {
						return new ResponseEntity<List<EmployeeMarital>>(
								employeemarital, HttpStatus.OK);
						} else {
							return new ResponseEntity<>(HttpStatus.NO_CONTENT);
						}
					} catch (HibernateException e) {
						e.printStackTrace();
						return new ResponseEntity<List<EmployeeMarital>>(
								HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
	// GET ALL RECORD FROM EMPLOYEEMARITAL TABLE
	@RequestMapping(value = "EmployeeMarital", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeMarital>> getAllEmployeeMarital(
			@RequestHeader int data) {
		try {
			employeemaritaldao = new EmployeeMaritalDAOImpl();
			List<EmployeeMarital> employeemarital = employeemaritaldao
					.getAllEmployeeMarital(data);
			return new ResponseEntity<List<EmployeeMarital>>(employeemarital,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// INSERT A RECORD IN EMPLOYEEMARITAL TABLE
	@RequestMapping(value = "/EmployeeMarital", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addEmployeeMarital(
			@RequestBody String addEmployeeMarital) {
		responseHeader.set("Content-type", "text/plain");
		try {

			EmployeeMarital employeemarital = (EmployeeMarital) convertJsonToObject(
					addEmployeeMarital, EmployeeMarital.class);
			employeemaritaldao = new EmployeeMaritalDAOImpl();
			int status = employeemaritaldao.addEmployeeMarital(employeemarital);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// DELETE A RECORD FROM EMPLOYEEMARITAL TABLE BY
	@RequestMapping(value = "EmployeeMarital/{empmaritalId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployeeMarital(
			@PathVariable int empmaritalId) {
		responseHeader.set("Content-type", "text/plain");

		try {
			employeemaritaldao = new EmployeeMaritalDAOImpl();
			int status = employeemaritaldao.deleteEmployeeMarital(empmaritalId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// UPDATE EMPLOYEE MARITAL DETAILS BY
	@RequestMapping(value = "EmployeeMarital/{empmaritalId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateEmployeemarital(
			@RequestBody String updateEmployeeMarital,
			@PathVariable int empmaritalId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeMarital employeemarital = (EmployeeMarital) convertJsonToObject(
					updateEmployeeMarital, EmployeeMarital.class);
			employeemaritaldao = new EmployeeMaritalDAOImpl();
			employeemarital.setEmpmaritalId(empmaritalId);
			int status = employeemaritaldao
					.updateEmployeeMarital(employeemarital);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * EMPLOYEE PERSONAL DETAILS Table :tblemppersonal
	 */

	// GET A RECORD FROM tblemppersonal TABLE USING emppersonalId
		@RequestMapping(value = "/EmployeePersonal/{employeeId}", method = RequestMethod.GET)
		public ResponseEntity<List<EmployeePersonal>> getEmpPersonaldtlById(
				@PathVariable int employeeId,@RequestHeader int data) {
			try {
				employeepersonaldao = new EmployeePersonalDAOImpl();
				List<EmployeePersonal> employeepersonal = employeepersonaldao
						.getEmpPersonal(employeeId,data);
				if (employeepersonal != null) {
						return new ResponseEntity<List<EmployeePersonal>>(
								employeepersonal, HttpStatus.OK);
						} else {
							return new ResponseEntity<>(HttpStatus.NO_CONTENT);
						}
					} catch (HibernateException e) {
						e.printStackTrace();
						return new ResponseEntity<List<EmployeePersonal>>(
								HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}

	// GET ALL RECORD FROM tblemppersonal
	@RequestMapping(value = "/EmployeePersonal", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeePersonal>> getAllEmpPersonaldtl(
			@RequestHeader int data) {
		try {
			employeepersonaldao = new EmployeePersonalDAOImpl();
			List<EmployeePersonal> emppersonal = employeepersonaldao
					.getAllEmpPersonal(data);
			return new ResponseEntity<List<EmployeePersonal>>(emppersonal,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// INSERT A RECORD IN EMPLOYEE PERSONAL TABLE
	@RequestMapping(value = "/EmployeePersonal", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addEmployeePersonal(
			@RequestBody String addEmployeePersonal) {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeePersonal employeepersonal = (EmployeePersonal) convertJsonToObject(
					addEmployeePersonal, EmployeePersonal.class);
			employeepersonaldao = new EmployeePersonalDAOImpl();
			int status = employeepersonaldao.addEmpPersonal(employeepersonal);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// DELETE A RECORD FROM EMPLOYEE PERSONAL TABLE
	@RequestMapping(value = "/EmployeePersonal/{emppersonalId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployeePersonal(
			@PathVariable int emppersonalId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			employeepersonaldao = new EmployeePersonalDAOImpl();
			int status = employeepersonaldao.deleteEmpPersonal(emppersonalId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// UPDATE A RECORD IN EMPLOYEE PERSONAL TABLE
	@RequestMapping(value = "/EmployeePersonal/{emppersonalId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateEmployeePersonal(
			@RequestBody String updateEmployeepersonal,
			@PathVariable int emppersonalId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeePersonal employeepersonal = (EmployeePersonal) convertJsonToObject(
					updateEmployeepersonal, EmployeePersonal.class);
			employeepersonaldao = new EmployeePersonalDAOImpl();
			employeepersonal.setEmppersonalId(emppersonalId);
			int status = employeepersonaldao
					.updateEmpPersonal(employeepersonal);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * EmployeeSalary Table :tblempsalary
	 */

	// Get EmployeeSalary by employeesalaryid
	@RequestMapping(value = "EmployeeSalary/{employeesalaryId}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeSalary> getEmployeeSalaryId(
			@PathVariable int employeesalaryId) {
		try {
			employeesalarydao = new EmployeeSalaryDAOImpl();
			EmployeeSalary employeesalary = employeesalarydao
					.getEmployeeSalaryById(employeesalaryId);

			if (employeesalary != null) {
				return new ResponseEntity<EmployeeSalary>(employeesalary,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return new ResponseEntity<EmployeeSalary>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all record in EmployeeSalary
	@RequestMapping(value = "EmployeeSalary", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<EmployeeSalary>> getAllEmployeeSalary(
			@RequestHeader int data) {
		try {
			employeesalarydao = new EmployeeSalaryDAOImpl();
			List<EmployeeSalary> employeesalary = employeesalarydao
					.getAllEmployeeSalary(data);

			return new ResponseEntity<List<EmployeeSalary>>(employeesalary,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record in EmployeeSalary
	@RequestMapping(value = "EmployeeSalary", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addEmployeeSalary(
			@RequestBody String addemployeesalaryId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeSalary employeesalary = (EmployeeSalary) convertJsonToObject(
					addemployeesalaryId, EmployeeSalary.class);
			employeesalarydao = new EmployeeSalaryDAOImpl();
			int status = employeesalarydao.addEmployeeSalary(employeesalary);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete EmployeeSalary by employeesalaryId
	@RequestMapping(value = "EmployeeSalary/{employeesalaryId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployeeSalary(
			@PathVariable int employeesalaryId) {
		responseHeader.set("Content-type", "text/plain");

		try {
			employeesalarydao = new EmployeeSalaryDAOImpl();
			int status = employeesalarydao
					.deleteEmployeeSalary(employeesalaryId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update EmployeeSalary by employeesalaryId
	@RequestMapping(value = "EmployeeSalary/{employeesalaryId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateEmployeeSalary(
			@RequestBody String updateEmployeeSalary,
			@PathVariable int employeesalaryId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeSalary employeesalary = (EmployeeSalary) convertJsonToObject(
					updateEmployeeSalary, EmployeeSalary.class);
			employeesalarydao = new EmployeeSalaryDAOImpl();
			employeesalary.setEmpsalaryId(employeesalaryId);
			int status = employeesalarydao.updateEmployeeSalary(employeesalary);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * EmployeeVisa table:tblempvisa
	 */
	// Get all record in employeevisa
	@RequestMapping(value = "EmployeeVisa", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeVisa>> getAllEmployeeVisa(
			@RequestHeader int data) {
		try {
			employeevisadao = new EmployeeVisaDAOImpl();
			List<EmployeeVisa> employee = employeevisadao
					.getAllEmployeeVisa(data);
			return new ResponseEntity<List<EmployeeVisa>>(employee,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

/*	// Get a record in employeevisa
	@RequestMapping(value = "EmployeeVisa/{employeevisaId}", method = RequestMethod.GET)
	public ResponseEntity<EmployeeVisa> getEmployeeVisaId(
			@PathVariable int employeevisaId) {
		try {
			employeevisadao = new EmployeeVisaDAOImpl();
			EmployeeVisa employee = employeevisadao
					.getEmployeeVisaById(employeevisaId);
			if (employee != null) {
				return new ResponseEntity<EmployeeVisa>(employee, HttpStatus.OK);
			} else {
				return new ResponseEntity<EmployeeVisa>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/
	
	//Get record by employeeId
		@RequestMapping(value = "EmployeeVisa/{employeeId}", method = RequestMethod.GET)
		public ResponseEntity<List<EmployeeVisa>> getEmployeeVisabyEmployeeId(
				@PathVariable int employeeId, @RequestHeader int data) {
			try {
				employeevisadao = new EmployeeVisaDAOImpl();
				List<EmployeeVisa> employee = employeevisadao.getEmployeeVisaByEmployeeId(employeeId,data);
				if (employee != null) {
					return new ResponseEntity<List<EmployeeVisa>>( employee, HttpStatus.OK);
				} else {
					return new ResponseEntity<List<EmployeeVisa>>(HttpStatus.NO_CONTENT);
				}

			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	// Insert a record in employeevisa
	@RequestMapping(value = "/EmployeeVisa", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addEmployeeVisa(
			@RequestBody String addEmployeeVisa) {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeVisa employeevisa = (EmployeeVisa) convertJsonToObject(
					addEmployeeVisa, EmployeeVisa.class);
			employeevisadao = new EmployeeVisaDAOImpl();
			int status = employeevisadao.addEmployeeVisa(employeevisa);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a EmployeeVisa by employeevisaId
	@RequestMapping(value = "EmployeeVisa/{employeevisaId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployeeVisa(
			@PathVariable int employeevisaId) {
		responseHeader.set("Content-type", "text/plain");

		try {
			employeevisadao = new EmployeeVisaDAOImpl();
			int status = employeevisadao.deleteEmployeeVisa(employeevisaId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update EmployeeVisa by employeevisaId
	@RequestMapping(value = "EmployeeVisa/{employeevisaId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateEmployeeVisa(
			@RequestBody String updateEmployeeVisa,
			@PathVariable int employeevisaId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			EmployeeVisa employeevisa = (EmployeeVisa) convertJsonToObject(
					updateEmployeeVisa, EmployeeVisa.class);
			employeevisadao = new EmployeeVisaDAOImpl();
			employeevisa.setEmpvisaId(employeevisaId);
			int status = employeevisadao.updateEmployeeVisa(employeevisa);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Holiday Table :tblholiday
	 */
	// Get holiday by id
	@RequestMapping(value = "Holiday/{holidayId}", method = RequestMethod.GET)
	public ResponseEntity<Holiday> getHolidayId(@PathVariable int holidayId) {
		try {
			holidaydao = new HolidayDAOImpl();
			Holiday holiday = holidaydao.getHolidayById(holidayId);
			if (holiday != null) {
				return new ResponseEntity<Holiday>(holiday, HttpStatus.OK);
			} else {
				return new ResponseEntity<Holiday>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all record in holiday
	@RequestMapping(value = "Holiday", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<List<Holiday>> getAllHoliday(@RequestHeader int data) {
		try {
			holidaydao = new HolidayDAOImpl();
			List<Holiday> holiday = holidaydao.getAllHoliday(data);
			return new ResponseEntity<List<Holiday>>(holiday, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record in holiday
	@RequestMapping(value = "/Holiday", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addHoliday(@RequestBody String addHoliday) {
		responseHeader.set("Content-type", "text/plain");
		try {

			Holiday holiday = (Holiday) convertJsonToObject(addHoliday,
					Holiday.class);
			holidaydao = new HolidayDAOImpl();
			int status = holidaydao.addHoliday(holiday);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete holiday by id
	@RequestMapping(value = "Holiday/{holidayId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteHoliday(@PathVariable int holidayId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			holidaydao = new HolidayDAOImpl();
			int status = holidaydao.deleteHoliday(holidayId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Update holiday by id
	@RequestMapping(value = "Holiday/{holidayId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateHoliday(
			@RequestBody String updateHoliday, @PathVariable int holidayId)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			Holiday holiday = (Holiday) convertJsonToObject(updateHoliday,
					Holiday.class);
			holidaydao = new HolidayDAOImpl();
			holiday.setHolidayId(holidayId);
			int status = holidaydao.updateHoliday(holiday);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * IdType Table :tbldtype
	 */

	// Get idtype by id
	@RequestMapping(value = "IdType/{idtypeId}", method = RequestMethod.GET)
	public ResponseEntity<Idtype> getIdtypeId(@PathVariable int idtypeId) {
		try {
			idtypedao = new IdtypeDAOImpl();
			Idtype idtype = idtypedao.getIdtypeById(idtypeId);
			if (idtype != null) {
				return new ResponseEntity<Idtype>(idtype, HttpStatus.OK);
			} else {
				return new ResponseEntity<Idtype>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Get all record in idtype
	@RequestMapping(value = "IdType", method = RequestMethod.GET)
	public ResponseEntity<List<Idtype>> getAllIdtype(@RequestHeader int data) {
		try {
			idtypedao = new IdtypeDAOImpl();
			List<Idtype> idtype = idtypedao.getAllIdtype(data);
			return new ResponseEntity<List<Idtype>>(idtype, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record in idtype
	@RequestMapping(value = "/IdType", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addIdtype(@RequestBody String addIdtype) {
		responseHeader.set("Content-type", "text/plain");
		try {

			Idtype idtype = (Idtype) convertJsonToObject(addIdtype,
					Idtype.class);
			idtypedao = new IdtypeDAOImpl();
			int status = idtypedao.addIdtype(idtype);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete idtype by id
	@RequestMapping(value = "IdType/{idtypeId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteIdtype(@PathVariable int idtypeId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			idtypedao = new IdtypeDAOImpl();
			int status = idtypedao.deleteIdtype(idtypeId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update idtype by id
	@RequestMapping(value = "IdType/{idtypeId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateIdtype(
			@RequestBody String updateIdtype, @PathVariable int idtypeId)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			Idtype idtype = (Idtype) convertJsonToObject(updateIdtype,
					Idtype.class);
			idtypedao = new IdtypeDAOImpl();
			idtype.setIdtypeId(idtypeId);
			int status = idtypedao.updateIdtype(idtype);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * LeaveType table:tblleavetype
	 */
	// Get a record leavetype
	@RequestMapping(value = "/LeaveType/{leavetypeId}/{fromdate}/{todate}", method = RequestMethod.GET)
	public ResponseEntity<LeaveType> getLeaveTypeById(
			@PathVariable int leavetypeId) {
		try {
			leavetypedao = new LeaveTypeDAOImpl();
			LeaveType leavetype = leavetypedao.getLeaveTypeById(leavetypeId);
			if (leavetype != null) {
				return new ResponseEntity<LeaveType>(leavetype, HttpStatus.OK);
			} else {
				return new ResponseEntity<LeaveType>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Get all the record in leavetype
	@RequestMapping(value = "/LeaveTypenodate", method = RequestMethod.GET)
	public ResponseEntity<List<LeaveType>> getAllLeaveTypenodate(
			@RequestHeader int data) {
		try {
			leavetypedao = new LeaveTypeDAOImpl();
			List<LeaveType> leavetype = leavetypedao.getAllLeaveTypenodate(data);
			return new ResponseEntity<List<LeaveType>>(leavetype, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all the record in leavetype eith date
		@RequestMapping(value = "/LeaveType/{fromdate}/{todate}", method = RequestMethod.GET)
		public ResponseEntity<List<LeaveType>> getAllLeaveType(
				@RequestHeader int data, @PathVariable String fromdate,@PathVariable String todate) {
			try {
				leavetypedao = new LeaveTypeDAOImpl();
				List<LeaveType> leavetype = leavetypedao.getAllLeaveType(data,fromdate,todate);
				return new ResponseEntity<List<LeaveType>>(leavetype, HttpStatus.OK);
			} catch (HibernateException he) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}		

	// Insert a record into leavetype
	@RequestMapping(value = "/LeaveType", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addLeaveType(@RequestBody String addLeaveType) {
		responseHeader.set("Content-type", "text/plain");
		try {
			LeaveType leavetype = (LeaveType) convertJsonToObject(addLeaveType,
					LeaveType.class);
			leavetypedao = new LeaveTypeDAOImpl();
			int status = leavetypedao.addLeaveType(leavetype);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			return new ResponseEntity<String>(ce.getConstraintName()+"Duplicate Entry Already Present In DataBase",responseHeader,
					HttpStatus.INTERNAL_SERVER_ERROR);
			/*return new ResponseEntity<String>("+++"+ce.getConstraintName()+"++++"+ce.getSQLState()
					+ " :Error in SQL to database",
					HttpStatus.INTERNAL_SERVER_ERROR);*/
		}catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",responseHeader,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Duplicate Entry "
					+ e.getMessage(),responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	// Delete a record in leavetype
	@RequestMapping(value = "/LeaveType/{leavetypeId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteLeaveType(@PathVariable int leavetypeId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			leavetypedao = new LeaveTypeDAOImpl();
			int status = leavetypedao.deleteLeaveType(leavetypeId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in leavetype
	@RequestMapping(value = "/LeaveType/{leavetypeId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateLeaveType(
			@RequestBody String updateleavetype, @PathVariable int leavetypeId)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			LeaveType leavetype = (LeaveType) convertJsonToObject(
					updateleavetype, LeaveType.class);
			leavetypedao = new LeaveTypeDAOImpl();
			leavetype.setLeavetypeId(leavetypeId);
			int status = leavetypedao.updateLeaveType(leavetype);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			return new ResponseEntity<String>(ce.getConstraintName()+"Already Present In DataBase",responseHeader,
					HttpStatus.INTERNAL_SERVER_ERROR);
			/*return new ResponseEntity<String>("+++"+ce.getConstraintName()+"++++"+ce.getSQLState()
					+ " :Error in SQL to database",
					HttpStatus.INTERNAL_SERVER_ERROR);*/
		}catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",responseHeader,
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			return new ResponseEntity<String>("Duplicate Entry "
					+ e.getMessage(), responseHeader,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Login Table:tbllogin
	 */

	// Authenticate the username and password
	@RequestMapping(value = "/Login", method = RequestMethod.POST, headers = "Accept=application/json")
	public JSONArray Authentication(@RequestBody String checklogin, String json) {

		logindao = new LoginDAOImpl();

		String encryptpassword = null;
		String encryptname = null;
		Gson gson = new Gson();
		Login login = new Login();
		login = gson.fromJson(checklogin, Login.class);

		try {
			encryptpassword = new Encryption()
					.encrypt(login.getLoginPassword());
			encryptname = new Encryption().encrypt(login.getLoginName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		login.setEncryptPassword(encryptpassword);
		login.setEncryptName(encryptname);
		List<Login> authenticate = logindao.Authentication(login);
		JSONObject jsonobject= new JSONObject();
		JSONArray jsonarray=new JSONArray();
		for (Object object : authenticate) {
			login = new Login();
			Object array[] = (Object[]) object;
			Integer login_id = (Integer) array[0];
			Integer organization_id = (Integer) array[1];
			Integer employee_id = (Integer) array[2];
			String login_name = (String) array[3];
			jsonobject.put("loginId", login_id);
			jsonobject.put("organizationId", organization_id);
			jsonobject.put("employeeId",employee_id);
			jsonobject.put("loginName",login_name);
			jsonarray.add(jsonobject);
		}
		return jsonarray;
	}
	// create new login
	@RequestMapping(value = "/CreateLogin", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> insertUser(@RequestBody String value) {
		String username = null;
		String password = null;

		Gson gson = new Gson();
		Login login = new Login();
		login = gson.fromJson(value, Login.class);
		try {
			username = new Encryption().encrypt(login.getLoginName());
			password = new Encryption().encrypt(login.getLoginPassword());
		} catch (Exception e) {

			e.printStackTrace();
		}
		login.setEncryptName(username);
		login.setEncryptPassword(password);
		try {

			logindao = new LoginDAOImpl();
			logindao.addLogin(login);

			return new ResponseEntity<String>("Inserted Successfully",
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<String>("Insertion failed",
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Get all Login records

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public List<Login> getAllLogin() {
		try {
			logindao = new LoginDAOImpl();
			List<Login> login = logindao.getAllLogin();
			return login;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/Login/{login_id}", method = RequestMethod.GET)
	public Login getLoginById(@PathVariable int login_id) {
		try {
			logindao = new LoginDAOImpl();
			Login login = logindao.getLoginById(login_id);
			return login;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//ChangePassword
	@RequestMapping(value = "/ChangePassword/{loginId}", method = RequestMethod.PUT)
	public ResponseEntity<String> changePassword(@RequestBody String value,@PathVariable int loginId) 
	
	{try{
		logindao= new LoginDAOImpl();
		
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(value).getAsJsonObject();
		int status=logindao.changePassword(loginId,json.get("userName").getAsString(),json.get("currentPassword").getAsString(),json.get("newPassword").getAsString(),json.get("updateBy").getAsInt());
		if(status==0)
		{
			return new ResponseEntity<String>("Incorrect password",responseHeader,
					 HttpStatus.UNAUTHORIZED);
		}
		else
		{
			return new ResponseEntity<String>("Password Changed Successfully",responseHeader,
					 HttpStatus.OK);
		}
	}
	catch(Exception e)
	{
		return new ResponseEntity<String>("Something Went wrong "
				+ e.getMessage(),responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	}
	//Forget password
	@RequestMapping(value = "/ForgetPassword", method = RequestMethod.POST)
	public ResponseEntity<String> forgetPassword(@RequestBody String value) 
	{try {
		logindao= new LoginDAOImpl();
		int status=logindao.forgetPassword(value);
		if(status==0)
		{
			return new ResponseEntity<String>("Error in sending mail ",responseHeader
					, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else
		{
			return new ResponseEntity<String>("Check your email for your password",responseHeader
					, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	catch(Exception e)
	{
		return new ResponseEntity<String>("Something Went wrong "
				+ e.getMessage(),responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
	}
		
	}

	/*
	 * Organization Table:tblorganization
	 */
	// Get all the record from organization
	@RequestMapping(value = "/Organization", method = RequestMethod.GET)
	public ResponseEntity<List<Organization>> getAllorganization() {
		try {
			organizationdao = new OrganizationDAOImpl();
			List<Organization> organization = organizationdao
					.getAllOrganization();
			return new ResponseEntity<List<Organization>>(organization,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Get a record from organization
	@RequestMapping(value = "/Organization/{organizationId}", method = RequestMethod.GET)
	public ResponseEntity<Organization> getOrganizationById(
			@PathVariable int organizationId) {
		try {
			organizationdao = new OrganizationDAOImpl();
			Organization organization = organizationdao
					.getOrganizationById(organizationId);
			if (organization != null) {
				return new ResponseEntity<Organization>(organization,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<Organization>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record into organization
	@RequestMapping(value = "/Organization", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<String> addOrganization(
			@RequestBody String addOrganization) {
		responseHeader.set("Content-type", "text/plain");
		try {
			Organization organization = (Organization) convertJsonToObject(
					addOrganization, Organization.class);
			organizationdao = new OrganizationDAOImpl();
			int status = organizationdao.addOrganization(organization);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in organization
	@RequestMapping(value = "/Organization/{organizationId}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public ResponseEntity<String> updateOrganization(
			@RequestBody String updateOrganization,
			@PathVariable int organizationId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			Organization organization = (Organization) convertJsonToObject(
					updateOrganization, Organization.class);
			organizationdao = new OrganizationDAOImpl();
			organization.setOrganizationId(organizationId);
			int status = organizationdao.updateOrganization(organization);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record in Organization
	@RequestMapping(value = "/Organization/{organizationId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteOrganization(
			@PathVariable int organizationId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			organizationdao = new OrganizationDAOImpl();
			int status = organizationdao.deleteOrganization(organizationId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Role Table :tblrole
	 */
	// Get all record in role
	@RequestMapping(value = "/Role", method = RequestMethod.GET)
	public ResponseEntity<List<Role>> getAllRole(@RequestHeader int data) {
		try {
			roledao = new RoleDAOImpl();
			List<Role> role = roledao.getAllRole(data);
			return new ResponseEntity<List<Role>>(role, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get role by id
	@RequestMapping(value = "/Role/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<Role> getRoleById(@PathVariable int roleId) {
		try {
			roledao = new RoleDAOImpl();
			Role role = roledao.getRoleById(roleId);
			if (role != null) {
				return new ResponseEntity<Role>(role, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return new ResponseEntity<Role>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record into role
	@RequestMapping(value = "Role", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addRole(@RequestBody String addRole) {
		responseHeader.set("Content-type", "text/plain");
		try {
			Role role = (Role) convertJsonToObject(addRole, Role.class);
			roledao = new RoleDAOImpl();
			int status = roledao.addRole(role);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in role
	@RequestMapping(value = "/Role/{roleId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateRole(@RequestBody String updateRole,
			@PathVariable int roleId) throws Exception {
		responseHeader.set("Content-type", "text/plain");

		try {
			Role role = (Role) convertJsonToObject(updateRole, Role.class);
			roledao = new RoleDAOImpl();
			role.setRoleId(roleId);
			int status = roledao.updateRole(role);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record from role
	@RequestMapping(value = "/Role/{roleId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteRole(@PathVariable int roleId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			roledao = new RoleDAOImpl();

			int status = roledao.deleteRole(roleId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * Screen Table :tblscreen
	 */
	// Get all record in screen
	@RequestMapping(value = "/Screen", method = RequestMethod.GET)
	public ResponseEntity<List<Screen>> getAllScreen() {
		try {
			screendao = new ScreenDAOImpl();
			List<Screen> screen = screendao.getAllScreen();
			return new ResponseEntity<List<Screen>>(screen, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get Screen by screenId
	@RequestMapping(value = "/Screen/{screenId}", method = RequestMethod.GET)
	public ResponseEntity<Screen> getScreenById(@PathVariable int screenId) {
		try {
			screendao = new ScreenDAOImpl();
			Screen screen = screendao.getScreenById(screenId);
			if (screen != null) {
				return new ResponseEntity<Screen>(screen, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record into Screen
	@RequestMapping(value = "Screen", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addScreen(@RequestBody String addScreen) {
		responseHeader.set("Content-type", "text/plain");
		try {
			Screen screen = (Screen) convertJsonToObject(addScreen,
					Screen.class);
			screendao = new ScreenDAOImpl();
			int status = screendao.addScreen(screen);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in Screen
	@RequestMapping(value = "/Screen/{screenId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateScreen(
			@RequestBody String updateScreen, @PathVariable int screenId)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			Screen screen = (Screen) convertJsonToObject(updateScreen,
					Screen.class);
			screendao = new ScreenDAOImpl();
			screen.setScreenId(screenId);
			int status = screendao.updateScreen(screen);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record from Screen
	@RequestMapping(value = "/Screen/{screenId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteScreen(@PathVariable int screenId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			screendao = new ScreenDAOImpl();

			int status = screendao.deleteScreen(screenId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * ScreenAuth Table :tblscreenauth
	 */
	// Get all record in screenauth
	@RequestMapping(value = "/ScreenAuth", method = RequestMethod.GET)
	public ResponseEntity<List<ScreenAuth>> getAllScreenAuth(
			@RequestHeader int data) {
		try {
			screenauthdao = new ScreenAuthDAOImpl();
			List<ScreenAuth> role = screenauthdao.getAllScreenAuth(data);
			return new ResponseEntity<List<ScreenAuth>>(role, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get screenauth by id
	@RequestMapping(value = "/ScreenAuth/{roleId}", method = RequestMethod.GET)
	public ResponseEntity<ScreenAuth> getScreenAuthById(@PathVariable int roleId) {
		try {
			screenauthdao = new ScreenAuthDAOImpl();
			ScreenAuth screenauth = screenauthdao.getScreenAuthById(roleId);
			if (screenauth != null) {
				return new ResponseEntity<ScreenAuth>(screenauth, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			return new ResponseEntity<ScreenAuth>(
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Insert a record into screenauth
	@RequestMapping(value = "ScreenAuth", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addScreenAuth(
			@RequestBody String addScreenAuth) {
		responseHeader.set("Content-type", "text/plain");
		try {
			ScreenAuth screenauth = (ScreenAuth) convertJsonToObject(
					addScreenAuth, ScreenAuth.class);
			screenauthdao = new ScreenAuthDAOImpl();
			int status = screenauthdao.addScreenAuth(screenauth);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in ScreenAuth
	@RequestMapping(value = "/ScreenAuth/{screenauthId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateScreenAuth(
			@RequestBody String updateScreenAuth, @PathVariable int screenauthId)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			ScreenAuth screen = (ScreenAuth) convertJsonToObject(
					updateScreenAuth, ScreenAuth.class);
			screenauthdao = new ScreenAuthDAOImpl();
			screen.setScreenauthId(screenauthId);
			int status = screenauthdao.updateScreenAuth(screen);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record from ScreenAuth
	@RequestMapping(value = "/ScreenAuth/{roleId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteScreenAuth(@PathVariable int roleId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			roledao = new RoleDAOImpl();

			int status = roledao.deleteRole(roleId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * State table:tblstateprovince
	 */
	// Get a record from state
	@RequestMapping(value = "/State/{stateId}", method = RequestMethod.GET)
	public ResponseEntity<StateProvince> getStateById(@PathVariable int stateId) {
		try {
			stateprovincedao = new StateProvinceDAOImpl();
			StateProvince stateprovince = stateprovincedao
					.getStateById(stateId);
			if (stateprovince != null) {
				return new ResponseEntity<StateProvince>(stateprovince,
						HttpStatus.OK);
			} else {
				return new ResponseEntity<StateProvince>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get all the record in state
	@RequestMapping(value = "/State", method = RequestMethod.GET)
	public ResponseEntity<List<StateProvince>> getAllState(
			@RequestHeader int data) {
		try {
			Country country = new Country(data);
			stateprovincedao = new StateProvinceDAOImpl();
			List<StateProvince> stateprovince = stateprovincedao
					.getAllState(country);
			return new ResponseEntity<List<StateProvince>>(stateprovince,
					HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Insert a record in state
	@RequestMapping(value = "/State", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addState(@RequestBody String addState) {
		responseHeader.set("Content-type", "text/plain");
		try {
			StateProvince stateprovince = (StateProvince) convertJsonToObject(
					addState, StateProvince.class);
			stateprovincedao = new StateProvinceDAOImpl();
			int status = stateprovincedao.addState(stateprovince);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Inserted Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Update a record in state
	@RequestMapping(value = "/State/{stateId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateState(@RequestBody String updatestate,
			@PathVariable int stateId) throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			StateProvince stateprovince = (StateProvince) convertJsonToObject(
					updatestate, StateProvince.class);
			stateprovincedao = new StateProvinceDAOImpl();
			stateprovince.setStateprovinceId(stateId);
			int status = stateprovincedao.updateState(stateprovince);
			if (status == 0) {
				return new ResponseEntity<String>("Error in updating data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Updated Succesfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getSQLException().toString(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Delete a record in state
	@RequestMapping(value = "/State/{stateId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteState(@PathVariable int stateId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			stateprovincedao = new StateProvinceDAOImpl();
			int status = stateprovincedao.deleteState(stateId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in deleting data",
						responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<String>("Deleted Succesfully",
						responseHeader, HttpStatus.OK);
			}

		} catch (ConstraintViolationException ce) {
			ce.printStackTrace();
			return new ResponseEntity<String>(ce.getConstraintName(),
					HttpStatus.BAD_REQUEST);
		} catch (HibernateException he) {
			return new ResponseEntity<String>(he.getMessage()
					+ " :Error in connecting to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Something Went wrong "
					+ e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * VisaType table:tblvisatype
	 */
	// Get a record in visatype by visatypeId
	@RequestMapping(value = "/VisaType/{visatypeId}", method = RequestMethod.GET)
	public ResponseEntity<VisaType> getvisatypeId(@PathVariable int visatypeId) {
		try {
			visatypedao = new VisaTypeDAOImpl();
			VisaType visatype = visatypedao.getVisaTypeById(visatypeId);
			if (visatype != null) {
				return new ResponseEntity<VisaType>(visatype, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Get all record in visatype
	@RequestMapping(value = "VisaType", method = RequestMethod.GET)
	public ResponseEntity<List<VisaType>> getAllVisatype(@RequestHeader int data) {
		try {
			visatypedao = new VisaTypeDAOImpl();
			List<VisaType> visatype = visatypedao.getAllVisaType(data);
			return new ResponseEntity<List<VisaType>>(visatype, HttpStatus.OK);
		} catch (HibernateException he) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Insert a record in visatype
	@RequestMapping(value = "/VisaType", method = RequestMethod.POST, headers = "content-type=application/json")
	public ResponseEntity<String> addVisatype(@RequestBody String addVisa) {
		responseHeader.set("Content-type", "text/plain");
		try {
			VisaType visatype = (VisaType) convertJsonToObject(addVisa,
					VisaType.class);
			visatypedao = new VisaTypeDAOImpl();
			int status = visatypedao.addVisaType(visatype);
			if (status == 0) {
				return new ResponseEntity<String>("Error in inserting data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data inserted Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (JsonSyntaxException je) {
			// je.printStackTrace();
			return new ResponseEntity<String>("Error in JSON input",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internal Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Delete a record in visatype(Hard delete)
	@RequestMapping(value = "/VisaType/{VisaId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteVisatype(@PathVariable int VisaId) {
		responseHeader.set("Content-type", "text/plain");
		try {
			visatypedao = new VisaTypeDAOImpl();
			int status = visatypedao.deleteVisaType(VisaId);
			if (status == 0) {
				return new ResponseEntity<String>("Error in Deleting data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data Deleted Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internel Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update a record in visatype
	@RequestMapping(value = "/VisaType/{visaId}", method = RequestMethod.PUT, headers = "content-type=application/json")
	public ResponseEntity<String> updateVisatype(
			@RequestBody String updatevisatype, @PathVariable int visaId)
			throws Exception {
		responseHeader.set("Content-type", "text/plain");
		try {
			VisaType visatype = (VisaType) convertJsonToObject(updatevisatype,
					VisaType.class);
			visatypedao = new VisaTypeDAOImpl();
			visatype.setVisaTypeId(visaId);
			int status = visatypedao.updateVisaType(visatype);
			if (status == 0) {
				return new ResponseEntity<String>("Error in Updating data",
						responseHeader, HttpStatus.BAD_REQUEST);
			} else {
				return new ResponseEntity<String>("Data Updated Successfully",
						responseHeader, HttpStatus.OK);
			}
		} catch (JsonSyntaxException je) {
			// je.printStackTrace();
			return new ResponseEntity<String>("Error in JSON input",
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Internel Server Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@RequestMapping(value = "/LeaveCalculation/{employeeId}", method = RequestMethod.GET)
	public List LeaveCalculation(@RequestHeader int data,@PathVariable int employeeId){
		LeaveCalculator leave=new LeaveCalculator();
		List leavecalculation= leave.LeaveCalculator(data,employeeId);
		JSONObject jsonobject= new JSONObject();
		JSONArray jsonarray=new JSONArray();
		for (Object object : leavecalculation) {
			Object array[] = (Object[]) object;
			Integer leavetypeId = (Integer) array[0];
			Integer eligibilitydays = (Integer) array[1];
			Object availabledays =  array[2];
			jsonobject.put("leavetypeId", leavetypeId);
			jsonobject.put("eligibilitydays", eligibilitydays);
			jsonobject.put("availabledays",availabledays);
			jsonarray.add(jsonobject);
		}
		return jsonarray;
	}

}
