package com.example.VuDuyDuc_Task_springboot.service;


import com.example.VuDuyDuc_Task_springboot.dto.GenderDTO;
import com.example.VuDuyDuc_Task_springboot.dto.UserDTO;
import com.example.VuDuyDuc_Task_springboot.entity.Gender;
import com.example.VuDuyDuc_Task_springboot.mapper.GenderMapper;
import com.example.VuDuyDuc_Task_springboot.mapper.UserMapper;
import com.example.VuDuyDuc_Task_springboot.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenderService {

    @Autowired
    private GenderRepository genderReposirtory;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GenderMapper genderMapper;

    public Gender saveGender(Gender gender){
        return genderReposirtory.save(gender);
    }

    public List<GenderDTO> getAllGenderWithUsers() {
        List<Gender> genders = genderReposirtory.findAll();

        return genders.stream()
                .map(gender -> {
                    System.out.println("Gender entity before mapping: " + gender);
                    GenderDTO genderDTO = genderMapper.toGenderDTO(gender);

                    List<UserDTO> userDTOS = gender.getUsers().stream()
                            .map(userMapper::toDTO)
                            .collect(Collectors.toList());

                    genderDTO.setUsers(userDTOS);
                    System.out.println("Mapped GenderDTO: " + genderDTO);
                    return genderDTO;
                })
                .collect(Collectors.toList());
    }


    public Gender getGenderById(Long id) {return genderReposirtory.findById(id).orElse(null);}

    public Gender findById(Long id){
        Optional<Gender> genderOptionnal = genderReposirtory.findById(id);
        return genderOptionnal.orElse(null);
    }

    public boolean updateGender(Long id, Gender updateGender) {
        Optional<Gender> optionalGender = genderReposirtory.findById(id);
        if (optionalGender.isPresent()) {
            Gender gender = optionalGender.get();
            gender.setGenders(updateGender.getGenders());
            genderReposirtory.save(gender);
            return true;
        }
        return false;
    }

    public boolean deleteGender(Long id) {
        if (genderReposirtory.existsById(id)) {
            genderReposirtory.deleteById(id);
            return true;
        }
        return false;
    }
}
