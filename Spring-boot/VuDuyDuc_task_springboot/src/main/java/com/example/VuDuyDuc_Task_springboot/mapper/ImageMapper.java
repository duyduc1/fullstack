package com.example.VuDuyDuc_Task_springboot.mapper;

import com.example.VuDuyDuc_Task_springboot.dto.ImageDTO;
import com.example.VuDuyDuc_Task_springboot.entity.ImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageEntity toEntity(ImageDTO imageDTO);
    ImageDTO toDto(ImageEntity imageEntity);
}
