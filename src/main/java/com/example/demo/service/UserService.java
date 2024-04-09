package com.example.demo.service;

import java.util.List;

import com.example.demo.payloads.UserDTO;

public interface UserService {
	List<UserDTO> getUsers();
	UserDTO createUser(UserDTO u);
	//UserDTO updateUser(int i);
	//String deleteUser(int i) ;
	String deleteUser(int i);
	UserDTO getUserDtoById(int id);
	UserDTO updateUser(UserDTO u, int i) ;	
}
