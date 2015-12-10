package com.prov.hrm.login;

import java.util.List;



public interface LoginDAO 
{
	public Login Authentication(Login login_name);
	public List<Login> getAllLogin();

	public int addLogin(Login login);

	/*public int updateLogin(Login login);

	public int deleteLogin(int login_id);*/

	public Login getLoginById(int login_id);
	
	public int forgetPassword(String email);


}
