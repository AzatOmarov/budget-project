package de.budget.project.model.categoryType;

import java.util.Arrays;
import java.util.Optional;

public enum CategoryType {
    DEBIT(1, "DEBIT"),
    CREDIT(2, "CREDIT");

    private Integer id;
    private String name;

    CategoryType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CategoryType findCategoryTypeId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        Optional<CategoryType> categoryType = Arrays
                .stream(CategoryType.values())
                .filter(k -> k.getId().equals(id))
                .findFirst();
        return categoryType.orElseThrow(() -> new IllegalArgumentException("Id cannot be null"));
    }

    public static CategoryType findCategoryTypeName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        Optional<CategoryType> categoryType = Arrays
                .stream(CategoryType.values())
                .filter(k -> k.getName().equals(name))
                .findFirst();
        return categoryType.orElseThrow(() -> new IllegalArgumentException("Name cannot be null"));
    }
}