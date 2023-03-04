package com.blog.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.blog.ExceptionHandling.ResourseNotFoundException;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.repository.Categoryrepo;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private Categoryrepo categoryrepo;

	@Override
	public PostDto createPost(PostDto postdto, Integer userId, Integer categoryId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourseNotFoundException("user", "Id", userId));
		Category cat = this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("category","categoryid",categoryId));
		Post post = this.modelMapper.map(postdto, Post.class);
		post.setImage("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(cat);
		Post saved = this.postRepository.save(post);
		PostDto savedpost = this.modelMapper.map(saved, PostDto.class);
		return savedpost;
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postId) {
		Post postfind = this.postRepository.findById(postId).orElseThrow(()-> new ResourseNotFoundException("postId", "post", postId));
		postfind.setAddedDate(new Date());
		postfind.setContent(postdto.getContent());
		postfind.setImage(postdto.getImage());
		postfind.setTittle(postdto.getTittle());
		Post updatedpost = this.postRepository.save(postfind);
		PostDto updatedpostdto = this.modelMapper.map(updatedpost, PostDto.class);
		return updatedpostdto;
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort = null;
		if (sortDir.equalsIgnoreCase("dsc")) {
			sort = Sort.by(sortBy).descending();
		}else
		{
			sort = Sort.by(sortBy).ascending();
		}
		PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
		//PageRequest p = PageRequest.of(pageNumber, pageSize);
		//List<Post> posts = this.postRepository.findAll();
		Page<Post> pageout = this.postRepository.findAll(p);
		List<Post> posts = pageout.getContent();
		List<PostDto> collected = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(collected);
		postResponse.setPageNumber(pageout.getNumber());
		postResponse.setPageSize(pageout.getSize());
		postResponse.setTotalElements(pageout.getNumberOfElements());
		postResponse.setTotalPages(pageout.getTotalPages());
		postResponse.setLastPage(pageout.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPost(Integer postId) {
		Post get = this.postRepository.findById(postId).orElseThrow(()-> new ResourseNotFoundException("postid", "post", postId));
		return this.modelMapper.map(get, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post gettodel = this.postRepository.findById(postId).orElseThrow(()-> new ResourseNotFoundException("postid", "post", postId));
		this.postRepository.delete(gettodel);
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourseNotFoundException("user", "Id", userId));
		List<Post> findByUser = this.postRepository.findByUser(user);
		List<PostDto> postbyuser = findByUser.stream().map((find)->this.modelMapper.map(find, PostDto.class)).collect(Collectors.toList());
		return postbyuser;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat = this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("category","categoryid",categoryId));
		List<Post> category = this.postRepository.findByCategory(cat);
		List<PostDto> collect = category.stream().map((caty)->this.modelMapper.map(caty, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> searchingContent = this.postRepository.searchingContent("%"+keyword+"%");
		List<PostDto> collect = searchingContent.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	

}
