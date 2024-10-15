package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// 프론트에서 보낸 데이터에 맞는 dto를 만든다.
@AllArgsConstructor
@ToString
public class ArticleForm {

    // dto에 필요한 필드를 변수로 선언함
    private Long id;
    private String title;
    private String content;

    // 생성자를 통해서 객체를 생성할 때 매개변수를 강제함
//    public ArticleForm(String title, String content) {
//        this.title = title;
//        this.content = content;
//    }

    // 오버라이드를 통해서 Object 클래스의 toString() 메서드를 재정의해서 사용
    // 객체의 속성을 표현하기 위해 맞게 변형함
//    @Override
//    public String toString() {
//        return "ArticleForm{" +
//                "title='" + title + '\'' +
//                ", content='" + content + '\'' +
//                '}';
//    }

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
