package de.budget.project.model.category;

import de.budget.project.model.categoryType.CategoryType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryWebDto {
    private String name;
    private String categoryTypeName;

    public CategoryWebDto(String name, CategoryType categoryType) {
        this.name = name;
        this.categoryTypeName = categoryType.getName();
    }

}