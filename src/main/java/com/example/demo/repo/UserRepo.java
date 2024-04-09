package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
		public List<User> findByEmail(String Email);
}
