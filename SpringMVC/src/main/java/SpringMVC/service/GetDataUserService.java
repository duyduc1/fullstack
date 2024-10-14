package SpringMVC.service;

import SpringMVC.dto.UserDTO;
import SpringMVC.entity.User;
import SpringMVC.mapper.UserMapper;
import SpringMVC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetDataUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper  userMapper;

    public Page<User> getUserWithPaginate(int pageNumber , int pageSize){
        Pageable pageable = PageRequest.of(pageNumber - 1 , pageSize);
        return userRepository.findAll(pageable);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(Long id , User updateUser){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setEmail(updateUser.getEmail());
            user.setUsername(updateUser.getUsername());
            user.setNumberphone(updateUser.getNumberphone());
            userRepository.save(user);
        }
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<User> searchUsersByUsername(String username){
        return userRepository.findByUsername(username);
    }
}