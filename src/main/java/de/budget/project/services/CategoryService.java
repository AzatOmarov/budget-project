package de.budget.project.services;

import de.budget.project.model.category.Category;
import de.budget.project.model.categoryType.CategoryType;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Category getCategoryById(Long id);

    List<Category> getAllByCategoryType(CategoryType categoryType);
}