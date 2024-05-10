package com.example.category.dto.response;

import com.example.category.global.domain.entity.Category;

public record CategoryResponse(
        Long id,
        String name,
        Long cafeId
) {
    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getCafeId()
        );
    }
}