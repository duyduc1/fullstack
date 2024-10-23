package com.example.VuDuyDuc_Task_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private Integer numberphone;
    private Long companyId;
    private Long genderId;
}
