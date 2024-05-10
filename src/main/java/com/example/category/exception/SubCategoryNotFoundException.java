package com.example.category.exception;

public class SubCategoryNotFoundException extends IllegalArgumentException {
    public SubCategoryNotFoundException(Long id) {
        super(id + " not found");
    }
}