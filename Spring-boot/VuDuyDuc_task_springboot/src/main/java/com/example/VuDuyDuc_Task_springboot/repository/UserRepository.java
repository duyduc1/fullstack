package com.example.VuDuyDuc_Task_springboot.repository;


import com.example.VuDuyDuc_Task_springboot.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findByUsernameIgnoreCase(String username);
    Page<User> findAll(Pageable pageable);
}
