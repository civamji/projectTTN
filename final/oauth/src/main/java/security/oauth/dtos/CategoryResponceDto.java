package security.oauth.dtos;

import security.oauth.entities.CategoryMetadataField;

import java.util.Set;

public class CategoryResponceDto {
CategoryDto categoryDto;
Set<CategoryDto> subCategories;
Set<CategoryMetadataFieldDto> fieldValues;


    public CategoryResponceDto(CategoryDto categoryDto, Set<CategoryDto> subCategories, Set<CategoryMetadataFieldDto> fieldValues) {
        this.categoryDto = categoryDto;
        this.subCategories = subCategories;
        this.fieldValues = fieldValues;
    }

    public CategoryResponceDto() {
    }

    @Override
    public String toString() {
        return "CategoryResponceDto{" +
                "categoryDto=" + categoryDto +
                ", subCategories=" + subCategories +
                ", fieldValues=" + fieldValues +
                '}';
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public Set<CategoryDto> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(Set<CategoryDto> subCategories) {
        this.subCategories = subCategories;
    }

    public Set<CategoryMetadataFieldDto> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(Set<CategoryMetadataFieldDto> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
