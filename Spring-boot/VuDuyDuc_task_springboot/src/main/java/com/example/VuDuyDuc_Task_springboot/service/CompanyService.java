package com.example.VuDuyDuc_Task_springboot.service;


import com.example.VuDuyDuc_Task_springboot.dto.CompanyDTO;
import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.entity.Companies;
import com.example.VuDuyDuc_Task_springboot.mapper.CompanyMapper;
import com.example.VuDuyDuc_Task_springboot.mapper.UserMapper;
import com.example.VuDuyDuc_Task_springboot.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    public Companies saveOrUpdate(Companies companies){
        return companyRepository.save(companies);
    }

    public List<CompanyDTO> getAllCompaniesWithUsers() {
        List<Companies> companies = companyRepository.findAll();

        return companies.stream()
                .map(company -> {

                    CompanyDTO companyDTO = companyMapper.toCompanyDTO(company);

                    List<UserDTO> userDTOs = company.getUsers().stream()
                            .map(userMapper::toDTO)
                            .collect(Collectors.toList());

                    companyDTO.setUsers(userDTOs);
                    return companyDTO;
                })
                .collect(Collectors.toList());
    }

    public Optional<Companies> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Companies findById(Long id) {
        Optional<Companies> companyOptional = companyRepository.findById(id);
        return companyOptional.orElse(null);
    }

    public Optional<Companies> updateCompany(Long id, Companies updateCompany) {
        Optional<Companies> existingCompanyOpt = companyRepository.findById(id);
        if (existingCompanyOpt.isPresent()) {
            Companies existingCompany = existingCompanyOpt.get();
            existingCompany.setCompanyname(updateCompany.getCompanyname());
            return Optional.of(companyRepository.save(existingCompany));
        }
        return Optional.empty();
    }

    public boolean deleteCompany(Long id) {
        companyRepository.deleteById(id);
        return true;
    }
}

