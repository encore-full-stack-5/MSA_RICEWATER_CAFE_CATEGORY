package com.example.category.dto.request;

import com.example.category.global.domain.entity.Category;

public record CategoryRequest(
        String name,
        Long cafeId
) {
    public Category toEntity() {
        return Category.builder()
                .id(null)
                .name(name)
                .cafeId(cafeId)
                .build();
    }
}