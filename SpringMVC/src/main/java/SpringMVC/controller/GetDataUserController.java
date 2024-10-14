package SpringMVC.controller;

import SpringMVC.dto.UserDTO;
import SpringMVC.entity.User;
import SpringMVC.service.GetDataUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GetDataUserController {

    @Autowired
    private GetDataUserService getDataUserService ;

    @GetMapping("/users")
    public String listUsersWithPaginate(Model model ,
                                        @RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "5")int size) {
        Page<User> userPage = getDataUserService.getUserWithPaginate(page, size);
        List<User> users = userPage.getContent();
        model.addAttribute("users" , users);
        model.addAttribute("totalPages" , userPage.getTotalPages());
        model.addAttribute("currentPage" , page);
        model.addAttribute("size", size);
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<UserDTO> userDTOList = getDataUserService.getAllUsers();
        model.addAttribute("users", userDTOList);
        return "home";
    }

    @GetMapping("/home/{id}")
    public String getUserById(@PathVariable("id") Long id , Model model) {
        User user = getDataUserService.getUserById(id);
        model.addAttribute("user" , user);
        return "user-detail";
    }

    @GetMapping("/edit/{id}")
    public String FormEditUser(@PathVariable("id") Long id, Model model) {
        User user = getDataUserService.getUserById(id);
        model.addAttribute("user" , user);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String UpdateUser(@PathVariable("id") Long id , @ModelAttribute User user) {
        getDataUserService.updateUser(id,user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String DeleteUser(@PathVariable("id") Long id){
        getDataUserService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/search")
    public String SearchUsers(@RequestParam("username") String username , Model model){
        List<User> users = getDataUserService.searchUsersByUsername(username);
        model.addAttribute("users" , users);
        return "home";
    }
}
