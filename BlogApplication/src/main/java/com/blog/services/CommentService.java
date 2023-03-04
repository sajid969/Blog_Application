package com.blog.services;

import org.springframework.stereotype.Service;

import com.blog.payload.CommentDto;

@Service
public interface CommentService {
	
	CommentDto createcomment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);

}
