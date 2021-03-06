package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entitiy.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByName(String name);
}
