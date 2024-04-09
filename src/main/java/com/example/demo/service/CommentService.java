package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.payloads.CommentDTO;

@Service
public interface CommentService {
	CommentDTO createComment(CommentDTO com, Integer postID, Integer UserId); //userId refers to the user which comments on that Post(PostId)
}
