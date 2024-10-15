package com.example.SpringMVC.service;

import com.example.SpringMVC.entity.Companies;
import com.example.SpringMVC.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Companies saveOrUpdate(Companies companies){
        return companyRepository.save(companies);
    }

    public List<Companies> getAll() {
        return companyRepository.findAll();
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

