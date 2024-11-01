package com.example.VuDuyDuc_Task_springboot.service;

import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.entity.Companies;
import com.example.VuDuyDuc_Task_springboot.entity.Gender;
import com.example.VuDuyDuc_Task_springboot.entity.User;
import com.example.VuDuyDuc_Task_springboot.repository.UserRepository;
import com.example.VuDuyDuc_Task_springboot.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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


    public UserDTO signIn(UserDTO signinRequest) {
        UserDTO response = new UserDTO();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
            var user = userRepository.findByEmail(signinRequest.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            System.out.println("User is " + user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24H");
            response.setMessage("Đăng Nhập Thành Công");
        } catch (UsernameNotFoundException e) {
            response.setStatusCode(404);
            response.setError("User not found");
        } catch (BadCredentialsException e) {
            response.setStatusCode(401);
            response.setError("Invalid credentials");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }

        return response;
    }


    public UserDTO refreshToken(UserDTO refreshTokenRequest){
        UserDTO response = new UserDTO();
        String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        User users = userRepository.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24H");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}
