package com.example.VuDuyDuc_Task_springboot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private Integer numberphone;
    private Long companyId;
    private Long genderId;

    public UserDTO(Long id, String email, String username, Integer numberphone, Long companyId , Long genderId) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.numberphone = numberphone;
        this.companyId = companyId;
        this.genderId = genderId;
    }
}
