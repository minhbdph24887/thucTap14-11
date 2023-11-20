package com.example.thuctap.postgresql.service.impl;

import com.example.thuctap.postgresql.entity.Role;
import com.example.thuctap.postgresql.repository.RoleRepository;
import com.example.thuctap.postgresql.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role addRole(Role role) {
        Role newRole = Role.builder()
                .codeRoles(role.getCodeRoles())
                .nameRoles(role.getNameRoles())
                .status(role.getStatus())
                .build();
        return roleRepository.save(newRole);
    }

    @Override
    public Role detailRole(Long idRole) {
        return roleRepository.findById(idRole).orElse(null);
    }

    @Override
    public Role updateRole(Role role) {
        Role updateRole = roleRepository.findById(role.getIdRoles()).orElse(null).builder()
                .idRoles(role.getIdRoles())
                .codeRoles(role.getCodeRoles())
                .nameRoles(role.getNameRoles())
                .status(role.getStatus())
                .build();
        return roleRepository.save(updateRole);
    }

    @Override
    public void deleteRole(Long idRole) {
        roleRepository.deleteById(idRole);
    }

    @Override
    public Role getAllRoleByUsernameAndPassword(String username, String password) {
        return roleRepository.getAllRoleByUsernameAndPassword(username, password);
    }
}
