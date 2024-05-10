package com.example.category.global.domain.repository;

import com.example.category.global.domain.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    // 서브 카테고리 조회
    List<SubCategory> findByIsDeleteFalseAndCategoryId(Long categoryId, Pageable pageable);

    // 서브 카테고리 단일 조회
    Optional<SubCategory> findByIsDeleteFalseAndId(Long id);

    // 삭제한 서브 카테고리 조회
    List<SubCategory> findByIsDeleteTrueAndCategoryId(Long categoryId);

    // 메인 카테고리 조회
    Optional<SubCategory> findByCategoryId(Long categoryId);
}