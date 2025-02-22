package com.example.thirdproject.dto;

import com.example.thirdproject.entity.ArticleEntity;
import com.example.thirdproject.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private String nickname;
    private String body;

    // TODO : 엔티티 -> DTO로 변환하는 메서드
    public static CommentDto createCommentDto(CommentEntity entity) {
        return new CommentDto(entity.getNickname(), entity.getBody());
    }
}
