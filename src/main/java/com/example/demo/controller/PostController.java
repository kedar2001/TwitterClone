package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.PostDTO;
import com.example.demo.service.impl.PostImpl;

@RestController
public class PostController {
	@Autowired
	private PostImpl imp;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts/add")
	public PostDTO createPost(@RequestBody PostDTO post,@PathVariable int userId,
			@PathVariable int categoryId) {
		
		return this.imp.createPost(post, userId, categoryId);
	}
	
	@GetMapping("/posts/getAll")
	public List<PostDTO> getAllPosts(){
		return this.imp.getAllPosts();
	}
	
	@GetMapping("/posts/get/{postId}")
	public PostDTO getPostByPostId(@PathVariable("postId") Integer id) {
		return this.imp.getPost(id);
	}
	
	@GetMapping("/byUser/{userId}/posts")
	public List<PostDTO> getPostsAccordingToUserId(@PathVariable("userId") Integer id) {
		return this.imp.findByUser(id);
	}
	
	@GetMapping("/posts/byCategory/{catId}")
	public List<PostDTO> accordingToCategory(@PathVariable("catId") Integer id) {
		return this.imp.findByCategory(id);
	}
	
	@PutMapping("/posts/update/{postId}")
	public PostDTO updatePostDTO(@PathVariable("postId") Integer id,@RequestBody PostDTO p) {
		
		return this.imp.updatePost(p,id);
	}
	
	@DeleteMapping("/posts/delete/{postId}")
	public void deletePost(@PathVariable("postId") Integer id) {
		this.imp.deletePost(id);
	}
	
	@PutMapping("/updateCategory/{catId}/post/{postId}")
	public PostDTO updateCategoryOfPost(@PathVariable Integer catId,@PathVariable Integer postId) {
		return this.imp.updateCategoryOfPost(catId, postId);
	}	
}
