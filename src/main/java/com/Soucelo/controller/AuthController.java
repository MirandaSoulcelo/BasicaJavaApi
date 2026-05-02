package com.Soucelo.controller;

import com.Soucelo.security.CustomUserDetails;
import com.Soucelo.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {

        var auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(),
                                                        dto.getPassword()
                )
        );

        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

        return jwtService.generateToken(user.getUsername());
    }
}
