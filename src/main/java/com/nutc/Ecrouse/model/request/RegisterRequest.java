package com.nutc.Ecrouse.model.request;

import com.nutc.Ecrouse.model.User.AppUserRole;

public class RegisterRequest {

	private String userName;
	private String email;
	private String password;
	private AppUserRole userRole;

	public RegisterRequest() {
	}

	
	public RegisterRequest(String userName, String email, String password, AppUserRole userRole) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AppUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(AppUserRole userRole) {
		this.userRole = userRole;
	}

}
