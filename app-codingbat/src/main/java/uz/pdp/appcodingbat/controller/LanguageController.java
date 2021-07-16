package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entitiy.Language;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.LanguageDto;
import uz.pdp.appcodingbat.service.LanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {
    @Autowired
    LanguageService languageService;

    /**
     * In this method we are returning all Languages
     *
     * @return Languages
     */

    @GetMapping
    public ResponseEntity<List<Language>> getLanguages() {
        List<Language> languages = languageService.getLanguages();
        return ResponseEntity.ok(languages);
    }

    /**
     * In this method we are returning a Language with given id
     *
     * @param id Integer
     * @return Language
     */

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguage(@PathVariable Integer id) {

        Language language = languageService.getLanguageById(id);
        return ResponseEntity.ok(language);
    }

    /**
     * In this method we are creating new Language
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation
     */

    @PostMapping
    public ResponseEntity<ApiResponse> addLanguage(@Valid @RequestBody LanguageDto languageDto
    ) {
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Address editing method
     *
     * @param id Integer
     * @param languageDto Json
     * @return ApiResponse
     * We are getting id and LanguageDto Json Object.
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@PathVariable Integer id, @Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.editLanguage(id, languageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Deleting Language by Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Integer id) {
        ApiResponse apiResponse = languageService.deleteLanguage(id);

        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
