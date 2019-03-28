package de.budget.project.controller;

import de.budget.project.model.category.Category;
import de.budget.project.model.category.CategoryWebDto;
import de.budget.project.model.categoryType.CategoryType;
import de.budget.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryWebDto createCategory(@RequestBody CategoryWebDto categoryWebDto) {
        return convertToDto(categoryService.createCategory(convertToEntity(categoryWebDto)));
    }

    @GetMapping("/categories/{id}")
    public CategoryWebDto getCategoryById(@PathVariable Long id) {
        return convertToDto(categoryService.getCategoryById(id));
    }

    @GetMapping("/categories/type/{type}")
    public List<CategoryWebDto> getAllByCategoryType(@PathVariable CategoryType type) {
        List<Category> categories = categoryService.getAllByCategoryType(type);
        return categories
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CategoryWebDto convertToDto(Category category) {
        return new CategoryWebDto(category.getName(), category.getCategoryType());
    }

    private Category convertToEntity(CategoryWebDto categoryWebDto) {
        Category category = new Category();
        if (categoryWebDto.getName() == null) {
            categoryWebDto.setName("is null");
        }
        category.setName(categoryWebDto.getName());
        category.setCategoryType(CategoryType.findCategoryTypeName(categoryWebDto.getCategoryTypeName()));
        return category;
    }
}