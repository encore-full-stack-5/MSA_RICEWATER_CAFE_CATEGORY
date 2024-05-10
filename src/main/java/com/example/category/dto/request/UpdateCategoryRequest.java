package com.example.category.dto.request;

import com.example.category.global.domain.entity.Category;

public record UpdateCategoryRequest(
        String name,
        Boolean isDelete
) {
    public Category from(Category category) {
        category.setName(name);
        category.setIsDelete(isDelete);
        return category;
    }
}