package com.prov.hrm.utility;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.web.bind.annotation.PathVariable;

import com.prov.hrm.organization.Organization;
import com.prov.hrm.organization.OrganizationDAOImpl;

public class Getaccountyear {
	
	public static HashMap<String,String> getaccountingyear(
			int organizationId,String Yearflag) {
		HashMap<String, String> list = new HashMap<String,String>();
		OrganizationDAOImpl organizationdao = null;
		try {
			organizationdao = new OrganizationDAOImpl();
			Organization organization = organizationdao
					.getOrganizationById(organizationId);
			String lam=organization.getOrganizationLeaveAccountingMethod();
			Calendar now = Calendar.getInstance();
			//if current year is 2015 then
			String Cyear=Integer.toString((now.get(Calendar.YEAR)));
			//2014
			String Pyear=Integer.toString((now.get(Calendar.YEAR)-1));
			//2016
			String Fyear=Integer.toString((now.get(Calendar.YEAR)+1));
			//2013
			String PPyear=Integer.toString((now.get(Calendar.YEAR)-2));
			//2017
			String FFyear=Integer.toString((now.get(Calendar.YEAR)+2));
			String month=Integer.toString((now.get(Calendar.MONTH) +1));
			if(lam.equals("CY"))
			{
				String fyear="01-01-";
				String tyear="31-12-";
				if (Yearflag.equals("P")) {
					
					String fromdate = new StringBuilder(fyear).append(Pyear).toString();	
					String todate = new StringBuilder(tyear).append(Pyear).toString();
					list.put("fromdate", fromdate);
					list.put("todate", todate);
				}else if (Yearflag.equals("F"))
				{
					
					String fromdate = new StringBuilder(fyear).append(Fyear).toString();	
					String todate = new StringBuilder(tyear).append(Fyear).toString();
					list.put("fromdate", fromdate);
					list.put("todate", todate);
				}else{
					String fromdate = new StringBuilder(fyear).append(Cyear).toString();	
					String todate = new StringBuilder(tyear).append(Cyear).toString();
					list.put("fromdate", fromdate);
					list.put("todate", todate);
				}
				
			}else if (lam.equals("FY")) 
			{
				String fyear="01-04-";
				String tyear="31-03-";
				if(month.matches("1|2|3"))
				{
					if(Yearflag.equals("C"))
					{
						String fromdate = new StringBuilder(fyear).append(Pyear).toString();	
						String todate = new StringBuilder(tyear).append(Cyear).toString();
						list.put("fromdate", fromdate);
						list.put("todate", todate);
					}else if (Yearflag.equals("P")) {
						
						String fromdate = new StringBuilder(fyear).append(PPyear).toString();	
						String todate = new StringBuilder(tyear).append(Pyear).toString();
						list.put("fromdate", fromdate);
						list.put("todate", todate);
					}else if (Yearflag.equals("F"))
					{
						
						String fromdate = new StringBuilder(fyear).append(Cyear).toString();	
						String todate = new StringBuilder(tyear).append(Fyear).toString();
						list.put("fromdate", fromdate);
						list.put("todate", todate);
					}
					}
					else {
						if(Yearflag.equals("C"))
						{
							String fromdate = new StringBuilder(fyear).append(Cyear).toString();	
							String todate = new StringBuilder(tyear).append(Fyear).toString();
							list.put("fromdate", fromdate);
							list.put("todate", todate);
						}else if (Yearflag.equals("P")) {
							
							String fromdate = new StringBuilder(fyear).append(Pyear).toString();	
							String todate = new StringBuilder(tyear).append(Cyear).toString();
							list.put("fromdate", fromdate);
							list.put("todate", todate);
						}else if (Yearflag.equals("F"))
						{
							
							String fromdate = new StringBuilder(fyear).append(Fyear).toString();	
							String todate = new StringBuilder(tyear).append(FFyear).toString();
							list.put("fromdate", fromdate);
							list.put("todate", todate);
						}
						
					}
				}
			return  list;
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return  list;
	}


}
