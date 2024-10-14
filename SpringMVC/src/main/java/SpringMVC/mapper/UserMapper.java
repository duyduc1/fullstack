package SpringMVC.mapper;

import SpringMVC.dto.UserDTO;
import SpringMVC.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user){
        if (user == null){
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setNumberphone(user.getNumberphone());
        userDTO.setCompanyname(user.getCompanyname());
        userDTO.setGioitinh(user.getGioitinh());
        return userDTO;
    }
}
