package com.prov.hrm.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.prov.hrm.leavetype.LeaveType;

public class LeaveCalculator {
	
	public List LeaveCalculator(int organizationId, int employeeId){
		Session session=SessionFactoryUtil.getSessionFactory().openSession();
		List<LeaveType> leave1=new ArrayList<>();
		List<LeaveType> leave2=new ArrayList<>();
		session.beginTransaction();
		/*Query query=session.createSQLQuery("select tblempleaveeligibility.leavetype_id, tblempleaveeligibility.eligibilitydays as eligibilitydays,(tblempleaveeligibility.eligibilitydays-tblempleave.total_days) as eligibility"
				+ " from tblempleaveeligibility inner join tblempleave on tblempleaveeligibility.employee_id='"+employeeId+"' and tblempleave.employee_id='"+employeeId+"' and"
						+ " tblempleaveeligibility.leavetype_id=tblempleave.leavetype_id and tblempleaveeligibility.organization_id='"+organizationId+"' and"
								+ " tblempleave.organization_id='"+organizationId+"'");
		Query query=session.createSQLQuery("select tblleavetype.leavetype, tblleavetype.leavetype_id,tblempleaveeligibility.eligibilitydays,"
				+ "( select sum(tblempleave.total_days) from tblempleave where tblempleave.leavetype_id = tblleavetype.leavetype_id and"
				+ " tblempleave.employee_id='"+employeeId+"' and tblempleave.organization_id='"+organizationId+"')-(tblempleaveeligibility.eligibilitydays) as sum_value"
				+ "	from tblleavetype join tblempleaveeligibility where tblleavetype.organization_id=3 and "
				+ "tblempleaveeligibility.employee_id='"+employeeId+"' and tblempleaveeligibility.organization_id='"+organizationId+"' and"
				+ " tblempleaveeligibility.leavetype_id=tblleavetype.leavetype_id ");
		*/
		
		Query query=session.createSQLQuery("select tblleavetype.leavetype, tblleavetype.leavetype_id,tblempleaveeligibility.eligibilitydays,"
				+ "(tblempleaveeligibility.eligibilitydays)-(  select coalesce(sum(a.total_days),0) from tblempleave as a"
				+ " where a.leavetype_id = tblleavetype.leavetype_id and a.employee_id='"+employeeId+"'"
				+ " and a.organization_id='"+organizationId+"' and a.delete_flag=0 and a.status in ('a','p')) as sum_value"
								+ " from tblleavetype join tblempleaveeligibility where tblleavetype.organization_id='"+organizationId+"' "
										+ "and tblempleaveeligibility.employee_id='"+employeeId+"' and tblempleaveeligibility.organization_id='"+organizationId+"'"
												+ " and tblempleaveeligibility.leavetype_id=tblleavetype.leavetype_id and tblempleaveeligibility.delete_flag=0 and tblleavetype.delete_flag=0");
		session.getTransaction().commit();
		return query.list();
		
		
	}

}
