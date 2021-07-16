package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entitiy.Category;
import uz.pdp.appcodingbat.entitiy.Language;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByNameAndLanguageId(String name, Integer language_id);
}
