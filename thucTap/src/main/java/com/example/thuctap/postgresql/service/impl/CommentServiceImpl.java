package com.example.thuctap.postgresql.service.impl;

import com.example.thuctap.postgresql.entity.Account;
import com.example.thuctap.postgresql.entity.Comment;
import com.example.thuctap.postgresql.repository.CommentRepository;
import com.example.thuctap.postgresql.service.AccountService;
import com.example.thuctap.postgresql.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AccountService accountService;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment addComment(Comment comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Account detailAccount = accountService.detailAccountByUserName(currentPrincipalName);
        Comment newComment = Comment.builder()
                .codeComment(comment.getCodeComment())
                .contents(comment.getContents())
                .account(detailAccount)
                .status(comment.getStatus())
                .build();
        return commentRepository.save(newComment);
    }

    @Override
    public Comment detailComment(Long idComment) {
        return commentRepository.findById(idComment).orElse(null);
    }

    @Override
    public Comment updateComment(Comment comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Account detailAccount = accountService.detailAccountByUserName(currentPrincipalName);
        Comment detailComment = commentRepository.findById(comment.getIdComment()).orElse(null);
        assert detailComment != null;
        if(!detailComment.getAccount().equals(detailAccount)){
            throw new AccessDeniedException("This account does not have permission to edit this data");
        }else{
            Comment updateComment = detailComment.builder()
                    .idComment(comment.getIdComment())
                    .codeComment(comment.getCodeComment())
                    .contents(comment.getContents())
                    .account(comment.getAccount())
                    .status(comment.getStatus())
                    .build();
            return commentRepository.save(updateComment);
        }
    }

    @Override
    public void deleteComment(Long idComment) {
        commentRepository.deleteById(idComment);
    }
}
