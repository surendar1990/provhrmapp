package com.prov.hrm.utility;

public class LeaveCalculation {

	private String leavetype;
	private int leavetypeId;
	private Float availabledays;
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

	public Float getAvailabledays() {
		return availabledays;
	}

	public void setAvailabledays(Float availabledays) {
		if (availabledays == null) {
			this.availabledays = new Float(0.0);
		} else {
			this.availabledays = availabledays;
		}
	}

	public int getEligibledays() {
		return eligibledays;
	}

	public void setEligibledays(int eligibledays) {
		this.eligibledays = eligibledays;
	}

	@Override
	public String toString() {
		return "LeaveCalculation [leavetype=" + leavetype + ", leavetypeId="
				+ leavetypeId + ", availabledays=" + availabledays
				+ ", eligibledays=" + eligibledays + "]";
	}

}
