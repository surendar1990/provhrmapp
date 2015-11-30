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
		Query query=session.createSQLQuery("select tblempleaveeligibility.leavetype_id, tblempleaveeligibility.eligibilitydays as eligibilitydays,(tblempleaveeligibility.eligibilitydays-tblempleave.total_days) as eligibility"
				+ " from tblempleaveeligibility inner join tblempleave on tblempleaveeligibility.employee_id='"+employeeId+"' and tblempleave.employee_id='"+employeeId+"' and"
						+ " tblempleaveeligibility.leavetype_id=tblempleave.leavetype_id and tblempleaveeligibility.organization_id='"+organizationId+"' and"
								+ " tblempleave.organization_id='"+organizationId+"'");
				session.getTransaction().commit();
		return query.list();
		
		
	}

}
