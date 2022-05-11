package com.spring.boot.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {

    private Integer postId;

    @NotBlank
    @Size(min = 5, max = 100, message = "post title must be min of 5 to 100 character")
    private String title;

    @NotBlank
    @Size(min = 5, max = 100, message = "post content must be min of 5 to 100 character")
    private String content;

    private String image;
    private Date date;
    private UserDTO user;
    private CategoryDTO category;
    private Set<CommentDTO> comments = new HashSet<>();

}
