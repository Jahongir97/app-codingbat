package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entitiy.Task;
import uz.pdp.appcodingbat.entitiy.User;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    boolean existsByNameAndCategoryId(String name, Integer category_id);
}
