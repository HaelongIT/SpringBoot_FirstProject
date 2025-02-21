package com.example.thirdproject.repository;

import com.example.thirdproject.entity.ArticleEntity;
import com.example.thirdproject.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // TODO : 특정 게시글의 Id를 사용해서, 해당되는 모든 댓글 조회하기
    List<CommentEntity> findCommentEntitiesByArticleId(Long articleId);
}
