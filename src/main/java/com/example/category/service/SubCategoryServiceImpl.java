package com.example.category.service;

import com.example.category.dto.request.SubCategoryRequest;
import com.example.category.dto.request.UpdateSubCategoryRequest;
import com.example.category.dto.response.SubCategoryResponse;
import com.example.category.global.domain.entity.SubCategory;
import com.example.category.global.domain.repository.SubCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    // 메인 카테고리별 서브 카테고리 조회
    @Override
    public List<SubCategoryResponse> getSubCategoryByCategoryId(Long categoryId, Pageable pageable) {
        List<SubCategory> all = subCategoryRepository.findByIsDeleteFalseAndCategoryId(categoryId, pageable);
        return all.stream().map(SubCategoryResponse::from).toList();
    }

    // 서브 카테고리 단일 조회
    @Override
    public SubCategoryResponse getSubCategoryById(Long id) {
        Optional<SubCategory> byId = subCategoryRepository.findByIsDeleteFalseAndId(id);
        SubCategory subCategory = byId.orElseThrow(() -> new IllegalArgumentException(String.valueOf(id)));
        return SubCategoryResponse.from(subCategory);
    }

    // 서브 카테고리 추가
    @Transactional
    @Override
    public void createSubCategory(
            SubCategoryRequest subCategoryRequest
    ) {
        Optional<SubCategory> byUserId = subCategoryRepository.findByCategoryId(subCategoryRequest.categoryId());
        if(byUserId.isPresent()) throw new IllegalArgumentException();
        subCategoryRepository.save(subCategoryRequest.toEntity());
    }

    // 서브 카테고리 수정
    @Transactional
    @Override
    public void updateSubCategory(Long id, UpdateSubCategoryRequest request) {
        Optional<SubCategory> byId = subCategoryRepository.findById(id);
        SubCategory subCategory = byId.orElseThrow(IllegalArgumentException::new);

        subCategory.setName(request.name());
        subCategory.setIsDelete(request.isDelete());
    }

    // 카테고리 삭제
    @Transactional
    @Override
    public void deletedSubCategory(Long id) {
        Optional<SubCategory> byId = subCategoryRepository.findById(id);
        SubCategory subCategory = byId.orElseThrow(IllegalArgumentException::new);

        subCategory.setIsDelete(true);
    }
}