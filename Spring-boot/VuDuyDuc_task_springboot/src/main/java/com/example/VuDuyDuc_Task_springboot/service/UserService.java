package com.example.VuDuyDuc_Task_springboot.service;

import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.entity.Companies;
import com.example.VuDuyDuc_Task_springboot.entity.Gender;
import com.example.VuDuyDuc_Task_springboot.entity.User;
import com.example.VuDuyDuc_Task_springboot.mapper.UserMapper;
import com.example.VuDuyDuc_Task_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private GenderService genderService;

    @Autowired
    private UserMapper userMapper;

    public User register(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        Companies companies = companyService.findById(userDTO.getCompanyId());
        Gender gender = genderService.findById(userDTO.getGenderId());

        user.setCompany(companies);
        user.setGender(gender);

        return userRepository.save(user);
    }
}

