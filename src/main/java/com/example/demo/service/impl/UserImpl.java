package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.UserDTO;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.UserService;

@Service
public class UserImpl implements UserService {

	@Autowired
	private UserRepo user;

	@Override
	public List<UserDTO> getUsers() {
		// TODO Auto-generated method stub
		List<User> u = user.findAll();
		if (!u.isEmpty()) {

			List<UserDTO> uu = new ArrayList<>();
			for (User usr : u) {
				uu.add(new UserDTO(usr.getId(), usr.getName(), usr.getEmail(), usr.getPassword()));
			}
			return uu;
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public UserDTO createUser(UserDTO u) {

		System.out.println("in UserImpl, that value of user Id is : " + u.getId());
		User orElse = this.user.findById(u.getId()).orElse(new User(-1, null, null, null));
		if (orElse.getId() == -1) {
			User usr = this.user.save(new User(u.getId(), u.getName(), u.getEmail(), u.getPassword()));
			return toDTO(usr);
		} else {
			return new UserDTO(-3, null, null, null);
		}

		// int i = searchUser(u.getId());
		// User us = toUser(userDtoById);
//			if(i==-1){ 	//no user found
//				System.out.println("The SearchUser method gives -1");
//				User uu = toUser(u);
//				User user1 = user.save(uu);
//				u = toDTO(user1);
//				return u;
//			}
//			else {
//				System.out.println("The SearchUser method gives 1, and now going to return (-2,null,null,null)");
//				return new UserDTO(-2,null,null,null);
//				//throw new UserAlreadyRegistered();
//			}
//			

//		String a = u.getEmail();
//		List<User> userr = user.findByEmail(a);
//		int size = userr.size();
//		if (size == 0) {
//			
//		} else {
//			
//		}
	}

	@Override
	public UserDTO updateUser(UserDTO u, int i) {
		// TODO Auto-generated method stub
		User user2 = user.findById(i).orElse(new User(-3, null, null, null));

		if (user2.getEmail() == null) {
			return toDTO(user2);
		} else {

			user2.setEmail(u.getEmail());
			user2.setPassword(u.getPassword());
			user2.setName(u.getName());

			user.save(user2);
			return toDTO(user2);
		}
	}

	@Override
	public String deleteUser(int i) {
		User u = this.user.findById(i).orElse(new User(-3,null,null,null));
		if(u.getId()==-3) {
			return "no user with this id";
		}
		else {
			this.user.delete(user.getReferenceById(i));
			return "done perfectly";
		}
		
//		try {
//			User u = user.getReferenceById(i);
//
//			// if(u!=null) {
//			this.user.delete(user.findById(i).orElseThrow(()->new ResourceNotFoundException()));
//			System.out.println("UserFound and Deleted");
//			return new ResponseEntity<>(HttpStatus.ACCEPTED);
//			// return new
//			// ResponseEntity<Object>(this.user.delete(this.user.getById(i)),HttpStatus.OK);
////			this.user.findById(i).orElseThrow(() -> new ResourceNotFoundException());
//		} catch (Exception e) {
//			// else{
//			System.out.println("No User Found");
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////			System.out.println("no user found");
////			throw new ResourceNotFoundException();
//		}
//		user.delete(user.getReferenceById(i));

	}

	@Override
	public UserDTO getUserDtoById(int id) {
		try {
			return toDTO(user.getReferenceById(id));
		} catch (Exception e) {
			return new UserDTO(-1, null, null, null);
		}
	}

	public User toUser(UserDTO u) {
		User user = new User(u.getId(), u.getName(), u.getEmail(), u.getPassword());
		return user;
	}

	public UserDTO toDTO(User u) {
		UserDTO user = new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getPassword());
		return user;
	}

	public int searchUser(int id) {
		try {
			System.out.println("in SearchUser, the id of user is: " + id);
			User a = this.user.findById(id).orElse(null);
			// System.out.println("the value of a is : "+a);
			System.out.println("name is : " + a.getName() + " Email is: " + a.getEmail());
			// if(a > 0) {
			System.out.println("found a user");
			return 1;
//			}
//			else {
//				System.out.println("no user exist with this Id");
//				return -2;
//			}
			// userFound

			// return toDTO(user.getReferenceById(id));
		} catch (Exception e) {
			return -1; // userNotFound
			// return new UserDTO(-1, null, null, null);
		}
	}

	public List<User> temp(UserDTO u) {

		String a = u.getEmail();
		List<User> userr = user.findByEmail(a);
		return userr;

		// int size = userr.size();
//		if (size == 0) {
//			User user1 = user.save(toUser(u));
//			u = toDTO(user1);
//			return u;
//		} else {
//			return new UserDTO(-1, null, null, null);
//		}
	}

}
