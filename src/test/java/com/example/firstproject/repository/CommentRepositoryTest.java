package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;            // 레포지토리 의존성 주입

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        // CASE1 : 4번 게시글의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글 고");    // 부모 게시글 객체 생성
            Comment a = new Comment(1L, article, "Park", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "Choi", "쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력");
        }
        // CASE2 : 1번 게시글의 모든 댓글 조회(댓글이 하나도 없음)
        {
            // 1. 입력 데이터 준비
            Long articleId = 1L;
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 3. 예상 데이터
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // CASE1 : "Park"의 모든 댓글 조회
        {
            // 1. 입력 데이터 준비
            String nick = "Park";
            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByNickname(nick);
            // 3. 예상 데이터
            Article article1 = new Article(4L, "당신의 인생 영화는?", "댓글 고");
            Article article2 = new Article(5L, "당신의 소울 푸드는?", "댓글 고고");
            Article article3 = new Article(6L, "당신의 취미는?", "댓글 고고곡");
            Comment a = new Comment(1L, article1, "Park", "굿 윌 헌팅");
            Comment b = new Comment(4L, article2, "Park", "치킨");
            Comment c = new Comment(7L, article3, "Park", "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(expected.toString(), comments.toString(), "Park의 모든 데이터 조회 완료!");
        }
    }
}