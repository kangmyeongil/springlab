package com.springlab.biz.user.domain;

public class UserDO {

	private String id;
	private String password;
	private String name;
	private String role;

	public UserDO() {
		// TODO Auto-generated constructor stub
	}

	public UserDO(String id, String password, String name, String role) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDO [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}

}
