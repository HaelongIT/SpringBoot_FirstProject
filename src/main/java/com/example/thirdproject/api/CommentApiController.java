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
//    @GetMapping("/articles/{articleId}/comments")
//    public ResponseEntity<List<CommentDto>> readComments(@PathVariable Long articleId)

    // TODO : UPDATE

    // TODO : DELETE

}
