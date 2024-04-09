package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.UserAlreadyRegistered;
import com.example.demo.payloads.UserDTO;
import com.example.demo.service.impl.UserImpl;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserImpl ump;

	@PostMapping("/create")
	public UserDTO createUser(@Valid @RequestBody UserDTO user) {

		System.out.println("in Controller, user id provided by user is : " + user.getId());
		UserDTO user2 = this.ump.createUser(user);

		if (user2.getId() == -3)	// (-3,null,null,null) that means no user in db
		{
			System.out.println("User Already Exist");
			throw new UserAlreadyRegistered();
//		System.out.println("back to controller, the value of ID is -2 and throwing userAlreadyRegistered Error");
//			UserDTO u =  this.ump.getUserDtoById(user2.getId())  ;
//			return u;
		} else {
			System.out.println("the goal is achieved, returning value");
			return user2;
			}
		}
/*
//		 this.ump.getUsers();
// 		 this.ump.get
//		 UserDTO uss = this.createUser(user)
//		
//		
//		UserDTO u = this.ump.getUserDtoById(user.getId());
//		if(u.getEmail()== null) {
//			return this.ump.createUser(user);
//		}
//			throw new UserAlreadyRegistered();
	}
*/
	@GetMapping("/getAllUser")
	public List<UserDTO> getAllUsers() {
		return this.ump.getUsers();
	}

	@GetMapping("/getUser/{id}")
	public UserDTO getUser(@PathVariable int id) {
		UserDTO userDtoById = this.ump.getUserDtoById(id);
		User u = toUser(userDtoById);
		if (u.getId() > 0) {
			return userDtoById;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@PutMapping("/update/{id}")
	public UserDTO updateUser(@PathVariable int id, @Valid @RequestBody UserDTO user) {

		UserDTO updateUser = this.ump.updateUser(user, id);
		if (updateUser.getEmail() != null) {
			return this.ump.updateUser(user, id);
		}else {
			throw new ResourceNotFoundException();
		}
	}

	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id) {
		String s =this.ump.deleteUser(id);
		if(!s.equals("done perfectly")) {
			throw new ResourceNotFoundException();
		}else {
			return "done perfectly";
		}
	}

//	@PostMapping("/createTemp")
//	public List<User> tempp(UserDTO u) {
//		return this.ump.temp((u));
//
//	}

	public User toUser(UserDTO u) {
		User user = new User(u.getId(), u.getName(), u.getEmail(), u.getPassword());
		return user;
	}

	public UserDTO toDTO(User u) {
		UserDTO user = new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getPassword());
		return user;
	}

}
