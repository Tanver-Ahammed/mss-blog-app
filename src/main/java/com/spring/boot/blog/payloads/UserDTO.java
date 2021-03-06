package com.spring.boot.blog.payloads;

import com.spring.boot.blog.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotEmpty
    @Size(min = 4, message = "user name must be min of 4 character")
    private String name;

    @Email(message = "your email address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "password must be 3 to 10 character")
    private String password;

    @NotEmpty(message = "about can't be null or empty")
    private String about;

    private RoleDTO roleDTO;

}
