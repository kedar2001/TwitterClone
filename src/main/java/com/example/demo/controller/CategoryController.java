package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.CommonException;
import com.example.demo.payloads.CategoryDTO;
import com.example.demo.service.impl.CategoryImpl;

@RestController
@RequestMapping("/Cat")
public class CategoryController {
	
	@Autowired
	private CategoryImpl imp;
	
	
	@PostMapping("/create")
	public CategoryDTO createCategory(@RequestBody CategoryDTO cat) {
		return this.imp.createCat(cat);
	}
	
	@GetMapping("/getAll")
	public List<CategoryDTO> getAllCategory(){
		return this.imp.getAllCat();
	}
	
	@GetMapping("/get/{id}")
	public CategoryDTO getCategory(@PathVariable Integer id) {
		CategoryDTO c = this.imp.getCat(id);
		if(c.getId()==-1) {
			throw new CommonException("no Category Found with id: "+id,309);
		}else {
			return this.imp.getCat(id);
		}
	}
	
	@PutMapping("/update/{id}")
	public CategoryDTO updateCategoryDTO(@RequestBody CategoryDTO cat,@PathVariable Integer id) {
		return this.imp.updateCat(cat,id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCategory(@PathVariable Integer id) {
		this.imp.deleteCat(id);
		
	}
}
