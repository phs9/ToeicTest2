package com.sang.toeictest2.Controller;

import com.sang.toeictest2.DTO.Request.AccountDTORe;
import com.sang.toeictest2.DTO.Response.AccountDTO;
import com.sang.toeictest2.Entity.Account;
import com.sang.toeictest2.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/account")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllAccount() {
        List<AccountDTO> listAccount = this.adminService.getAllAccount();
        return new ResponseEntity<>(listAccount, HttpStatus.OK);
    }

    @GetMapping("/account/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAccountById(@PathVariable("id") Long id) {
        AccountDTO accountDTO = this.adminService.getAccountById(id);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @PostMapping("/account")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createAccount(@RequestBody Account account) {
        boolean status = this.adminService.createAccount(account);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/account/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateAccount(@RequestBody AccountDTORe accountDTORe, @PathVariable("id") Long id) {
        boolean status = this.adminService.updateAccount(accountDTORe, id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @DeleteMapping("/account/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAccount(@PathVariable("id") Long id) {
        boolean status = this.adminService.deleteAccount(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
