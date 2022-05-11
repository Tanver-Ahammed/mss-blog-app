package com.spring.boot.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "post_title", nullable = false)
    private String title;

    @Column(name = "post_content", length = 10000)
    private String content;

    private String image;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id_fk")
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

}
