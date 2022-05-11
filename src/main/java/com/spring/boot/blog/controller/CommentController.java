package com.spring.boot.blog.controller;

import com.spring.boot.blog.entities.Comment;
import com.spring.boot.blog.payloads.ApiResponse;
import com.spring.boot.blog.payloads.CommentDTO;
import com.spring.boot.blog.services.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDTO> createComment(
            @Valid @RequestBody CommentDTO commentDTO,
            @PathVariable("postId") Integer postId) {
        return new ResponseEntity<>(this.commentService
                .createComment(commentDTO, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("comment deleted successfully", true),
                HttpStatus.OK);
    }

}
