package com.example.thuctap.postgresql.service;

import com.example.thuctap.postgresql.entity.Comment;
import com.example.thuctap.postgresql.entity.Role;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    Comment addComment(Comment comment);

    Comment detailComment(Long idComment);

    Comment updateComment(Comment comment);

    void deleteComment(Long idComment);
}
