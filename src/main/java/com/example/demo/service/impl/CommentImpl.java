package com.example.demo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.CommonException;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.CommentDTO;
import com.example.demo.repo.CommentRepo;
import com.example.demo.repo.PostRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.CommentService;
import com.example.demo.utils.ReferenceMethods;

@Service
public class CommentImpl implements CommentService {

	@Autowired
	CommentRepo comRep;
	
	@Autowired
	PostRepo postRep;
	
	@Autowired
	UserRepo userRep;
	
	@Autowired 
	ModelMapper mapper;
	
	@Override
	public CommentDTO createComment(CommentDTO com, Integer postID,Integer UserId) {
		Post p = this.postRep.findById(postID).orElseThrow(()->new PostNotFoundException());
		User u = this.userRep.findById(UserId).orElseThrow(()->new ResourceNotFoundException());
		com.setUser(ReferenceMethods.toDTO(u));
		Comment map = this.mapper.map(com, Comment.class);
		List<Comment> c = this.comRep.findAll();
		c.add(map);
		p.setCommment(c);
		Comment co = new Comment();
		co.setPosts(p);
		co.setUser(u);
		co.setId(com.getId());
		co.setComment(com.getComment());
		CommentDTO a = this.mapper.map(this.comRep.save(co),CommentDTO.class);
		this.postRep.save(p);
		return a;
	}
	
	public void deleteComment(Integer comId,Integer UserId) {
		User u = this.userRep.findById(UserId).orElseThrow();
		Comment c = this.comRep.findById(comId).orElseThrow();
		if(c.getUser().getId()==u.getId()) {
			this.comRep.delete(this.comRep.findById(comId).orElseThrow(()->new CommonException("No Comment found with this Id",304)));
		}
		else {
			throw new CommonException("You are Not allowed to perform this action", 303);
		}
	}
	
	

}
