package com.example.demo.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {

	@Id
	private int postId;
	
	@Column(length = 1000)
	private String title;
	private String content;
	private String imageName;
	private LocalDateTime addedDate;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post")
	private List<Likes> likes;
	
	private Integer Likess;
		

	@OneToMany(mappedBy = "posts")
	private List<Comment> commment;
	
	
	
	
	

	public List<Comment> getCommment() {
		return commment;
	}

	public void setCommment(List<Comment> commment) {
		this.commment = commment;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public LocalDateTime getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(LocalDateTime addedDate) {
		this.addedDate = addedDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Likes> getLike() {
		return likes;
	}

	public void setLike(List<Likes> likes) {
		this.likes = likes;
	}

	public Integer getLikess() {
		return Likess;
	}

	public void setLikess(Integer likes) {
		Likess = likes;
	}
}
