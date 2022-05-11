package com.spring.boot.blog.services;

import com.spring.boot.blog.payloads.CommentDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO, Integer postId);

    void deleteComment(Integer commentId);

}
