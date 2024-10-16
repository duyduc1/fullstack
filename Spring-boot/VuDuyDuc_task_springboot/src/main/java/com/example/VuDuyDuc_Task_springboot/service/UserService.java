package com.example.VuDuyDuc_Task_springboot.service;



import com.example.VuDuyDuc_Task_springboot.entity.Companies;
import com.example.VuDuyDuc_Task_springboot.entity.Gender;
import com.example.VuDuyDuc_Task_springboot.entity.User;
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
    private com.example.SpringMVC.service.GenderService genderService;

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

