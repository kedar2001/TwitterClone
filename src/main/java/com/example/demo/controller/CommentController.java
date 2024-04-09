package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payloads.CommentDTO;
import com.example.demo.service.impl.CommentImpl;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentImpl imp;
	
	@PostMapping("/create/{postId}/{userId}")	//userId refers to the user who comments on that post(which is identified by PostId)
	public CommentDTO createComment(@RequestBody CommentDTO com,@PathVariable Integer postId,
			@PathVariable Integer userId) {
		return this.imp.createComment(com, postId,userId);
	}
	
	@DeleteMapping("/delete/{comId}/{userId}")
	public void deleteComment(@PathVariable Integer comId,@PathVariable Integer userId) {
		this.imp.deleteComment(comId,userId);
	}
}
