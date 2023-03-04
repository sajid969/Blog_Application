package com.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.ExceptionHandling.ResourseNotFoundException;
import com.blog.entity.User;
import com.blog.payload.UserDto;
import com.blog.repository.UserRepository;

@Service
public class UserServicesImpl implements UserServices {
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.DtoTouser(userDto);
		User usersaved = userRepository.save(user);
		return this.userToDto(usersaved);
	}
	@Override
	public UserDto updateUsers(UserDto userDto, Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourseNotFoundException("user", "Id", userId));
		user.setUserId(userDto.getUserId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updateduser = this.userRepository.save(user);
		UserDto updateddtouser = this.userToDto(updateduser);
		return updateddtouser;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourseNotFoundException("user", "Id", userId));
		UserDto getuser = this.userToDto(user);
		
		return getuser;
	}

	

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepository.findAll();
		List<UserDto> userdto = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());		
		return userdto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourseNotFoundException("user", "Id", userId));
		this.userRepository.delete(user);

	}
	
	public User DtoTouser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
		/*
		 * User user = new User(); user.setUserId(userDto.getUserId());
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setPassword(userDto.getPassword()); user.setAbout(userDto.getAbout());
		 */
		
		return user;
		
	}
	public UserDto userToDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		/*
		 * UserDto userDto = new UserDto(); userDto.setUserId(user.getUserId());
		 * userDto.setName(user.getName()); userDto.setEmail(user.getEmail());
		 * userDto.setPassword(user.getPassword()); userDto.setAbout(user.getAbout());
		 */
		
		
		
		return userDto;
		
	}

}
