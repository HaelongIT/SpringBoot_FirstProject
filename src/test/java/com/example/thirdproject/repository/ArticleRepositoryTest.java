package com.example.thirdproject.repository;

import com.example.thirdproject.entity.ArticleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    @BeforeEach
    void insertDummyData() {
        ArticleEntity articleEntity = new ArticleEntity(null, "제목1", "내용1");
        articleRepository.save(articleEntity);
    }

    @Test
    void findArticleEntityById() {
        // 1. 예상 데이터
        ArticleEntity expectArticleEntity = new ArticleEntity(1L, "제목1", "내용1");

        // 2. 실제 데이터
        ArticleEntity realArticleEntity = articleRepository.findArticleEntityById(1L);

        // 3. 비교 및 검증
        assertEquals(expectArticleEntity.toString(), realArticleEntity.toString());
        System.out.println("realArticleEntity = " + realArticleEntity);
    }
}