package com.example.VuDuyDuc_Task_springboot.controller;

import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping("signup")
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authUserService.signUp(userDTO));
    }
}
