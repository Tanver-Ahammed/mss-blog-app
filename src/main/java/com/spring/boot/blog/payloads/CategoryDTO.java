package com.spring.boot.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Integer id;

    @NotBlank
    @Size(min = 4, max = 50, message = "category name must be min of 4 to 50 character")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, max = 250, message = "category name must be min of 10 to 250 character")
    private String categoryDescription;

}
