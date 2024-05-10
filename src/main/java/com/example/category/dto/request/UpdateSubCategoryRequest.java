package com.example.category.dto.request;

import com.example.category.global.domain.entity.Category;
import com.example.category.global.domain.entity.SubCategory;

public record UpdateSubCategoryRequest(
        String name,
        Boolean isDelete
) {
    public SubCategory from(SubCategory subCategory) {
        subCategory.setName(name);
        subCategory.setIsDelete(isDelete);
        return subCategory;
    }
}
