package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 1. 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> readComments(@PathVariable Long articleId) {
        // 서비스 위임
        List<CommentDto> returnDtos = commentService.getCommentById(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(returnDtos);
    }

    // 2. 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> createComments(@PathVariable Long articleId,
                                                     @RequestBody CommentDto commentDto) {
        // 서비스 위임
        CommentDto createdDto = commentService.createCommentById(articleId, commentDto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 3. 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> updateComments(@PathVariable Long id,
                                                     @RequestBody CommentDto commentDto) {
        // 서비스에 위임
        CommentDto updatedDto = commentService.updateCommentById(id, commentDto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    // 4. 댓글 삭제

}
