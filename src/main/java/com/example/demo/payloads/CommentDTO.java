package com.example.demo.payloads;

public class CommentDTO {
	
	
	private int id;
	private String comment;
	
	private UserDTO user;
	
	
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
//	public CommentDTO(int id, String comment) {
//		super();
//		this.id = id;
//		this.comment = comment;
//	}
	public CommentDTO(int id, String comment, UserDTO user) {
		super();
		this.id = id;
		this.comment = comment;
		this.user = user;
	}
	public CommentDTO() {
		
	}
	
	
}
