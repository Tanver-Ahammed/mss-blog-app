package com.spring.boot.blog.services.impl;

import com.spring.boot.blog.entities.User;
import com.spring.boot.blog.exceptions.ResourceNotFoundException;
import com.spring.boot.blog.payloads.UserDTO;
import com.spring.boot.blog.repositories.UserRepository;
import com.spring.boot.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.UserTransaction;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return this.userToDTO(this.userRepository.save(this.dtoToUser(userDTO)));
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, int userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        return this.userToDTO(this.userRepository.save(user));
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        return this.userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDTO> userDTOS = users.stream().map(this::userToDTO).toList();
        return userDTOS;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));
        this.userRepository.delete(user);
    }

    private User dtoToUser(UserDTO userDTO) {
//        return new User(userDTO.getId(), userDTO.getName(),
//                userDTO.getEmail(), userDTO.getPassword(), userDTO.getAbout());
        return this.modelMapper.map(userDTO, User.class);
    }

    private UserDTO userToDTO(User user) {
//        return new UserDTO(user.getId(), user.getName(),
//                user.getEmail(), user.getPassword(), user.getAbout());
        return this.modelMapper.map(user, UserDTO.class);
    }

}
