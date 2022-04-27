package com.spring.boot.blog.services.impl;

import com.spring.boot.blog.entities.Category;
import com.spring.boot.blog.entities.Post;
import com.spring.boot.blog.entities.User;
import com.spring.boot.blog.exceptions.ResourceNotFoundException;
import com.spring.boot.blog.payloads.PostDTO;
import com.spring.boot.blog.payloads.PostResponse;
import com.spring.boot.blog.repositories.CategoryRepository;
import com.spring.boot.blog.repositories.PostRepository;
import com.spring.boot.blog.repositories.UserRepository;
import com.spring.boot.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // create post
    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("user", "id", userId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("category", "Id", categoryId));
        Post post = this.modelMapper.map(postDTO, Post.class);
        post.setImage("Tanver.jpg");
        post.setDate(new Date());
        post.setCategory(category);
        post.setUser(user);
        return this.modelMapper.map(this.postRepository.save(post), PostDTO.class);
    }

    // update post
    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("post", "Id", postId));
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        return this.modelMapper.map(this.postRepository.save(post), PostDTO.class);
    }

    // delete post
    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("post", "Id", postId));
        this.postRepository.delete(post);
    }

    // get single post by post id
    @Override
    public PostDTO getSinglePost(Integer postId) {
        return this.modelMapper.map(this.postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("post", "Id", postId)), PostDTO.class);
    }

    // get all post
    @Override
    public PostResponse getAllPost(Integer pageNo, Integer pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> postPage = this.postRepository.findAll(pageable);
        List<Post> posts = postPage.getContent();

        List<PostDTO> postDTOS = posts
                .stream()
                .map(post -> this.modelMapper.map(post, PostDTO.class))
                .toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContents(postDTOS);
        postResponse.setPageNo(postPage.getNumber());
        postResponse.setPageSize(postPage.getSize());
        postResponse.setTotalElements(postPage.getTotalElements());
        postResponse.setTotalPages(postPage.getTotalPages());
        postResponse.setLastPage(postPage.isLast());

        return postResponse;

//        return this
//                .postRepository
//                .findAll(pageable)
//                .getContent()
//                .stream()
//                .map(post -> this.modelMapper.map(post, PostDTO.class))
//                .collect(Collectors.toList());
    }

    // get post by user
    @Override
    public List<PostDTO> getPostByUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("user", "id", userId));
        return this.postRepository.findByUser(user).stream().map(post ->
                this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    // get post by category
    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("category", "Id", categoryId));
        return this.postRepository.findByCategory(category).stream().map(post ->
                this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    // get post by search keyword
    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}
