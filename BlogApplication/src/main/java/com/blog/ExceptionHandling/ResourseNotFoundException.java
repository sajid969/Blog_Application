package com.blog.ExceptionHandling;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class ResourseNotFoundException extends RuntimeException {

	String resourseName;
	String fieldName;
	Integer fieldValue;
	public ResourseNotFoundException(String resourseName, String fieldName, Integer fieldValue) {
		super(String.format("%s not found with %s : %s ",resourseName,fieldName, fieldValue));
		this.resourseName = resourseName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

}
