package com.example.thuctap.postgresql.controller;

import com.example.thuctap.postgresql.entity.Comment;
import com.example.thuctap.postgresql.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment/")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("list")
    public ResponseEntity<?> getAllComment() {
        return ResponseEntity.ok().body(commentService.getAllComments());
    }

    @PostMapping("add")
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        return ResponseEntity.ok().body(commentService.addComment(comment));
    }

    @GetMapping("detail/{idComment}")
    public ResponseEntity<?> detailCommnet(@PathVariable("idComment") Long idComment) {
        return ResponseEntity.ok().body(commentService.detailComment(idComment));
    }

    @PutMapping("update")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment) {
        return ResponseEntity.ok().body(commentService.updateComment(comment));
    }

    @DeleteMapping("delete/{idComment}")
    public ResponseEntity<?> deleteComment(@PathVariable("idComment") Long idComment) {
        commentService.deleteComment(idComment);
        return ResponseEntity.ok().build();
    }
}
