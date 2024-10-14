package SpringMVC.service;

import SpringMVC.entity.Gender;
import SpringMVC.repository.GenderReposirtory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderService {

    @Autowired
    private GenderReposirtory genderReposirtory;

    public void saveGender(Gender gender){
        genderReposirtory.save(gender);
    }

    public List<Gender> getAllGenders(){
        return genderReposirtory.findAll();
    }

    public Gender getGenderById(Long id) {return genderReposirtory.findById(id).orElse(null);}

    public Gender findById(Long id){
        Optional<Gender> genderOptionnal = genderReposirtory.findById(id);
        return genderOptionnal.orElse(null);
    }

    public void updateGender(Long id , Gender updateGender){
        Gender genders = genderReposirtory.findById(id).orElse(null);
        if (genders != null){
            genders.setGioitinh(updateGender.getGioitinh());
            genderReposirtory.save(genders);
        }
    }

    public void deleteGender(Long id){
        genderReposirtory.deleteById(id);
    }
}

