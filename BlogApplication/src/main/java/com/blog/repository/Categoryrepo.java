package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entity.Category;

public interface Categoryrepo extends JpaRepository<Category, Integer> {

}
