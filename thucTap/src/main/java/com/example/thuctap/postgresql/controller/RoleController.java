package com.example.thuctap.postgresql.controller;

import com.example.thuctap.postgresql.entity.Role;
import com.example.thuctap.postgresql.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role/")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("list")
    public ResponseEntity<?> getAllRole() {
        return ResponseEntity.ok().body(roleService.getAllRole());
    }

    @PostMapping("add")
    public ResponseEntity<?> addRole(@RequestBody Role role) {
        return ResponseEntity.ok().body(roleService.addRole(role));
    }

    @GetMapping("detail/{idRole}")
    public ResponseEntity<?> detailRole(@PathVariable("idRole") Long idRole) {
        return ResponseEntity.ok().body(roleService.detailRole(idRole));
    }

    @PutMapping("update")
    public ResponseEntity<?> updateRole(@RequestBody Role roleDetails) {
        return ResponseEntity.ok().body(roleService.updateRole(roleDetails));
    }
    
    @DeleteMapping("delete/{idRole}")
    public ResponseEntity<?> deleteRole(@PathVariable("idRole") Long idRole) {
        roleService.deleteRole(idRole);
        return ResponseEntity.ok().build();
    }
}

