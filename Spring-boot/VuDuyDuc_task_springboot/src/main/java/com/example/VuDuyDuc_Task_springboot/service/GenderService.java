package com.example.SpringMVC.service;


import com.example.VuDuyDuc_Task_springboot.entity.Gender;
import com.example.VuDuyDuc_Task_springboot.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderService {

    @Autowired
    private GenderRepository genderReposirtory;

    public Gender saveGender(Gender gender){
        return genderReposirtory.save(gender);
    }

    public List<Gender> getAllGenders(){
        return genderReposirtory.findAll();
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
            gender.setGioitinh(updateGender.getGioitinh());
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
