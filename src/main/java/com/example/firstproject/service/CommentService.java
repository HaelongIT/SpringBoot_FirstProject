package com.example.firstproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;
}
