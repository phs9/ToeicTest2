package com.sang.toeictest2.Service.Impl;

import com.sang.toeictest2.DTO.Response.AccountDTO;
import com.sang.toeictest2.Entity.Account;
import com.sang.toeictest2.Entity.Result;
import com.sang.toeictest2.Repository.AccountRepository;
import com.sang.toeictest2.Repository.ResultRepository;
import com.sang.toeictest2.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public boolean createAccount(Account account) {
        if (account.getPassword() != null)
            account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return true;
    }

    @Override
    public boolean deleteAccount(Long id) {
        List<Result> results = resultRepository.findByAccount(accountRepository.getById(id));
        resultRepository.deleteAll(results);
        accountRepository.deleteById(id);
        return true;
    }

    @Override
    public List<AccountDTO> getAllAccount() {
        List<Account> list = accountRepository.findAll();
        return list.stream()
                .map(account -> new AccountDTO(account))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository.getById(id);
        return new AccountDTO(account);
    }

    @Override
    public boolean updateAccount(Account accountRe, Long id) {
        Account account = accountRepository.getById(id);
        account.setEmail(accountRe.getEmail());
        account.setFullName(accountRe.getFullName());
        account.setPhone(accountRe.getPhone());
        account.setAddress(accountRe.getAddress());
        account.setRole(accountRe.getRole());
        accountRepository.save(account);
        return true;
    }
}
