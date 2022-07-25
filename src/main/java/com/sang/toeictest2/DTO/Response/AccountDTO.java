package com.sang.toeictest2.DTO.Response;

import com.sang.toeictest2.Entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private String role;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.address = account.getAddress();
        this.email = account.getEmail();
        this.fullName = account.getFullName();
        this.role = account.getRole().substring(5);
        this.phone = account.getPhone();
    }

}