package com.example.VuDuyDuc_Task_springboot.repository;

import com.example.VuDuyDuc_Task_springboot.entity.Companies;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Companies, Long> {
}

