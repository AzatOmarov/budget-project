package de.budget.project.services.impl;

import de.budget.project.model.category.Category;
import de.budget.project.model.categoryType.CategoryType;
import de.budget.project.repository.CategoryRepository;
import de.budget.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public List<Category> getAllByCategoryType(CategoryType categoryType) {
        return categoryRepository.getAllByCategoryType(categoryType);
    }
}