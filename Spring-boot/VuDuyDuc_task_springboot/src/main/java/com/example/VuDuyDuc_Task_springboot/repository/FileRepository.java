package com.example.VuDuyDuc_Task_springboot.repository;


import com.example.VuDuyDuc_Task_springboot.entity.FileModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<FileModel,Long> {
}

