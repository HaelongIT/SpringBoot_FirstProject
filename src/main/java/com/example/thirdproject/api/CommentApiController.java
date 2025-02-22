package com.example.thirdproject.api;

import com.example.thirdproject.dto.CommentDto;
import com.example.thirdproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    // TODO : CREATE
    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long articleId) {
        // TODO : 서비스에 위임
        CommentDto createdDto = commentService.addCommentToArticle(commentDto, articleId);

        // TODO : 반환
        return createdDto != null
                ? ResponseEntity.ok(createdDto)
                : ResponseEntity.badRequest().build();
    }

    // TODO : READ
    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> readComments(@PathVariable Long articleId) {
        // TODO : 서비스에 위임
        List<CommentDto> readDtos = commentService.getCommentsByArticleId(articleId);

        // TODO : 반환
        return readDtos != null
                ? ResponseEntity.ok(readDtos)
                : ResponseEntity.badRequest().build();
    }

    // TODO : UPDATE
    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable Long id) {
        // TODO : 서비스에 위임
        CommentDto updatedDto = commentService.editComment(commentDto, id);

        // TODO : 반환
        return updatedDto != null
                ? ResponseEntity.ok(updatedDto)
                : ResponseEntity.badRequest().build();
    }

    // TODO : DELETE
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        // TODO : 서비스에 위임
        commentService.removeComment(id);

        // TODO : 반환
        return ResponseEntity.noContent().build();
    }
}
