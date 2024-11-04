package com.example.VuDuyDuc_Task_springboot.mapper;

import com.example.VuDuyDuc_Task_springboot.dto.GenderDTO;
import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.entity.Gender;
import com.example.VuDuyDuc_Task_springboot.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-04T14:57:53+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class GenderMapperImpl implements GenderMapper {

    @Override
    public GenderDTO toGenderDTO(Gender gender) {
        if ( gender == null ) {
            return null;
        }

        GenderDTO genderDTO = new GenderDTO();

        genderDTO.setId( gender.getId() );
        genderDTO.setGenders( gender.getGenders() );
        genderDTO.setUsers( userListToUserDTOList( gender.getUsers() ) );

        return genderDTO;
    }

    @Override
    public UserDTO toUserDTO(User user) {
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

    protected List<UserDTO> userListToUserDTOList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserDTO> list1 = new ArrayList<UserDTO>( list.size() );
        for ( User user : list ) {
            list1.add( toUserDTO( user ) );
        }

        return list1;
    }
}
