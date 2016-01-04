package com.prov.hrm.idtype;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Idtype {

	private Integer idtypeId;
	@JsonIgnore
	private int organizationId;
	private String idtypeName;
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
	
	public Idtype() {
		super();
	}
	public Idtype(Integer idtypeId) {
		super();
		this.idtypeId = idtypeId;
	}

	public Integer getIdtypeId() {
		return this.idtypeId;
	}

	public void setIdtypeId(Integer idtypeId) {
		this.idtypeId = idtypeId;
	}

	public int getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getIdtypeName() {
		return this.idtypeName;
	}

	public void setIdtypeName(String idtypeName) {
		this.idtypeName = idtypeName;
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

	public boolean isDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}


	@Override
	public String toString() {
		return "{idtypeId=" + idtypeId + ", organizationId=" + organizationId
				+ ", idtypeName=" + idtypeName + ", insertBy=" + insertBy
				+ ", insertDate=" + insertDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", deleteFlag=" + deleteFlag
				+"}";
	}

}
