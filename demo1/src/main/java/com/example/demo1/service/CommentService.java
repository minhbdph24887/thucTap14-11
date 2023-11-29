package com.example.demo1.service;

import com.example.demo1.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComment();

    Comment detailComment(Long idComment);

    Comment saveComment(Comment comment);

    Comment updateComment(Comment comment);
}
