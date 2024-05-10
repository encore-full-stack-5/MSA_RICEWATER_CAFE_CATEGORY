package com.example.category.service;

import com.example.category.dto.request.CategoryRequest;
import com.example.category.dto.request.UpdateCategoryRequest;
import com.example.category.dto.response.CategoryResponse;
import com.example.category.global.domain.entity.Category;
import com.example.category.global.domain.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    // 카페별 카테고리 조회
    @Override
    public List<CategoryResponse> getCategoryByCafeId(Long cafeId, Pageable pageable) {
        List<Category> all = categoryRepository.findByIsDeleteFalseAndCafeId(cafeId, pageable);
        return all.stream().map(CategoryResponse::from).toList();
    }

    // 카테고리 단일 조회
    @Override
    public CategoryResponse getCategoryById(Long id) {
        Optional<Category> byId = categoryRepository.findByIsDeleteFalseAndId(id);
        Category category = byId.orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
        return CategoryResponse.from(category);
    }

    // 카테고리 추가
    @Transactional
    @Override
    public void createCategory(
            CategoryRequest categoryRequest
    ) {
        Optional<Category> byUserId = categoryRepository.findByCafeId(categoryRequest.cafeId());
        if(byUserId.isPresent()) throw new IllegalArgumentException();
        categoryRepository.save(categoryRequest.toEntity());
    }

    // 카테고리 수정
    @Transactional
    @Override
    public void updateCategory(Long id, UpdateCategoryRequest request) {
        Optional<Category> byId = categoryRepository.findById(id);
        Category category = byId.orElseThrow(IllegalArgumentException::new);

        category.setName(request.name());
        category.setIsDelete(request.isDelete());
    }

    // 카테고리 삭제
    @Transactional
    @Override
    public void deletedCategory(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);
        Category category = byId.orElseThrow(IllegalArgumentException::new);

        category.setIsDelete(true);
    }
}