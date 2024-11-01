package com.example.VuDuyDuc_Task_springboot.dto;

import com.example.VuDuyDuc_Task_springboot.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private Long id;
    private String email;
    private String username;
    private String password;
    private Integer numberphone;
    private String role;
    private Long companyId;
    private Long genderId;
    private User user;
}
