package com.example.demo1.service.impl;

import com.example.demo1.entity.Account;
import com.example.demo1.entity.Comment;
import com.example.demo1.repository.AccountRepository;
import com.example.demo1.repository.CommentRepository;
import com.example.demo1.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              AccountRepository accountRepository) {
        this.commentRepository = commentRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Comment detailComment(Long idComment) {
        return commentRepository.findById(idComment).orElse(null);
    }

    @Override
    public Comment saveComment(Comment comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentEmail = authentication.getName();
        if (currentEmail != null) {
            Account account = accountRepository.findAccountByEmail(currentEmail).orElse(null);
            Comment saveComment = new Comment();
            saveComment.setCode(comment.getCode());
            saveComment.setContent(comment.getContent());
            saveComment.setAccount(account);
            saveComment.setDateCreate(LocalDate.now());
            saveComment.setDateUpdate(LocalDate.now());
            saveComment.setStatus(1);
            return commentRepository.save(saveComment);
        } else {
            return null;
        }
    }

    @Override
    public Comment updateComment(Comment comment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Account detailAccount = accountRepository.findAccountByEmail(currentPrincipalName).orElse(null);
        Comment detailComment = commentRepository.findById(comment.getId()).orElse(null);
        if (detailComment != null) {
            if (!detailComment.getAccount().equals(detailAccount)) {
                System.out.println("This account does not have permission to edit this data");
                throw new AccessDeniedException("This account does not have permission to edit this data");
            } else {
                Comment updateComment = new Comment();
                updateComment.setId(comment.getId());
                updateComment.setCode(comment.getCode());
                updateComment.setContent(comment.getContent());
                updateComment.setAccount(detailComment.getAccount());
                updateComment.setDateCreate(detailComment.getDateCreate());
                updateComment.setDateUpdate(LocalDate.now());
                updateComment.setStatus(comment.getStatus());
                return commentRepository.save(updateComment);
            }
        } else {
            System.out.println("Detail Account is not exits");
            return null;
        }
    }
}
