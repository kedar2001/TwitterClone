package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.payloads.PostDTO;

@Service
public interface PostService {
//	create
	PostDTO createPost(PostDTO post,Integer userId,Integer categoryId);
	
//	get
	List<PostDTO> getAllPosts();
	PostDTO getPost(Integer id);
	
//	update
	PostDTO updatePost(PostDTO post,Integer id);
	
//	delete
	void deletePost(Integer Id);
	
	List<PostDTO> findByCategory(Integer Id);
	List<PostDTO> findByUser(Integer id);
}
