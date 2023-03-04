package com.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.payload.PostDto;
import com.blog.services.FileService;
import com.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class FileServiceController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private PostService postService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> UploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		PostDto postdto = this.postService.getPost(postId);
		String uploadImage = this.fileService.UploadImage(path, image);
		postdto.setImage(uploadImage);
		PostDto updatedPost = this.postService.updatePost(postdto, postId);
		
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);	
	}
	
	@GetMapping(value="/profiles/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
		InputStream resourses = this.fileService.GetResourses(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resourses, response.getOutputStream());
	}

}
