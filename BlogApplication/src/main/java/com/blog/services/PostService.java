package com.blog.services;

import java.util.List;

import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postdto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postdto,Integer postId);
	
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDto getPost(Integer postId);
	
	void deletePost(Integer postId);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> searchPosts(String keyword);

	//List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);
	
	

}
