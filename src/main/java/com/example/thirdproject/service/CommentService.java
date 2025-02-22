package com.example.thirdproject.service;

import com.example.thirdproject.dto.CommentDto;
import com.example.thirdproject.entity.ArticleEntity;
import com.example.thirdproject.entity.CommentEntity;
import com.example.thirdproject.repository.ArticleRepository;
import com.example.thirdproject.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    // TODO : CREATE
    public CommentDto addCommentToArticle(CommentDto commentDto, Long articleId) {
        // TODO : 1. 게시글id를 사용해서 게시글 조회해서, 유효성 검증 및 예외 발생시키기
        ArticleEntity searchArticleEntity = articleRepository.findArticleEntityById(articleId);
        if (searchArticleEntity == null) {
            throw new EntityNotFoundException("게시글이 존재하지 않습니다.");
        }

        // TODO : 2. 서비스 계층에서 댓글 데이터인 DTO를, 엔티티로 변환
        CommentEntity commentEntity = CommentEntity.createCommentEntity(commentDto, searchArticleEntity);

        // TODO : 3. 서비스 계층에서 DB에 articleId에 해당되는 게시글에 댓글 생성
        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);

        // TODO : 4. 서비스 계층에서 게시글 저장하고 반환받은 엔티티를 DTO로 변환
        CommentDto returnDto = CommentDto.createCommentDto(savedCommentEntity);

        // TODO : 5. 응답으로 생성한 댓글 데이터 반환
        return returnDto;
    }

    // TODO : READ
    public List<CommentDto> getCommentsByArticleId(Long articleId) {
        // TODO : 1. 서비스 계층에서 DB에서 articleId에 해당되는 게시글의 모든 댓글 데이터 묶음을 조회
        List<CommentEntity> searchedComments = commentRepository.findCommentEntitiesByArticleId(articleId);

        // TODO : 2. 서비스 계층에서 댓글 엔티티를, 댓글 DTO로 변환
        List<CommentDto> returnComments = new ArrayList<>();
        for (CommentEntity comment : searchedComments) {
            returnComments.add(CommentDto.createCommentDto(comment));
        }

        // TODO : 3. 서비스 계층에서 응답으로 조회한 댓글 데이터 묶음 반환
        return returnComments;
    }

    // TODO : UPDATE
//    public CommentDto editComment(Long id) {
//
//    }

    // TODO : DELETE
}
