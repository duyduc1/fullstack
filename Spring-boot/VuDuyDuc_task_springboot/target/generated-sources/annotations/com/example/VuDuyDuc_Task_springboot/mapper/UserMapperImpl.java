package com.example.VuDuyDuc_Task_springboot.mapper;

import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-04T14:52:24+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setEmail( userDTO.getEmail() );
        user.setUsername( userDTO.getUsername() );
        user.setNumberphone( userDTO.getNumberphone() );
        user.setPassword( userDTO.getPassword() );
        user.setRole( userDTO.getRole() );
        user.setToken( userDTO.getToken() );

        return user;
    }

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setNumberphone( user.getNumberphone() );
        userDTO.setRole( user.getRole() );
        userDTO.setToken( user.getToken() );

        return userDTO;
    }
}
