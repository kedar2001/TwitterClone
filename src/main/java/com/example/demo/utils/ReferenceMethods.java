package com.example.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entities.Category;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Likes;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.payloads.CategoryDTO;
import com.example.demo.payloads.CommentDTO;
import com.example.demo.payloads.LikeDTO;
import com.example.demo.payloads.PostDTO;
import com.example.demo.payloads.UserDTO;

public class ReferenceMethods {

	public static Post toPost(PostDTO p) {
		Post s = new Post();
		s.setPostId(p.getPostId());
		s.setAddedDate(p.getAddedDate());
		s.setCategory(toCat(p.getCategory()));
		s.setContent(p.getContent());
		s.setImageName(p.getImageName());
		s.setTitle(p.getTitle());
		s.setUser(toUser(p.getUser()));
		return s;
	}

	public static PostDTO toPostDTO(Post p) {
		PostDTO s = new PostDTO();
		s.setPostId(p.getPostId());
		s.setAddedDate(p.getAddedDate());
		s.setCategory(toCatDTO(p.getCategory()));
		s.setContent(p.getContent());
		s.setImageName(p.getImageName());
		s.setTitle(p.getTitle());
		s.setUser(toDTO(p.getUser()));
		return s;
	}

	public static CategoryDTO toCatDTO(Category cat) {
		CategoryDTO c = new CategoryDTO();
		c.setId(cat.getId());
		c.setCategoryTitle(cat.getCategoryTitle());
		c.setCategoryDesc(cat.getCategoryDesc());
		return c;
	}

	public static Category toCat(CategoryDTO cat) {
		Category c = new Category();
		c.setId(cat.getId());
		c.setCategoryTitle(cat.getCategoryTitle());
		c.setCategoryDesc(cat.getCategoryDesc());
		return c;
	}

	public static User toUser(UserDTO u) {
		User user = new User(u.getId(), u.getName(), u.getEmail(), u.getPassword());
		return user;
	}

	public static UserDTO toDTO(User u) {
		UserDTO user = new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getPassword());
		return user;
	}

	public static List<CommentDTO> toComDTO(List<Comment> c) {
		List<CommentDTO> a = new ArrayList<>();
		c.stream().map(s -> a.add(new CommentDTO(s.getId(), s.getComment(), toDTO(s.getUser()))))
				.collect(Collectors.toList());

		return a;
	}

	public static List<LikeDTO> toLikeDTO(List<Likes> l) {
		List<LikeDTO> dto = new ArrayList<>();
		
		l.stream().map(x->dto.add(new LikeDTO(x.getLikeId(),x.isLiked(),toDTO(x.getUser()))))
				.collect(Collectors.toList());
		
		return dto;
	}
	
	public static Integer getLikeCounts(Post p ) {
		List<Likes> like = p.getLike();
		return like.size();
	}
	
}
