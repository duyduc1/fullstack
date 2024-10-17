package com.example.VuDuyDuc_Task_springboot.controller;

import com.example.VuDuyDuc_Task_springboot.entity.Gender;
import com.example.VuDuyDuc_Task_springboot.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gender")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @GetMapping
    public ResponseEntity<List<Gender>> getAllGenders() {
        List<Gender> genders = genderService.getAllGenders();
        return new ResponseEntity<>(genders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Gender> saveGender(@RequestBody Gender gender) {
        Gender savedGender = genderService.saveGender(gender);
        return new ResponseEntity<>(savedGender, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gender> getGenderById(@PathVariable("id") Long id) {
        Gender gender = genderService.getGenderById(id);
        if (gender != null) {
            return new ResponseEntity<>(gender, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGender(@PathVariable("id") Long id, @RequestBody Gender gender) {
        boolean updated = genderService.updateGender(id, gender);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGender(@PathVariable("id") Long id) {
        boolean isDeleted = genderService.deleteGender(id);
        if (isDeleted) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Gender not found", HttpStatus.NOT_FOUND);
        }
    }
}