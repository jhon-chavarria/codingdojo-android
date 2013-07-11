package com.example.codingdojo_android.entities;

/*
 * Comment Class 
 *
 * @author Jonathan Chavarria <jhon.chavarria@gmail.com>
 * @version 1.0.0
 */

public class Comment {
	
	/** Attributes **/
	private String id;
	private	 String name;
	private	 String email;
	private	 String message;
	private	 String registered;
	
	
	/** Methods **/    
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return registered;
	}
	public void setDate(String date) {
		this.registered = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
