package com.blog.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.ExceptionHandling.ResourseNotFoundException;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createcomment(CommentDto commentDto, Integer postId) {
		Post postfind = this.postRepository.findById(postId).orElseThrow(()-> new ResourseNotFoundException("postId", "post", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(postfind);
		Comment savedcomment = this.commentRepository.save(comment);
		CommentDto saved = this.modelMapper.map(savedcomment, CommentDto.class);
		return saved;
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment commentfind = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourseNotFoundException("commentId", "comment", commentId));
		this.commentRepository.delete(commentfind);
	}

}
