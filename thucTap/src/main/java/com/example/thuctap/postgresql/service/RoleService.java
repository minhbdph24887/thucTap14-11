package com.example.thuctap.postgresql.service;

import com.example.thuctap.postgresql.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();

    Role addRole(Role role);

    Role detailRole(Long idRole);

    Role updateRole(Role role);

    void deleteRole(Long idRole);

    Role getAllRoleByUsernameAndPassword(String username, String password);
}
