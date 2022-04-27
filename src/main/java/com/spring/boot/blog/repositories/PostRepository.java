package com.spring.boot.blog.repositories;

import com.spring.boot.blog.entities.Category;
import com.spring.boot.blog.entities.Post;
import com.spring.boot.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

}
