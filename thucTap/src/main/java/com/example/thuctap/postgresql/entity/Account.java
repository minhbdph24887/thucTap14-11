package com.example.thuctap.postgresql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;

    @Size(max = 200, message = "The Account Code Cannot Exceed 200 Characters")
    @Column(name = "code")
    private String codeAccount;

    @Size(max = 200, message = "The Account Name Cannot Exceed 200 Characters")
    @Column(name = "name")
    private String nameAccount;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private boolean gender;

    @NotBlank
    @Size(max = 200, message = "The Address Cannot Exceed 200 Characters")
    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Comment> comments;
}
