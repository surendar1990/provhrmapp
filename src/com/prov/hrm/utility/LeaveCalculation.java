package com.prov.hrm.utility;

public class LeaveCalculation {
	
	private String leavetype;
	private int leavetypeId;
	private Object availabledays;
	private int eligibledays;
	
	
	public String getLeavetype() {
		return leavetype;
	}
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}
	public int getLeavetypeId() {
		return leavetypeId;
	}
	public void setLeavetypeId(int leavetypeId) {
		this.leavetypeId = leavetypeId;
	}
	public Object getAvailabledays() {
		return availabledays;
	}
	public void setAvailabledays(Object availabledays) {
		this.availabledays = availabledays;
	}
	public int getEligibledays() {
		return eligibledays;
	}
	public void setEligibledays(int eligibledays) {
		this.eligibledays = eligibledays;
	}
	
	

}
