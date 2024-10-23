package com.example.VuDuyDuc_Task_springboot.controller;


import com.example.VuDuyDuc_Task_springboot.entity.FileModel;
import com.example.VuDuyDuc_Task_springboot.repository.FileRepository;
import com.example.VuDuyDuc_Task_springboot.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {
    @Autowired
    private FileSystemStorageService storageService;

    @Autowired
    private FileRepository fileRepository;

    @GetMapping
    public ResponseEntity<List<FileModel>> listUploadedFiles() {
        List<FileModel> files = fileRepository.findAll();
        return ResponseEntity.ok(files);
    }

    @PostMapping
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Upload file thành công: " + file.getOriginalFilename());
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        FileModel fileModel = storageService.getFileById(id);

        if (fileModel != null) {
            ByteArrayResource resource = new ByteArrayResource(fileModel.getFilePath().getBytes());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileModel.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileModel.getFileName() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
