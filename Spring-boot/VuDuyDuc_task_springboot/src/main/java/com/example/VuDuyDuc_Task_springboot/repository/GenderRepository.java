package com.example.SpringMVC.repository;

import com.example.SpringMVC.entity.Gender;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface GenderRepository extends JpaRepository<Gender, Long> {

}
