package org.zenja.dataanalysis.utils;

public class MyTestInfoObject {
	private int id;
	private String name;
	private String email;
	private String comments;
	
	public MyTestInfoObject() {
	}
	
	public MyTestInfoObject(int id, String name, String email, String comments) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.comments = comments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
