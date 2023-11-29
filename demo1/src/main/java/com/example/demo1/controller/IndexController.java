package com.example.demo1.controller;

import com.example.demo1.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/demo/")
public class IndexController {

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MANAGER')")
    @GetMapping("test")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Authentication and Authorization is success");
    }
}
