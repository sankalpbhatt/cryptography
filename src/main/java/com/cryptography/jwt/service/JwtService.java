package com.cryptography.jwt.service;

import com.cryptography.jwt.model.CreateJwtRequest;
import com.cryptography.jwt.model.JwtResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.security.NoSuchAlgorithmException;

public interface JwtService {
    String generateJwt(CreateJwtRequest createJwtRequest) throws NoSuchAlgorithmException;
    JwtResponse decodeJwt(String jwtToken) throws JsonProcessingException;
}
