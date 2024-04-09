package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.LikeDTO;
import com.example.demo.payloads.PostDTO;
import com.example.demo.service.impl.LikeImpl;

@RestController
@RequestMapping("/like")
public class LikeController {
	
	@Autowired
	LikeImpl imp;
	
	@PostMapping("/setLike/{userId}/{postId}")
	public List<LikeDTO> toLike(@PathVariable Integer userId,@PathVariable Integer postId) {
		List<LikeDTO> b = this.imp.toLike(userId, postId);
		return b;
	}
	
	@GetMapping("/getPostLikes/{UserId}/{postId}")
	public PostDTO getLikes(@PathVariable Integer UserId,@PathVariable Integer postId) {
		return this.imp.getLikes(UserId, postId);
	}
	
	@DeleteMapping("delete/{userId}/{postId}")
	public void deleteLike(@PathVariable Integer userId,@PathVariable Integer postId) {
		this.imp.delete(userId, postId);
	}
}
