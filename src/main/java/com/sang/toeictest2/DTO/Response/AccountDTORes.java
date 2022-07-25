package com.sang.toeictest2.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTORes {
    private Long id;
    private String email;
    private String name;
    private String roles;
    private String type = "Bearer";
    private String token;

    public AccountDTORes(String accessToken, Long id, String email, String roles,String name) {
        this.token = accessToken;
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
    }
}