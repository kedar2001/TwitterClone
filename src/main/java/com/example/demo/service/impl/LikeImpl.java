package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Likes;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.CommonException;
import com.example.demo.payloads.LikeDTO;
import com.example.demo.payloads.PostDTO;
import com.example.demo.repo.LikeRepo;
import com.example.demo.repo.PostRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.LikeService;
import com.example.demo.utils.ReferenceMethods;

@Service
public class LikeImpl implements LikeService {
	@Autowired
	private LikeRepo likeRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PostRepo postRepo;

	public List<LikeDTO> toLike(Integer userId, Integer PostId) {

		User u = this.userRepo.findById(userId).orElseThrow(() -> new CommonException("User Not found", 305));
		Post p = this.postRepo.findById(PostId).orElseThrow(() -> new CommonException("No post found", 306));

		try {
			List<Likes> lik = p.getLike();
			for (Likes k : lik) {
				if (k.getUser().getId() == userId) {
					throw new CommonException("You have already Liked this Post", 307);
				}
			}

			Likes like = new Likes();
			List<Likes> list = this.likeRepo.findAll();
			like.setLiked(true);
			like.setUser(u);
			like.setPost(p);

			list.add(like);
			p.setLike(list);
			Post pos = this.postRepo.save(p);
			this.likeRepo.save(like);
			List<LikeDTO> likee = new ArrayList<LikeDTO>();
			for (Likes l : pos.getLike()) {
				likee.add(new LikeDTO(l.getLikeId(), l.isLiked(), ReferenceMethods.toDTO(l.getUser())));
			}

//			PostDTO dto = new PostDTO(pos.getPostId(), pos.getTitle(), pos.getContent(), pos.getImageName(),
//					pos.getAddedDate(), ReferenceMethods.toCatDTO(pos.getCategory()),
//					ReferenceMethods.toComDTO(pos.getCommment()), ReferenceMethods.toDTO(pos.getUser()), likee,
//					ReferenceMethods.getLikeCounts(pos));
//			List<PostDTO> dto = new ArrayList<PostDTO>();
//			for(PostDTO s:dto) {
//				dto.add(new PostDTO(pos.getPostId(), pos.getTitle(),
//						pos.getContent(), pos.getImageName(), pos.getAddedDate(),
//						ReferenceMethods.toCatDTO(pos.getCategory()), ReferenceMethods.toComDTO(pos.getCommment()),
//						ReferenceMethods.toDTO(pos.getUser()), ReferenceMethods.toLikeDTO(pos.getLike())));
//			}
			return likee;

		} catch (Exception e) {
			e.printStackTrace();
			throw new CommonException(e.getCause().toString(), 0);
		}
	}

	public PostDTO getLikes(Integer userId, Integer postId) {
		Post pos = this.postRepo.findById(postId)
				.orElseThrow(() -> new CommonException("No Post Found with that Id", 307));
		List<LikeDTO> lik = new ArrayList<LikeDTO>();
		for (Likes l : pos.getLike()) {
			lik.add(new LikeDTO(l.getLikeId(), l.isLiked(), ReferenceMethods.toDTO(l.getUser())));
		}

		PostDTO dto = new PostDTO(pos.getPostId(), pos.getTitle(), pos.getContent(), pos.getImageName(),
				pos.getAddedDate(), ReferenceMethods.toCatDTO(pos.getCategory()),
				ReferenceMethods.toComDTO(pos.getCommment()), ReferenceMethods.toDTO(pos.getUser()), lik,
				ReferenceMethods.getLikeCounts(pos));

		if (dto.getLikes().isEmpty()) {
			throw new CommonException("Likes are not found", 308);
		}
		return dto;
	}

	public void delete(Integer userId, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new CommonException("No Post Found", 308));
		User user = this.userRepo.findById(userId).orElseThrow(() -> new CommonException("No User Found", 309));
		for (Likes l : post.getLike()) {
			if (l.getUser().equals(user)) {
				this.likeRepo.delete(l);
			}
		}
	}
}

/*
 * //boolean isLikedByThisUser = false;
 * 
 * //for (Likes a : l) { // if (a.getUser().getId() == userId) { //
 * isLikedByThisUser = true; // throw new
 * CommonException("You have Already Liked this post", 306); // } else { //
 * isLikedByThisUser = false; // } //} //if (isLikedByThisUser == false) {
 * 
 * 
 * // l.add(like); // p.setLike(l);
 * 
 */
