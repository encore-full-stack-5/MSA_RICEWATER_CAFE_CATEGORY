package com.example.category.global.domain.repository;

import com.example.category.global.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 카테고리 조회
    List<Category> findByIsDeleteFalseAndCafeId(Long cafeId, Pageable pageable);

    // 카테고리 단일 조회
    Optional<Category> findByIsDeleteFalseAndId(Long id);

    // 삭제한 카테고리 조회
    List<Category> findByIsDeleteTrueAndCafeId(Long cafeId);

    // 카페 아이디 조회
    Optional<Category> findByCafeId(Long cafeId);
}