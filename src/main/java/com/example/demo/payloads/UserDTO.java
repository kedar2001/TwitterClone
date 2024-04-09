package com.example.demo.payloads;

import org.springframework.stereotype.Service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Service
public class UserDTO {
	
	@NotNull
	int id;

	@NotEmpty(message = "min 2 to max 20 char required")
	@Size(min = 2, max = 20)
	String name;
	
	@Email(message = "Please Enter valid Email")
	String email;
	
	@NotEmpty(message = "Password shouldn't be empty")
	String password;
	
	public UserDTO(int id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public UserDTO() {
		
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
