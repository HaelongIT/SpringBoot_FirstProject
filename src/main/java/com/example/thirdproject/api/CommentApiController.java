package com.example.thirdproject.api;

import com.example.thirdproject.dto.CommentDto;
import com.example.thirdproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentApiController {
    private final CommentService commentService;

    // CREATE
//    @PostMapping("/articles/{articleId}/comments")
//    public ResponseBody<CommentDto> createComment(@PathVariable )

    // READ

    // UPDATE

    // DELETE

}
