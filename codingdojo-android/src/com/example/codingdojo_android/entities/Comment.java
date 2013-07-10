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
	private	 String email;
	private	 String message;
	private	 String date;
	
	
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
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
