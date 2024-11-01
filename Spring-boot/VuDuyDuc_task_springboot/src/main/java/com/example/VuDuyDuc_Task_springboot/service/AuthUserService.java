package com.example.VuDuyDuc_Task_springboot.service;

import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.entity.Companies;
import com.example.VuDuyDuc_Task_springboot.entity.Gender;
import com.example.VuDuyDuc_Task_springboot.entity.User;
import com.example.VuDuyDuc_Task_springboot.repository.UserRepository;
import com.example.VuDuyDuc_Task_springboot.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private GenderService genderService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserDTO signUp(UserDTO registrationRequets) {
        UserDTO resp = new UserDTO();
        try {
            User user = new User();
            Companies companies = companyService.findById(registrationRequets.getCompanyId());
            Gender gender = genderService.findById(registrationRequets.getGenderId());
            user.setUsername(registrationRequets.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequets.getPassword()));
            user.setEmail(registrationRequets.getEmail());
            user.setNumberphone(registrationRequets.getNumberphone());
            user.setRole("USER");
            user.setCompany(companies);
            user.setGender(gender);
            User savedUser = userRepository.save(user);
            if (savedUser != null) {
                resp.setUser(savedUser);
                resp.setMessage("Đăng ký tài khoảng thành công");
                resp.setStatusCode(200);
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

}
