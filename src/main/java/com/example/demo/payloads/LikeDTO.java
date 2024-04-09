package com.example.demo.payloads;

import org.springframework.stereotype.Service;

@Service
public class LikeDTO {
	
	int likeId;
	boolean isLiked;
	
	private UserDTO user;
	
	
	

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public LikeDTO(int likeId, boolean isLiked, UserDTO user) {
		super();
		this.likeId = likeId;
		this.isLiked = isLiked;
		this.user = user;
		
	}
	
	public LikeDTO() {
		
	}
}
