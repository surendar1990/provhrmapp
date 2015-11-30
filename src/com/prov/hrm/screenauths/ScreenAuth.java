package com.prov.hrm.screenauths;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prov.hrm.role.Role;
import com.prov.hrm.screen.Screen;

public class ScreenAuth {

	private Integer screenauthId;
	private Screen screen;
	private Role role;
	private boolean addPermission;
	private boolean editPermission;
	private boolean viewPermission;
	private boolean deletePermission;
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

	public ScreenAuth() {
	}

	

	public ScreenAuth(Integer screenauthId) {
		super();
		this.screenauthId = screenauthId;
	}



	public Integer getScreenauthId() {
		return this.screenauthId;
	}

	public void setScreenauthId(Integer screenauthId) {
		this.screenauthId = screenauthId;
	}

	public Screen getTblscreen() {
		return this.screen;
	}

	public void setTblscreen(Screen screen) {
		this.screen = screen;
	}

	public Role getTblrole() {
		return this.role;
	}

	public void setTblrole(Role role) {
		this.role = role;
	}

	public boolean isAddPermission() {
		return this.addPermission;
	}

	public void setAddPermission(boolean addPermission) {
		this.addPermission = addPermission;
	}

	public boolean isEditPermission() {
		return this.editPermission;
	}

	public void setEditPermission(boolean editPermission) {
		this.editPermission = editPermission;
	}

	public boolean isViewPermission() {
		return this.viewPermission;
	}

	public void setViewPermission(boolean viewPermission) {
		this.viewPermission = viewPermission;
	}

	public boolean isDeletePermission() {
		return this.deletePermission;
	}

	public void setDeletePermission(boolean deletePermission) {
		this.deletePermission = deletePermission;
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
		return "{screenauthId=" + screenauthId + ", screen=" + screen
				+ ", role=" + role + ", addPermission=" + addPermission
				+ ", editPermission=" + editPermission + ", viewPermission="
				+ viewPermission + ", deletePermission=" + deletePermission
				+ ", insertBy=" + insertBy + ", insertDate=" + insertDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate
				+ ", deleteFlag=" + deleteFlag + "}";
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
