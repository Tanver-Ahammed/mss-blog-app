package com.spring.boot.blog.controller;

import com.spring.boot.blog.payloads.ApiResponse;
import com.spring.boot.blog.payloads.PostDTO;
import com.spring.boot.blog.payloads.PostResponse;
import com.spring.boot.blog.services.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    // create post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO,
                                              @PathVariable("userId") Integer userId,
                                              @PathVariable("categoryId") Integer categoryId) {
        PostDTO createPost = this.postService.createPost(postDTO, userId, categoryId);
        return new ResponseEntity<>(createPost, HttpStatus.CREATED);
    }

    // update post
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO,
                                              @PathVariable("postId") Integer postId) {
        PostDTO updatePost = this.postService.updatePost(postDTO, postId);
        return new ResponseEntity<>(updatePost, HttpStatus.OK);
    }

    // delete post
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable("postId") Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully.", true),
                HttpStatus.OK);
    }

    // get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "ASC", required = false) String sortDirection
    ) {
        PostResponse posts = this.postService.getAllPost(pageNo, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // get post single by postId
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getSinglePost(@PathVariable("postId") Integer postId) {
        return new ResponseEntity<>(this.postService.getSinglePost(postId), HttpStatus.OK);
    }

    // get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable("userId") Integer userId) {
        List<PostDTO> postDTOS = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    // get post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable("categoryId") Integer categoryId) {
        List<PostDTO> postDTOS = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

}
