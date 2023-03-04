package com.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4, message = "min 4 chars")
	private String categoryName;
	
	@NotEmpty
	@Size(min = 4, message = "min 4 chars")
	private String categoryDescription;
}
