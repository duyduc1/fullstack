package SpringMVC.controller;

import SpringMVC.entity.Companies;
import SpringMVC.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/company")
    public String addCompany(Model model) {
        model.addAttribute("companies" , new Companies());
        return "company";
    }

    @PostMapping("/company")
    public String saveOrUpdate(@ModelAttribute("companies") Companies companies){
        companyService.saveOrUpdate(companies);
        return "redirect:/companies";
    }


    @GetMapping("/companies")
    public String getAllCompanies(Model model) {
        List<Companies> companies = companyService.getAll();
        model.addAttribute("companies", companies);
        return "companyList";
    }

    @GetMapping("/editcompany/{id}")
    public String FormEditCompany(@PathVariable("id") Long id , Model model){
        Companies companies = companyService.getCompanyById(id);
        model.addAttribute("company" , companies);
        return "editcompany";
    }

    @PostMapping("/updatecompany/{id}")
    public String UpdateCompany(@PathVariable("id") Long id , @ModelAttribute Companies companies ){
        companyService.updateCompany(id, companies);
        return "redirect:/companies";
    }

    @GetMapping("/deletecompany/{id}")
    public String DeleteCompany(@PathVariable("id") Long id){
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }
}

