package de.budget.project.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String e) {
        super(e);
    }
}
