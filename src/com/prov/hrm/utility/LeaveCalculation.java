package com.prov.hrm.utility;

public class LeaveCalculation {

	private String leaveType;
	private int leavetypeId;
	private Float availedDays;
	private Float balancDays;
	private Float accrualDays;
	private Float totalDays;
	private Float pendingDays; 
	private Float maxLimit;
	

	public LeaveCalculation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Float getMaxLimit() {
		this.maxLimit=this.totalDays-(this.availedDays+this.pendingDays);
		return maxLimit;
	}

	public void setMaxLimit(Float maxLimit) {
		this.maxLimit = maxLimit;
	}

	public Float getPendingDays() {
		return pendingDays;
	}

	public void setPendingDays(Float pendingDays) {
		if(pendingDays== null)
		{
			this.pendingDays=(float) 0.0;
		}
		else
		{
		this.pendingDays = pendingDays;
		}
	}


	public Float getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Float totalDays) {
		this.totalDays = totalDays;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public int getLeavetypeId() {
		return leavetypeId;
	}

	public void setLeavetypeId(int leavetypeId) {
		this.leavetypeId = leavetypeId;
	}

	public Float getAvailedDays() {
		return availedDays;
	}

	public void setAvailedDays(Float availedDays) {
		if(availedDays== null)
		{
			this.availedDays=(float) 0.0;
		}
		else
		{
		this.availedDays = availedDays;
		}
	}

	public Float getBalancDays() {
		this.balancDays =this.accrualDays-this.availedDays;
		return balancDays;
	}

	public void setBalancDays(Float balancDays) {
		this.balancDays = balancDays;
	}

	public Float getAccrualDays() {
		return accrualDays;
	}

	public void setAccrualDays(Float accrualDays) {
		if(accrualDays==null)
		{
			this.accrualDays=(float)0.0;
		}
		else
		{
		this.accrualDays = accrualDays;
		}
	}

	@Override
	public String toString() {
		return "LeaveCalculation [leaveType=" + leaveType + ", leavetypeId="
				+ leavetypeId + ", availedDays=" + availedDays
				+ ", balancDays=" + balancDays + ", accrualDays=" + accrualDays
				+ ", totalDays=" + totalDays + ", pendingDays=" + pendingDays
				+ ", maxLimit=" + maxLimit + "]";
	}

	
}
