package com.cryptography.jwt.controller;

import com.cryptography.jwt.model.CreateJwtRequest;
import com.cryptography.jwt.model.JwtResponse;
import com.cryptography.jwt.service.JwtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController()
@RequestMapping("/jwt")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/generate")
    public String generateJwt( CreateJwtRequest createJwtRequest ) throws NoSuchAlgorithmException {
        return jwtService.generateJwt(createJwtRequest);
    }

    @GetMapping("/decode")
    public JwtResponse decodeJwt(@RequestParam("token") String jwt) throws JsonProcessingException {
        return jwtService.decodeJwt(jwt);
    }
}
