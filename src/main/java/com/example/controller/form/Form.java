package com.example.controller.form;

import javax.validation.constraints.NotBlank;


public class Form {
	
	@NotBlank
	private String login_id;
	
	@NotBlank
	private String password;
	
	private String search;
	
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
