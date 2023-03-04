package com.blog.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String UploadImage(String path, MultipartFile file) throws IOException {
		
		String ofn = file.getOriginalFilename();
		
		String randomname = UUID.randomUUID().toString();
		String filename1 = randomname.concat(ofn.substring(ofn.lastIndexOf(".")));
		
		String filefullpath = path+File.separator+filename1 ;
		
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filefullpath));
		
		
		return filename1;
	}

	@Override
	public InputStream GetResourses(String path, String filename) throws IOException {
		String fullpath = path+File.separator+filename;
		InputStream fileInputStream = new FileInputStream(fullpath);
		
		
		return fileInputStream;
	}
	
	

}
