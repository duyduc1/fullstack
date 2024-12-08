package com.example.VuDuyDuc_Task_springboot.mapper;

import com.example.VuDuyDuc_Task_springboot.dto.ImageDTO;
import com.example.VuDuyDuc_Task_springboot.entity.ImageEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-08T12:44:15+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241112-1021, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class ImageMapperImpl implements ImageMapper {

    @Override
    public ImageEntity toEntity(ImageDTO imageDTO) {
        if ( imageDTO == null ) {
            return null;
        }

        ImageEntity imageEntity = new ImageEntity();

        imageEntity.setId( imageDTO.getId() );
        imageEntity.setPublicId( imageDTO.getPublicId() );
        imageEntity.setUrl( imageDTO.getUrl() );

        return imageEntity;
    }

    @Override
    public ImageDTO toDto(ImageEntity imageEntity) {
        if ( imageEntity == null ) {
            return null;
        }

        ImageDTO imageDTO = new ImageDTO();

        imageDTO.setId( imageEntity.getId() );
        imageDTO.setPublicId( imageEntity.getPublicId() );
        imageDTO.setUrl( imageEntity.getUrl() );

        return imageDTO;
    }
}
