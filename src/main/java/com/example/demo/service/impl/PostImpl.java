package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Category;
import com.example.demo.entities.Comment;
import com.example.demo.entities.Likes;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.PostAlreadyExist;
import com.example.demo.exceptions.PostNotFoundException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.CategoryDTO;
import com.example.demo.payloads.CommentDTO;
import com.example.demo.payloads.LikeDTO;
import com.example.demo.payloads.PostDTO;
import com.example.demo.payloads.UserDTO;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.CommentRepo;
import com.example.demo.repo.LikeRepo;
import com.example.demo.repo.PostRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.PostService;

@Service
public class PostImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private CommentRepo comRepo;
	
	@Autowired
	private LikeRepo likeRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public PostDTO createPost(PostDTO post,Integer userId,Integer categoryId) {
		Category c = this.catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException());
		User u = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException());
		List<Comment> com = this.comRepo.findByPostid(post.getPostId());
		List<Likes> likes = this.likeRepo.findByPostid(post.getPostId());
		post.setAddedDate(LocalDateTime.now());
		post.setImageName("default.jpg");
		post.setComment(toComDTO(com));
		post.setCategory(toCatDTO(c));
		post.setUser(toDTO(u));
		post.setLikes(toLikeDTO(likes));
		Optional<Post> p = this.postRepo.findById(post.getPostId());
		if(p.isPresent()) {
			throw new PostAlreadyExist();
		}
		else {
			return toPostDTO(this.postRepo.save(toPost(post)));
		}
		
		
//		return toPostDTO();
		//return null;
	}

	@Override
	public List<PostDTO> getAllPosts() {
		List<Post> all = this.postRepo.findAll();
		//List<Likes> l = new ArrayList<Likes>();
		List<PostDTO> dto = new ArrayList<PostDTO>();
		//System.out.println(all.get(0).getLike());
//		List<LikeDTO> l = new ArrayList<LikeDTO>();
		
		
//		for(Post p:all) {
//			for(Likes li:p.getLike()) {
//				l.add(new LikeDTO(li.getLikeId(),li.isLiked(),ReferenceMethods.toDTO(li.getUser())));
//			}
//		}
		
		for(Post p:all) {
			//l.add(p.getLike().get(p.getPostId()));
			
			dto.add(new PostDTO(p.getPostId(),p.getTitle(),p.getContent(),p.getImageName(),p.getAddedDate(),
					toCatDTO(p.getCategory()),toComDTO(p.getCommment()),toDTO(p.getUser())
					,toLikeDTO(p.getLike()),getLikeCounts(p)));
			
			;
		}
		return dto;
	}
	
	
	
	public List<LikeDTO> qualifyUserOnly(Integer PostId){
		
		
		return null;
	}

	@Override
	public PostDTO getPost(Integer id) {
		return toPostDTO(this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException()));
	}

	@Override
	public PostDTO updatePost(PostDTO p, Integer id) {
		Optional<Post> opt = this.postRepo.findById(id);
		
		if(opt.isPresent()) {
			if(p.getImageName()!=null) {
				PostDTO post = new PostDTO(
						id,p.getTitle(),p.getContent(),p.getImageName(),opt.get().getAddedDate(),
						toCatDTO(opt.get().getCategory()),toComDTO(opt.get().getCommment()),
						toDTO(opt.get().getUser()),toLikeDTO(opt.get().getLike()),getLikeCounts(opt.get()));
				return toPostDTO(this.postRepo.save(toPost(post)));
			}
			else {
				PostDTO post = new PostDTO(id,p.getTitle(),p.getContent(),opt.get().getImageName(),opt.get().getAddedDate(),
						toCatDTO(opt.get().getCategory()),toComDTO(opt.get().getCommment()),toDTO(opt.get().getUser()),toLikeDTO(opt.get().getLike()),getLikeCounts(opt.get()));
				return toPostDTO(this.postRepo.save(toPost(post)));
			}
		}
		else {
			throw new PostNotFoundException();
		}
		
	}

	@Override
	public void deletePost(Integer Id) {
		this.postRepo.delete(this.postRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException()));
	}
	@Override
	public List<PostDTO> findByCategory(Integer CategoryId){
		List<Post> p = this.postRepo.findAll();
//		List<PostDTO> map = p.stream().map((pos)->(new ModelMapper().map(pos, PostDTO.class))).collect(Collectors.toList());
		List<PostDTO> dto = new ArrayList<>();
		for(Post s:p) {
			if(s.getCategory().getId()==CategoryId) {
				dto.add(new PostDTO(s.getPostId(), s.getTitle(), s.getContent(), s.getImageName(), s.getAddedDate(), 
						toCatDTO(s.getCategory()), toComDTO(s.getCommment()),toDTO(s.getUser()),toLikeDTO(s.getLike()),getLikeCounts(s)));
			}
		}
		return dto;
	}

	@Override
	public List<PostDTO> findByUser(Integer id) {
		User u = this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException());
		List<Post> p = this.postRepo.findByUser(u);
		List<PostDTO> dto = new ArrayList<>();
//		List<PostDTO> dto = new ArrayList<PostDTO>();
//		for(Post l:p) {
//			dto.add(new )
//		}
		
		
		for(Post s:p) {
			if(s.getUser().getId()==id) {
				dto.add(new PostDTO(s.getPostId(), s.getTitle(), s.getContent(), s.getImageName(), s.getAddedDate(), 
						toCatDTO(s.getCategory()), toComDTO(s.getCommment()),toDTO(s.getUser()),toLikeDTO(s.getLike()),getLikeCounts(s)));
			}
		}
		return dto;
	}
	
	public PostDTO updateCategoryOfPost(Integer i, Integer j) {	//(new categoryId,PostId)
		
		Post p = this.postRepo.findById(j).orElseThrow(()->new PostNotFoundException());
		Category c = this.catRepo.findById(i).orElseThrow(()->new CategoryNotFoundException());
		p.setCategory(c);
		this.postRepo.save(p);
		PostDTO dto = this.mapper.map(p, PostDTO.class);
		return dto;
	}
	
	
	
	
	public Post toPost(PostDTO p) {
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
	
	public PostDTO toPostDTO(Post p) {
		PostDTO s = new PostDTO();
		s.setPostId(p.getPostId());
		s.setAddedDate(p.getAddedDate());
		s.setCategory(toCatDTO(p.getCategory()));
		s.setContent(p.getContent());
		s.setImageName(p.getImageName());
		s.setTitle(p.getTitle());
		s.setUser(toDTO(p.getUser()));
		s.setComment(toComDTO(p.getCommment()));
		s.setLikes(toLikeDTO(p.getLike()));
		s.setLikess(p.getLike().size());
		return s;
	}

	
	public CategoryDTO toCatDTO(Category cat) {
		CategoryDTO c = new CategoryDTO();
		c.setId(cat.getId());
		c.setCategoryTitle(cat.getCategoryTitle());
		c.setCategoryDesc(cat.getCategoryDesc());
		return c;
	}

	public Category toCat(CategoryDTO cat) {
		Category c = new Category();
		c.setId(cat.getId());
		c.setCategoryTitle(cat.getCategoryTitle());
		c.setCategoryDesc(cat.getCategoryDesc());
		return c;
	}
	
	public User toUser(UserDTO u) {
		User user = new User(u.getId(), u.getName(), u.getEmail(), u.getPassword());
		return user;
	}

	public UserDTO toDTO(User u) {
		UserDTO user = new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getPassword());
		return user;
	}
	
	public List<CommentDTO> toComDTO(List<Comment> c) {
		List<CommentDTO> a = new ArrayList<>();
		c.stream().map(s->a.add(new CommentDTO(s.getId(), s.getComment(),toDTO(s.getUser())))).collect(Collectors.toList());
		
		return a;
	}
	
	public List<LikeDTO> toLikeDTO(List<Likes> l){
		List<LikeDTO> s = new ArrayList<LikeDTO>();		//int likeId, boolean isLiked, UserDTO user,PostDTO post
		l.stream().map(x -> s.add(new LikeDTO(	x.getLikeId(),x.isLiked(),toDTO(x.getUser())	))).collect(Collectors.toList());
	
		return s;
	}
	
	public Integer getLikeCounts(Post p ) {
		List<Likes> like = p.getLike();
		return like.size();
	}
	

}
