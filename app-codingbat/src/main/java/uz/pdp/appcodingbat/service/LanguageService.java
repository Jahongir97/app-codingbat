package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entitiy.Language;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.LanguageDto;
import uz.pdp.appcodingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    /**
     * In this method we are returning all Languages
     *
     * @return Languages
     */

    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }

    /**
     * In this method we are returning a Language with given id
     *
     * @param id Integer
     * @return Language
     */

    public Language getLanguageById(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }

    /**
     * In this method we are creating new Language
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation Language
     */

    public ApiResponse addLanguage(LanguageDto languageDto) {
        boolean exists = languageRepository.existsByName(languageDto.getName());
        if (exists) {
            return new ApiResponse("This Language already exist", false);
        }
        Language language = new Language();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("Language successfully added", true);
    }

    /**
     * Language editing method
     *
     * @param id Integer
     * @param languageDto Json
     * @return ApiResponse
     * We are getting id and LanguageDto Json Object.
     */

    public ApiResponse editLanguage(Integer id, LanguageDto languageDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            Language editingLanguage = optionalLanguage.get();
            editingLanguage.setName(languageDto.getName());
            languageRepository.save(editingLanguage);
            return new ApiResponse("Successfully edited", true);
        }
        return new ApiResponse("Address not found", false);
    }

    /**
     * Deleting address by Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    public ApiResponse deleteLanguage(Integer id) {
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("Language deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);

        }
    }
}
