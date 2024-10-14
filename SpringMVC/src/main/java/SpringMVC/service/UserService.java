package SpringMVC.service;

import SpringMVC.entity.Companies;
import SpringMVC.entity.Gender;
import SpringMVC.entity.User;
import SpringMVC.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private GenderService genderService;

    public User register(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Companies company = companyService.findById(user.getCompany().getId());
        user.setCompany(company);
        user.setCompanyname(company.getCompanyname());
        Gender gender = genderService.findById(user.getGender().getId());
        user.setGender(gender);
        user.setGioitinh(gender.getGioitinh());
        return userRepository.save(user);
    }

    public boolean login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User u = user.get();
            return passwordEncoder.matches(password, u.getPassword());
        }
        return false;
    }
}
