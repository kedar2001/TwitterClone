package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

	@Id
	private int id;
	private String categoryTitle;
	private String categoryDesc;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<Post> posts;
	
	public Category() {
	}
	public Category(int cat_id, String categoryTitle, String categoryDesc) {
		super();
		this.id = cat_id;
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
	}
	public int getId() {
		return id;
	}
	public void setId(int cat_id) {
		this.id = cat_id;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDesc() {
		return categoryDesc;
	}
	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}
	
}
