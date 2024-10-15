package com.example.SpringMVC.service;

import com.example.SpringMVC.dto.UserDTO;
import com.example.SpringMVC.entity.User;
import com.example.SpringMVC.mapper.UserMapper;
import com.example.SpringMVC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetDataUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public Page<User> getUserWithPaginate(int pageNumber , int pageSize){
        Pageable pageable = PageRequest.of(pageNumber - 1 , pageSize);
        return userRepository.findAll(pageable);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public boolean updateUser(Long id , User updateUser){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setEmail(updateUser.getEmail());
            user.setUsername(updateUser.getUsername());
            user.setNumberphone(updateUser.getNumberphone());
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> searchUsersByUsername(String username){
        return userRepository.findByUsernameIgnoreCase(username);
    }
}