package com.example.category.global.domain.dto;

public record SubCategoryDto(
        Long id,
        String name,
        Long categoryId
) {
}