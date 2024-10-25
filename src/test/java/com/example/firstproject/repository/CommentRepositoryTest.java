package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @DisplayName("특정 게시글의 모든 댓글 조회")
    @Test
    void findByArticleId() {
        // CASE1 : 4번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비

            // 2. 실제 데이터

            // 3. 예상 데이터

            // 4. 비교 및 검증
        }
        // CASE2 : 1번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터

            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }
    }

    @Test
    void findByNickname() {
    }
}