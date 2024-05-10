package com.example.category.exception;

public class CategoryNotFoundException extends IllegalArgumentException {
    public CategoryNotFoundException(Long id) {
        super(id + " not found");
    }
}