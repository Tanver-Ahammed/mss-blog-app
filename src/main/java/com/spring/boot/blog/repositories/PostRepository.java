package com.spring.boot.blog.repositories;

import com.spring.boot.blog.entities.Category;
import com.spring.boot.blog.entities.Post;
import com.spring.boot.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    @Query("SELECT p FROM Post p WHERE p.title LIKE :key")
    List<Post> findByTitle(@Param("key") String title);

}
