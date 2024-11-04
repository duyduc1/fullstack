package com.example.VuDuyDuc_Task_springboot.mapper;

import com.example.VuDuyDuc_Task_springboot.dto.GenderDTO;
import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.entity.Gender;
import com.example.VuDuyDuc_Task_springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenderMapper {
    GenderDTO toGenderDTO(Gender gender);

    UserDTO toUserDTO(User user);

    List<GenderDTO> toGenderDTOList(List<Gender> genderList);

    List<UserDTO> toUserDTOList(List<User> userList);
}
