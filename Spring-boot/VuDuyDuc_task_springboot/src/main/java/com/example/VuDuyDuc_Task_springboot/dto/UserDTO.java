package com.example.VuDuyDuc_Task_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private Integer numberphone;
    private Long companyId;
    private Long genderId;

    public UserDTO() {

    }

    public UserDTO(Long id, String email, String username, Integer numberphone, Long companyId) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.numberphone = numberphone;
    }
}
