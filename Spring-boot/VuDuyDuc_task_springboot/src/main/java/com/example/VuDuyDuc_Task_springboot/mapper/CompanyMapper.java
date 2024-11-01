package com.example.VuDuyDuc_Task_springboot.mapper;

import com.example.VuDuyDuc_Task_springboot.dto.CompanyDTO;
import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.entity.Companies;
import com.example.VuDuyDuc_Task_springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    @Mapping(source = "id" , target = "id")
    @Mapping(source = "companyname" , target = "companyname")
    CompanyDTO toCompanyDTO(Companies company);

    UserDTO toUserDTO(User user);

    List<CompanyDTO> toCompanyDTOList(List<Companies> companies);

    List<UserDTO> toUserDTOList(List<User> users);
}
