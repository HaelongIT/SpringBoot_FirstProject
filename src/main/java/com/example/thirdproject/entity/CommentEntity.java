package com.example.thirdproject.entity;

import com.example.thirdproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "comment")
@Getter
@ToString(exclude = "article")
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

    @Column
    private String nickname;

    @Column
    private String body;

    // TODO : DTO -> 엔티티로 변환하는 메서드
    public static CommentEntity createCommentEntity(CommentDto dto, ArticleEntity article) {
        return new CommentEntity(null, article, dto.getNickname(), dto.getBody());
    }

    // TODO : DTO를 매개변수로 받아서, 엔티티를 수정하는 메서드
}
