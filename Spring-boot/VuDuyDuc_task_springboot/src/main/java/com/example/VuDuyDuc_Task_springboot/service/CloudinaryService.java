package com.example.VuDuyDuc_Task_springboot.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.VuDuyDuc_Task_springboot.dto.ImageDTO;
import com.example.VuDuyDuc_Task_springboot.entity.ImageEntity;
import com.example.VuDuyDuc_Task_springboot.mapper.ImageMapper;
import com.example.VuDuyDuc_Task_springboot.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;
    private final ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    public List<ImageDTO> getAllImages() {
        List<ImageEntity> imageEntities = imageRepository.findAll();
        return imageEntities.stream()
                .map(imageMapper::toDto)
                .collect(Collectors.toList());
    }

    public ImageDTO getImageById(Long id) {
        ImageEntity imageEntity = imageRepository.findById(id).orElse(null);
        return imageMapper.toDto(imageEntity);
    }

    public Map<String, Object> upload(MultipartFile file) {
        try {
            Map<String, Object> data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());

            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setUrl((String) data.get("url"));
            imageEntity.setPublicId((String) data.get("public_id"));

            imageRepository.save(imageEntity);

            return data;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file: " + e.getMessage(), e);
        }
    }

    public void updateImage(Long id, MultipartFile newFile)  throws IOException {
        ImageEntity imageEntity = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
        cloudinary.uploader().destroy(imageEntity.getPublicId(), ObjectUtils.emptyMap());

        Map<String,Object> newData = this.cloudinary.uploader().upload(newFile.getBytes(), Map.of());

        imageEntity.setUrl((String) newData.get("url"));
        imageEntity.setPublicId((String) newData.get("public_id"));
        imageRepository.save(imageEntity);
    }

    public void deleteImage(Long id)  throws IOException {
        ImageEntity imageEntity = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
        cloudinary.uploader().destroy(imageEntity.getPublicId(), ObjectUtils.emptyMap());
        imageRepository.delete(imageEntity);
    }

}
