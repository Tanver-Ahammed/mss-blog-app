package com.spring.boot.blog.controller;

import com.spring.boot.blog.payloads.ApiResponse;
import com.spring.boot.blog.payloads.CategoryDTO;
import com.spring.boot.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // create category
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(this.categoryService
                .createCategory(categoryDTO), HttpStatus.CREATED);
    }

    // update category
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                      @PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryDTO, categoryId));
    }

    // delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true),
                HttpStatus.OK);
    }

    // get a single category
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getSingleCategory(@PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity.ok(this.categoryService.getSingleCategory(categoryId));
    }

    // get all category
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }


}
