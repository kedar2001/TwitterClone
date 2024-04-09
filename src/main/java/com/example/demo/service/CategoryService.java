package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.payloads.CategoryDTO;

@Service
public interface CategoryService {

	//create
		CategoryDTO createCat(CategoryDTO cat);
	//get
		List<CategoryDTO> getAllCat();
		CategoryDTO getCat(Integer catId);
	//update
		CategoryDTO updateCat(CategoryDTO cat,Integer id);
	//delete
		void deleteCat(Integer id);
}
