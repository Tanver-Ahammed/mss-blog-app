package com.spring.boot.blog.services;

import com.spring.boot.blog.entities.Post;
import com.spring.boot.blog.payloads.PostDTO;
import com.spring.boot.blog.payloads.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    // create
    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    // update
    PostDTO updatePost(PostDTO postDTO, Integer postId);

    // delete
    void deletePost(Integer postId);

    // get single post
    PostDTO getSinglePost(Integer postId);

    // get all post
    PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy, String sortDirection);

    // get all post by user
    List<PostDTO> getPostByUser(Integer userId);

    // get all post by category
    List<PostDTO> getPostByCategory(Integer categoryId);

    // search posts
    List<Post> searchPost(String keyword);

}
