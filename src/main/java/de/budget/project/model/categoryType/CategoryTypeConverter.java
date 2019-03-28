package de.budget.project.model.categoryType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CategoryTypeConverter implements AttributeConverter<CategoryType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CategoryType attribute) {
        return attribute == null ? null : attribute.getId();
    }

    @Override
    public CategoryType convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : CategoryType.findCategoryTypeId(dbData);
    }
}