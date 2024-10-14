package SpringMVC.controller;

import SpringMVC.entity.Gender;
import SpringMVC.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GenderController {

    @Autowired
    private GenderService genderService;

    @GetMapping("/addgender")
    public String addGender(Model model) {
        model.addAttribute("genders" , new Gender());
        return "addgender";
    }

    @PostMapping("/addgender")
    public String saveGender(@ModelAttribute("genders")Gender gender){
        genderService.saveGender(gender);
        return "redirect:/genderlist";
    }

    @GetMapping("/editgender/{id}")
    public String FromtEditGender(@PathVariable("id") Long id , Model model){
        Gender gender = genderService.getGenderById(id);
        model.addAttribute("gender" , gender);
        return "editgender";
    }

    @GetMapping("/genderlist")
    public String getAllgender(Model model){
        List<Gender> gendes = genderService.getAllGenders();
        model.addAttribute("genders" , gendes);
        return "genderlist";
    }

    @PostMapping("/updategender/{id}")
    public String UpdateGender(@PathVariable("id") Long id , @ModelAttribute Gender gender){
        genderService.updateGender(id, gender);
        return "redirect:/genderlist";
    }

    @GetMapping("/deletegender/{id}")
    public String DeleteGender(@PathVariable("id") Long id){
        genderService.deleteGender(id);
        return "redirect:/genderlist";
    }
}
