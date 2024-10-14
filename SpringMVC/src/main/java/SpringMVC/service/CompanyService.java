package SpringMVC.service;

import SpringMVC.entity.Companies;
import SpringMVC.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public void saveOrUpdate(Companies companies){
        companyRepository.save(companies);
    }

    public List<Companies> getAll(){
        return companyRepository.findAll();
    }

    public Companies getCompanyById(Long id) {return companyRepository.findById(id).orElse(null);}

    public Companies findById(Long id) {
        Optional<Companies> companyOptional = companyRepository.findById(id);
        return companyOptional.orElse(null);
    }

    public void updateCompany(Long id , Companies updateCompany){
        Companies companies = companyRepository.findById(id).orElse(null);
        if (companies != null){
            companies.setCompanyname(updateCompany.getCompanyname());
            companyRepository.save(companies);
        }
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}

