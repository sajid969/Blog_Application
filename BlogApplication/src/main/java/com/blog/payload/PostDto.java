package com.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private Integer postId;
	private String tittle;
	private String content;
	private String image;
	private Date addedDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set <CommentDto> comment = new HashSet<>();

}
