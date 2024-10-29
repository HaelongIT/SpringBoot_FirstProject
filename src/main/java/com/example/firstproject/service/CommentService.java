package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    // 1. 댓글 조회
    public List<CommentDto> getCommentById(Long articleId) {
//        // 1. 리파지터리의 findByArticleId 메서드를 이용해서 엔티티 묶음 가져오기
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 2. 엔티티 묶음을 dto 묶음으로 변경
//        List<CommentDto> commentDtos = new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment selectComment = comments.get(i);
//            CommentDto ChangedDto = CommentDto.toDto(selectComment);
//            commentDtos.add(ChangedDto);
//        }
//        // 3. 리턴
//        return commentDtos;

        // 1번 2번 3번 과정을 리팩토링(스트림 문법 사용)
        return commentRepository.findByArticleId(articleId)     // 엔티티 묶음 가져오기
                .stream()
                .map(comment -> CommentDto.toDto(comment)) // 각 엔티티를 dto로 변환
                .collect(Collectors.toList());                  // 스트림 데이터를 리스트 자료형으로 변환
    }

    // 2. 댓글 생성
    @Transactional
    public CommentDto createCommentById(Long articleId, CommentDto commentDto) {
        // 부모 게시글 있는지 확인 후 없을 경우 예외 발생
        Article parentArticle = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" +
                        "대상 게시글이 없습니다."));
        // dto를 엔티티로 변환(dto에 toEntity() 메서드 추가)
        Comment saveComment = Comment.toEntity(parentArticle, commentDto);
        // 레파지터리의 메서드 이용해서 DB에 댓글 추가(레파지터리 메서드도 추가 필요)
        Comment created = commentRepository.save(saveComment);
        // 생성하면서 받은 엔티티를 다시 dto로 변환하기
        CommentDto createdDto = CommentDto.toDto(created);
        // 반환
        return createdDto;
    }
}
