package com.example.thuctap.postgresql.controller;

import com.example.thuctap.postgresql.entity.Account;
import com.example.thuctap.postgresql.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account/")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("list")
    public ResponseEntity<?> getAllAccount() {
        return ResponseEntity.status(200).body(accountService.getAccount());
    }

    @PostMapping("add")
    public ResponseEntity<?> addAccount(@RequestBody Account account) {
        return ResponseEntity.ok().body(accountService.addAccount(account));
    }

    @GetMapping("detail/{idAccount}")
    public ResponseEntity<?> detailAccount(@PathVariable("idAccount") Long idAccount) {
        return ResponseEntity.ok().body(accountService.detailAccount(idAccount));
    }

    @PutMapping("update")
    public ResponseEntity<?> updateAccount(@RequestBody Account account) {
        return ResponseEntity.ok().body(accountService.updateAccount(account));
    }

    @DeleteMapping("delete/{idAccount}")
    public ResponseEntity<?> deleteRole(@PathVariable("idAccount") Long idAccount) {
        accountService.deleteAccount(idAccount);
        return ResponseEntity.ok().build();
    }
}
