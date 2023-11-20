package com.example.thuctap.postgresql.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    @NotBlank
    @Size(max = 200, message = "The Comment Code Cannot Exceed 200 Characters")
    @Column(name = "code")
    private String codeComment;

    @NotBlank
    @Size(max = 5000, message = "The Contents Cannot Exceed 200 Characters")
    @Column(name = "contents")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private Account account;

    @Column(name = "status")
    private Integer status;
}
