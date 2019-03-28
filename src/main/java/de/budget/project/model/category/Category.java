package de.budget.project.model.category;

import de.budget.project.model.categoryType.CategoryType;
import de.budget.project.model.categoryType.CategoryTypeConverter;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 50, unique = true)
    private String name;

    @Convert(converter = CategoryTypeConverter.class)
    private CategoryType categoryType;
}