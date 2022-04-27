package com.spring.boot.blog.services;

import com.spring.boot.blog.payloads.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    // create
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    // update
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    // delete
    void deleteCategory(Integer categoryId);

    // get
    // single category
    CategoryDTO getSingleCategory(Integer categoryId);

    // all category
    List<CategoryDTO> getAllCategories();

}
