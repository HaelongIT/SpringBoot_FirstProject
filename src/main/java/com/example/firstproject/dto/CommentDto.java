package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto toDto(Comment comment) {
        CommentDto changedDto = new CommentDto(comment.getId(), comment.getArticle().getId(),
                comment.getNickname(), comment.getBody());
        return changedDto;
    }
}
