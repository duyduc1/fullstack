package com.example.VuDuyDuc_Task_springboot.repository;


import com.example.VuDuyDuc_Task_springboot.entity.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileModel,Long> {
}

