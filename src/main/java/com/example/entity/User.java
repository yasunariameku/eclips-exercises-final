package com.example.entity;

public class User {
	
	
	private Integer id;
	private String login_id;
	private String password;
	private String name;
	private Integer role;
	
	public User() {
		
	}
	
	public User(Integer id,String login_id,String password,String name,Integer role) {
		this.id = id;
		this.login_id = login_id;
		this.password = password;
		this.name = name;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

}
