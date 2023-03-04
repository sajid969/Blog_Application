package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto, 
			@PathVariable Integer userId, 
			@PathVariable Integer categoryId){
		PostDto createdPost = this.postService.createPost(postdto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto, @PathVariable Integer postId){
		PostDto createdPost = this.postService.updatePost(postdto, postId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
		PostDto createdPost = this.postService.getPost(postId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.OK);
	}
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully", true), HttpStatus.OK);
	}
	@GetMapping("/postsbyuser/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> createdPost = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(createdPost, HttpStatus.OK);
	}
	@GetMapping("/postsbycategory/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> createdPost = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(createdPost, HttpStatus.OK);
	}
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam (value = "pageNumber", defaultValue =  "0", required = false) Integer pageNumber,
			@RequestParam (value = "pageSize", defaultValue =  "5", required = false) Integer pageSize,
			@RequestParam (value = "sortBy", defaultValue =  "postId", required = false) String sortBy,
			@RequestParam (value = "sortDir", defaultValue =  "dsc", required = false) String sortDir
			){
		PostResponse createdPost = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(createdPost, HttpStatus.OK);
	}
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable("keyword") String keyword){
		List<PostDto> createdPost = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(createdPost, HttpStatus.OK);
	}
	

}
