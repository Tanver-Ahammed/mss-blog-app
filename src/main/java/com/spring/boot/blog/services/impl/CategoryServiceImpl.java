package com.spring.boot.blog.services.impl;

import com.spring.boot.blog.entities.Category;
import com.spring.boot.blog.exceptions.ResourceNotFoundException;
import com.spring.boot.blog.payloads.CategoryDTO;
import com.spring.boot.blog.repositories.CategoryRepository;
import com.spring.boot.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    // create category
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return this.modelMapper.map(this.categoryRepository.save
                (this.modelMapper.map(categoryDTO, Category.class)), CategoryDTO.class);
    }

    // update category
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "Id", categoryId));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        return this.modelMapper.map(this.categoryRepository.save(category), CategoryDTO.class);
    }

    // delete category
    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "Id", categoryId));
        this.categoryRepository.delete(category);
    }

    // get single category
    @Override
    public CategoryDTO getSingleCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "Id", categoryId));
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    // get all categories
    @Override
    public List<CategoryDTO> getAllCategories() {
        return this
                .categoryRepository
                .findAll()
                .stream()
                .map(category -> this.modelMapper.map(category, CategoryDTO.class))
                .toList();
    }

}
