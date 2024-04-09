package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Category;
import com.example.demo.exceptions.CategoryAlreadyExist;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.payloads.CategoryDTO;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.service.CategoryService;

@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo rep;

	@Override
	public CategoryDTO createCat(CategoryDTO cat) {
		Optional<Category> opt=this.rep.findById(cat.getId());
		if(opt.isPresent()) {
			throw new CategoryAlreadyExist();
		}
		return toCatDTO(this.rep.save(toCat(cat)));
	}

	@Override
	public List<CategoryDTO> getAllCat() {
		List<Category> l = this.rep.findAll();
		if (!l.isEmpty()) {
			List<CategoryDTO> dto = new ArrayList<>();
			for (Category c : l) {
				dto.add(new CategoryDTO(c.getId(), c.getCategoryTitle(), c.getCategoryDesc()));
			}
			return dto;
		}
		else {
			throw new CategoryNotFoundException();
		}
	}

	@Override
	public CategoryDTO getCat(Integer catId) {
		Category c = this.rep.findById(catId).orElse(new Category(-1,null,null));
		CategoryDTO d = toCatDTO(c);
		return d;
	}

	@Override
	public CategoryDTO updateCat(CategoryDTO cat,Integer id) {
		CategoryDTO dto = new CategoryDTO(id,cat.getCategoryTitle(),cat.getCategoryDesc());
			//no user found with that id
		Optional<Category> opt= this.rep.findById(id);
		if(opt.isPresent()) {
			return toCatDTO(this.rep.save(toCat(dto)));
		}
		else {
			throw new CategoryNotFoundException();
		}
	}

	@Override
	public void deleteCat(Integer id) {
		this.rep.delete(this.rep.findById(id).orElseThrow(()-> new CategoryNotFoundException()));
	}

	public CategoryDTO toCatDTO(Category cat) {
		CategoryDTO c = new CategoryDTO();
		c.setId(cat.getId());
		c.setCategoryTitle(cat.getCategoryTitle());
		c.setCategoryDesc(cat.getCategoryDesc());
		return c;
	}

	public Category toCat(CategoryDTO cat) {
		Category c = new Category();
		c.setId(cat.getId());
		c.setCategoryTitle(cat.getCategoryTitle());
		c.setCategoryDesc(cat.getCategoryDesc());
		return c;
	}

}
