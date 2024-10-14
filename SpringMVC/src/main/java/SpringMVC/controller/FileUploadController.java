package SpringMVC.controller;

import SpringMVC.entity.FileModel;
import SpringMVC.repository.FileRepository;
import SpringMVC.service.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FileUploadController {
    @Autowired
    private FileSystemStorageService storageService;

    @Autowired
    private FileRepository fileRepository;

    @GetMapping("/upload")
    public String listUploadFiles(Model model){
        List<FileModel> files = fileRepository.findAll();
        model.addAttribute("files", files);
        return "uploadForm";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file , RedirectAttributes redirectAttributes) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "UpLoad File thành công " + file.getOriginalFilename() + "!");
        return "redirect:/upload";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id){
        FileModel fileModel = storageService.getFileById(id);

        ByteArrayResource resource = new ByteArrayResource(fileModel.getFilePath().getBytes());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileModel.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileModel.getFileName() + "\"")
                .body(resource);
    }
}
