package com.example.SpringMVC.repository;

import com.example.SpringMVC.entity.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileModel,Long> {
}

