package SpringMVC.repository;

import SpringMVC.entity.Gender;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface GenderReposirtory extends JpaRepository<Gender, Long> {

}