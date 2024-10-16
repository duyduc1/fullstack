package com.example.SpringMVC.controller;


import com.example.SpringMVC.dto.UserDTO;
import com.example.SpringMVC.entity.User;
import com.example.SpringMVC.service.GetDataUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class GetDataUserController {

    @Autowired
    private GetDataUserService getDataUserService ;

    @GetMapping
    public ResponseEntity<Page<User>> listUsersWithPaginate(Model model ,
                                                            @RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "5")int size) {
        Page<User> userPage = getDataUserService.getUserWithPaginate(page, size);
        List<User> users = userPage.getContent();
        model.addAttribute("users" , users);
        model.addAttribute("totalPages" , userPage.getTotalPages());
        model.addAttribute("currentPage" , page);
        model.addAttribute("size", size);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = getDataUserService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        boolean updated = getDataUserService.updateUser(id, user);
        if (updated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable("id") Long id){
        boolean isDeleted = getDataUserService.deleteUser(id);
        if (isDeleted) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Gender not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public String SearchUsers(@RequestParam("username") String username , Model model){
        List<User> users = getDataUserService.searchUsersByUsername(username);
        model.addAttribute("users" , users);
        return "home";
    }
}
