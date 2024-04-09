package com.example.demo.payloads;


public class CategoryDTO {
	
	private int id;
	private String categoryTitle;
	private String categoryDesc;
	
	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryDTO(int id, String categoryTitle, String categoryDesc) {
		super();
		this.id = id;
		this.categoryTitle = categoryTitle;
		this.categoryDesc = categoryDesc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
