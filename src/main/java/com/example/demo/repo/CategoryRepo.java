package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;

@Service
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
