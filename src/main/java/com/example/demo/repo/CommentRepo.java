package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Comment;

@Service
public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
	@Query("select c.posts from Comment c where c.posts = :postId")
	List<Comment> findByPostid(Integer postId);

}
