package com.example.SpringMVC.service;


import com.example.SpringMVC.entity.Companies;
import com.example.SpringMVC.entity.Gender;
import com.example.SpringMVC.entity.User;
import com.example.SpringMVC.repository.UserRepository;
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

    public User register(User user) {
        Companies company = companyService.findById(user.getCompany().getId());
        user.setCompany(company);
        user.setCompanyname(company.getCompanyname());
        Gender gender = genderService.findById(user.getGender().getId());
        user.setGender(gender);
        user.setGioitinh(gender.getGioitinh());
        return userRepository.save(user);
    }
}

