package com.spring.boot.blog.controller;

import com.spring.boot.blog.payloads.ApiResponse;
import com.spring.boot.blog.payloads.UserDTO;
import com.spring.boot.blog.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // POST -> create user
    @PostMapping("")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createUserDTO = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
    }

    // PUT -> update user
    @PutMapping("/{userId}")
    private ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,
                                               @PathVariable("userId") Integer userId) {
        UserDTO updateUser = this.userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(updateUser);
    }

    // DELETE -> delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully.", true),
                HttpStatus.OK);
    }

    // GET -> get user
    // get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> deleteSingleUser(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    // get all user
    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

}
