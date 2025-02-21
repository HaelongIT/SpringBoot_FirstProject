package com.example.thirdproject.repository;

import com.example.thirdproject.entity.ArticleEntity;
import com.example.thirdproject.entity.CommentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void insertDummyData() {
        ArticleEntity dummyArticle = new ArticleEntity(null, "제목1", "내용1");
        ArticleEntity dummyArticle2 = new ArticleEntity(null, "제목2", "내용2");
        List<ArticleEntity> articleEntities = Arrays.asList(dummyArticle, dummyArticle2);
        List<ArticleEntity> savedArticles = articleRepository.saveAll(articleEntities);

        CommentEntity dummyComment = new CommentEntity(null, savedArticles.get(0), "닉네임1", "내용1");
        CommentEntity dummyComment2 = new CommentEntity(null, savedArticles.get(0), "닉네임2", "내용2");
        List<CommentEntity> commentEntities = Arrays.asList(dummyComment, dummyComment2);

        commentRepository.save(dummyComment);
    }

//    @Test
//    void findCommentEntitiesByArticle() {
//        // 1. 예상 데이터
//
//
//        // 2. 실제 데이터
//
//        // 3. 비교 및 검증
//    }
//ㅊ
//    @Test
//    void findCommentEntityById() {
//        // 1. 예상 데이터
//
//        // 2. 실제 데이터
//
//        // 3. 비교 및 검증
//    }
}