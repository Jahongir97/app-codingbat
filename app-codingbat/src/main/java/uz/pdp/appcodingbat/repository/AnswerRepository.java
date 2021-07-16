package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entitiy.Answer;
import uz.pdp.appcodingbat.entitiy.Category;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
