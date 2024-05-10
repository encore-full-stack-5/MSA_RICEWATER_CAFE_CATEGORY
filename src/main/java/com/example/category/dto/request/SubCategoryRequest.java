package com.example.category.dto.request;

import com.example.category.global.domain.entity.SubCategory;

public record SubCategoryRequest(
        String name,
        Long categoryId
) {
    public SubCategory toEntity() {
        return SubCategory.builder()
                .id(null)
                .name(name)
                .categoryId(categoryId)
                .build();
    }
}