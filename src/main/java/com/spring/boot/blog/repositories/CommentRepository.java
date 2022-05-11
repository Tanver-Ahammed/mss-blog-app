package com.spring.boot.blog.repositories;

import com.spring.boot.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
