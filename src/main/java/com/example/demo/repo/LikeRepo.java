package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Likes;

@Service
public interface LikeRepo extends JpaRepository<Likes, Integer>{
	
	@Query("select l.post from Likes l where l.post = :postId")
	List<Likes> findByPostid(Integer postId);

	@Query("DELETE FROM Likes WHERE post = :postId AND user = :userId")
	public void deleteLike(Integer userId,Integer postId);
}
