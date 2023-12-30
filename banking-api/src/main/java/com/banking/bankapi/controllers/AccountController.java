package com.banking.bankapi.controllers;

import com.banking.bankapi.dto.AccountDto;
import com.banking.bankapi.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody AccountDto accountDto
    ) {
        return ResponseEntity.ok(service.save(accountDto));
    }
    @GetMapping("/")
    public ResponseEntity<List<AccountDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountDto> findById(
            @PathVariable("account-id") Integer accountId
    ) {
        return ResponseEntity.ok(service.findById(accountId));
    }

    @DeleteMapping("/{account-id}")
    public ResponseEntity<Void> delete(
        @PathVariable("account-id") Integer accountId
    ) {
        service.delete(accountId);
        return ResponseEntity.accepted().build();
    }
}
