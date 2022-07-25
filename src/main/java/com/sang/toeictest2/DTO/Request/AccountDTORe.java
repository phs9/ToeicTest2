package com.sang.toeictest2.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTORe {

    private Long id;
    private String email;
    private String fullName;
    private String address;
    private String phone;
    private String role;

}
