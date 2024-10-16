package com.example.secondproject.controller;

import com.example.secondproject.dto.ArticleForm;
import com.example.secondproject.entity.Article;
import com.example.secondproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        // System.out.println(form.toString());        // form 데이터 dto로 변환 확인
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환
        Article article = form.toEntity();
        // System.out.println(article.toString());     // dto 데이터 entity로 변환 확인
        log.info(article.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        // System.out.println(saved.toString());       // entity가 db로 저장된거 확인
        log.info(saved.toString());
        return "";
    }
}
