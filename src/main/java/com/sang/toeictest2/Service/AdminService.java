package com.sang.toeictest2.Service;

import com.sang.toeictest2.DTO.Request.AccountDTORe;
import com.sang.toeictest2.DTO.Response.AccountDTO;
import com.sang.toeictest2.Entity.Account;

import java.util.List;

public interface AdminService {
    boolean createAccount(Account account);

    boolean deleteAccount(Long id);

    List<AccountDTO> getAllAccount();

    AccountDTO getAccountById(Long id);

    boolean updateAccount(AccountDTORe accountDTORe, Long id);
}
