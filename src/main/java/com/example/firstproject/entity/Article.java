package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

    public Article patch(Article articleEntity) {
        if (articleEntity.title != null) {      // 수정할 데이터에 title이 존재할 경우만 덮어쓰기
            this.title = articleEntity.title;   // 기존 타이틀에서 수정 타이틀로 변경
        }
        if (articleEntity.content != null) {       // 수정할 데이터에 content가 있을 경우에만 덮어쓰기
            this.content = articleEntity.content;
        }
        return this;
    }

//    public Long getId() {
//    }

    // 기본 생성자 추가
//    Article() {
//
//    }

    // Article 생성자 추가
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

    // toString 매서드 추가
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
