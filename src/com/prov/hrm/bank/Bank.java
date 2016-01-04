package com.prov.hrm.bank;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Bank {
	
	private Integer bankId;
	@JsonIgnore
	private int organizationId;
	private String bankName;
	@JsonIgnore
	private Integer insertBy;
	@JsonIgnore
	private String insertDate;
	@JsonIgnore
	private Integer updateBy;
	@JsonIgnore
	private String updateDate;
	@JsonIgnore
	private boolean deleteFlag;

		
	public Bank() {
		super();
	}

	public Bank(Integer bankId) {
		super();
		this.bankId = bankId;
	}

	public Integer getBankId() {
		return this.bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	
	public Integer getInsertBy() {
		return this.insertBy;
	}

	public void setInsertBy(Integer insertBy) {
		this.insertBy = insertBy;
	}

	public String getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public Integer getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	@Override
	public String toString() {
		return "{bankId=" + bankId + ", organizationId=" + organizationId
				+ ", bankName=" + bankName + ", insertBy=" + insertBy
				+ ", insertDate=" + insertDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", deleteFlag=" + deleteFlag
				+"}";
	}

}
