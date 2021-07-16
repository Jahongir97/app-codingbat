package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entitiy.Example;
import uz.pdp.appcodingbat.entitiy.User;

public interface ExampleRepository extends JpaRepository<Example, Integer> {
    boolean existsByTextAndTaskId(String text, Integer task_id);
}
