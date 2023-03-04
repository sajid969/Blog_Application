package com.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payload.UserDto;

@Service
public interface UserServices {
	
	UserDto createUser(UserDto userDto);
	
	UserDto getUserById(Integer userId);
	
	UserDto updateUsers(UserDto userDto, Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);

}
