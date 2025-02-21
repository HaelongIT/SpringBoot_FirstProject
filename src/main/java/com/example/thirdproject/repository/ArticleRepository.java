package com.example.thirdproject.repository;

import com.example.thirdproject.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {
    // TODO : 특정 게시글의 id를 사용해서, 특정 게시글 조회하기
    ArticleEntity findArticleEntityById(Long id);
}
