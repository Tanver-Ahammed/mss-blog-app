package com.spring.boot.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private int id;

    private Date date;

    @NotBlank
    @Size(min = 5, max = 100, message = "comment content must be min of 5 to 100 character")
    private String content;

    // private PostDTO postDTO;

}
