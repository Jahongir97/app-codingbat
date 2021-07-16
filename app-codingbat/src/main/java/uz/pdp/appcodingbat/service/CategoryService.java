package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entitiy.Category;
import uz.pdp.appcodingbat.entitiy.Language;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.CategoryDto;
import uz.pdp.appcodingbat.repository.CategoryRepository;
import uz.pdp.appcodingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;

    /**
     * In this method we are returning all Categories
     *
     * @return Categories
     */

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    /**
     * In this method we are returning a Category with given id
     *
     * @param id Integer
     * @return Category
     */

    public Category getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    /**
     * In this method we are creating new Category
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation Category
     */

    public ApiResponse addCategory(CategoryDto categoryDto) {
        boolean exists = categoryRepository.existsByNameAndLanguageId(categoryDto.getName(), categoryDto.getLanguageId());
        if (exists) {
            return new ApiResponse("This Category already exist", false);
        }
        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        optionalLanguage.ifPresent(category::setLanguage);
        categoryRepository.save(category);
        return new ApiResponse("Category successfully added", true);
    }

    /**
     * Category editing method
     *
     * @param id          Integer
     * @param categoryDto Json
     * @return ApiResponse
     * We are getting id and CategoryDto Json Object.
     */

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category editingCategory = optionalCategory.get();
            editingCategory.setName(categoryDto.getName());
            editingCategory.setDescription(categoryDto.getDescription());
            Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
            optionalLanguage.ifPresent(editingCategory::setLanguage);
            categoryRepository.save(editingCategory);
            return new ApiResponse("Successfully edited", true);
        }
        return new ApiResponse("Category not found", false);
    }

    /**
     * Deleting Category by Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    public ApiResponse deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);

        }
    }
}
