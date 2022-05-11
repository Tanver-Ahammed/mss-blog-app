package com.spring.boot.blog.services.impl;

import com.spring.boot.blog.entities.Comment;
import com.spring.boot.blog.entities.Post;
import com.spring.boot.blog.exceptions.ResourceNotFoundException;
import com.spring.boot.blog.payloads.CommentDTO;
import com.spring.boot.blog.repositories.CommentRepository;
import com.spring.boot.blog.repositories.PostRepository;
import com.spring.boot.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", postId));
        Comment comment = this.modelMapper.map(commentDTO, Comment.class);
        comment.setDate(new Date());
        comment.setPost(post);
        return this.modelMapper.map(this.commentRepository.save(comment), CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));
        this.commentRepository.delete(comment);
    }

}
