package com.example.VuDuyDuc_Task_springboot.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenderDTO {
    private Long id;
    private String genders;
    private List<UserDTO> users;
}

