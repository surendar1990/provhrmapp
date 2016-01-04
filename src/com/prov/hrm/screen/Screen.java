package com.prov.hrm.screen;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Screen {

	public Screen(Integer screenId) {
		super();
		this.screenId = screenId;
	}

	public Screen() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer screenId;
	private String screenName;
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
	//private Set<ScreenAuth> screenauths = new HashSet<ScreenAuth>(0);

	

	public Integer getScreenId() {
		return this.screenId;
	}

	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}

	public String getScreenName() {
		return this.screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
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

	public Boolean getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

/*	public Set getScreenauths() {
		return this.screenauths;
	}

	public void setScreenauths(Set screenauths) {
		this.screenauths = screenauths;
	}*/

	@Override
	public String toString() {
		return "{screenId=" + screenId + ", screenName=" + screenName
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag + "}";
	}

}
