package com.example.thuctap.postgresql.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoles;

    @NotBlank
    @Size(max = 200, message = "The Position Code Cannot Exceed 200 Characters")
    @Column(name = "code")
    private String codeRoles;

    @NotBlank
    @Size(max = 200, message = "The Position Name Cannot Exceed 200 Characters")
    @Column(name = "name")
    private String nameRoles;

    @NotNull
    @Column(name = "status")
    private Integer status;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    List<Account> accounts;
}
