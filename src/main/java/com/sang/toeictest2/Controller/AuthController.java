package com.sang.toeictest2.Controller;

import com.sang.toeictest2.DTO.Request.AccountRequest;
import com.sang.toeictest2.DTO.Response.AccountDTORes;
import com.sang.toeictest2.Repository.AccountRepository;
import com.sang.toeictest2.Security.jwt.JwtUtils;
import com.sang.toeictest2.Security.service.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authencateUser(@RequestBody AccountRequest accountRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountRequest.getUsername(), accountRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.genarateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String role = roles.get(0);
        String name = userDetails.getName();

        return ResponseEntity.ok(new AccountDTORes(jwt, userDetails.getId(), userDetails.getUsername(), role, name));
    }

}
