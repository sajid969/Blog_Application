package com.blog.services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
	
	String UploadImage(String path, MultipartFile file) throws IOException;
	
	InputStream GetResourses(String path, String filename) throws IOException;

}
