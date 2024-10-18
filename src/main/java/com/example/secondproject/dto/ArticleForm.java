package com.example.secondproject.dto;

import com.example.secondproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor     // 생성자 만들어주는 어노테이션
@ToString               // toString() 메서드를 대신하는 어노테이션
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    // 생성자 만들기
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

    // 확인용으로 toString() 추가
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    // dto를 엔티티로 바꿔줄 메서드 추가
    public Article toEntity() {
        return new Article(id, title, content);
    }
}
