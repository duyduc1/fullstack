package com.example.SpringMVC.mapper;

import com.example.SpringMVC.dto.UserDTO;
import com.example.SpringMVC.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
}
