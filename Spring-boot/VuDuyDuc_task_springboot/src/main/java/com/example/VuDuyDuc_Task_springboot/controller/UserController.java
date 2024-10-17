package com.example.VuDuyDuc_Task_springboot.controller;

import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.entity.User;
import com.example.VuDuyDuc_Task_springboot.service.CompanyService;
import com.example.VuDuyDuc_Task_springboot.service.GenderService;
import com.example.VuDuyDuc_Task_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO) {
        return new ResponseEntity(userService.register(userDTO), HttpStatus.CREATED);
    }
}
