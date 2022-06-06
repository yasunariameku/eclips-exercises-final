package com.example.controller.form;

import javax.validation.constraints.NotBlank;

public class Form {
	
	@NotBlank
	private String loginId;
	
	@NotBlank
	private String password;
	
	private String search;
	
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
