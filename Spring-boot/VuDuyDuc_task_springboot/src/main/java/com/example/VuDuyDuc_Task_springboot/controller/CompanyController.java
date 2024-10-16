package com.example.SpringMVC.controller;

import com.example.SpringMVC.entity.Companies;
import com.example.SpringMVC.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<String> saveOrUpdate(@RequestBody Companies companies) {
        companyService.saveOrUpdate(companies);
        return ResponseEntity.status(201).body("Company saved successfully");
    }

    @GetMapping
    public ResponseEntity<List<Companies>> getAllCompanies() {
        List<Companies> companies = companyService.getAll();
        return ResponseEntity.ok(companies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable("id") Long id, @RequestBody Companies companies) {
        companyService.updateCompany(id, companies);
        return ResponseEntity.ok("Company updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok("Company deleted successfully");
    }
}
