package com.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.ExceptionHandling.ResourseNotFoundException;
import com.blog.entity.Category;
import com.blog.payload.CategoryDto;
import com.blog.repository.Categoryrepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	public Categoryrepo categoryrepo;
	@Autowired
	public ModelMapper modelMapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category ctc = this.modelMapper.map(categoryDto, Category.class);
		Category createdctc = this.categoryrepo.save(ctc);
		CategoryDto cretecateg = this.modelMapper.map(createdctc, CategoryDto.class);
		return cretecateg;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("category","categoryid",categoryId));
		cat.setCategoryId(categoryDto.getCategoryId());
		cat.setCategoryName(categoryDto.getCategoryName());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category saved = this.categoryrepo.save(cat);
		CategoryDto updatedcat = this.modelMapper.map(saved, CategoryDto.class);
		return updatedcat;
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("category","categoryid",categoryId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category del = this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourseNotFoundException("category","categoryid",categoryId));
		this.categoryrepo.delete(del);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> findAll = this.categoryrepo.findAll();
		List<CategoryDto> collect = findAll.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return collect;
	}

}
