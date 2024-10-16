package com.example.secondproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor         // 생성자를 대신하는 어노테이션
@ToString                   // toString() 메서드를 대신하는 어노테이션
@NoArgsConstructor          // 기본 생성자를 추가하는 어노테이션(jpa의 엔티티에는 기본 생성자가 필수)
@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    // Article 생성자 추가
//    public Article(Long id, String title, String content) {
//        this.id = id;
//        this.title = title;
//        this.content = content;
//    }

    // toString() 메서드 추가(확인용)
//    @Override
//    public String toString() {
//        return "Article{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }
}
