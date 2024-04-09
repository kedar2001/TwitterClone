package com.example.demo.payloads;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {
	
	private int postId;
	
	private String title;
	private String content;
	private String imageName;
	private LocalDateTime addedDate;
	
	private CategoryDTO category;
	
	
	
	private UserDTO user;
	
	
	private List<LikeDTO> likes;
	
	
	
	private Integer Likess;
	
	private List<CommentDTO> comment;
	
	
	public List<LikeDTO> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeDTO> likes) {
		this.likes = likes;
	}

	public List<CommentDTO> getComment() {
		return comment;
	}
	
	public Integer getLikess() {
		return Likess;
	}

	public void setLikess(Integer likes) {
		Likess = likes;
	}

	public void setComment(List<CommentDTO> comment) {
		this.comment = comment;
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

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	
	

	public PostDTO(int postId, String title, String content, String imageName, LocalDateTime addedDate, CategoryDTO category,
			List<CommentDTO> comment, UserDTO user,List<LikeDTO> likes,Integer Likess) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.addedDate = addedDate;
		this.category = category;
		this.comment = comment;
		this.user = user;
		this.likes = likes;
		this.Likess = Likess;
	}
	
//	public PostDTO(int postId, String title, String content, String imageName, LocalDateTime addedDate, CategoryDTO category,
//			 UserDTO user) {
//		super();
//		this.postId = postId;
//		this.title = title;
//		this.content = content;
//		this.imageName = imageName;
//		this.addedDate = addedDate;
//		this.category = category;
//		this.user = user;
//	}
	

	public PostDTO() {
		
	}

	
	
	


}
