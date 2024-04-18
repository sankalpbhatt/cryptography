package com.cryptography.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cryptography.jwt.helper.JwtHelper;
import com.cryptography.jwt.model.CreateJwtRequest;
import com.cryptography.jwt.model.JwtHeader;
import com.cryptography.jwt.model.JwtPayload;
import com.cryptography.jwt.model.JwtResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

@Service
public class JwtServiceImpl implements JwtService{

    private static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);

    private final KeyPair jwtKeyPair;
    private final ObjectMapper objectMapper;

    public JwtServiceImpl(KeyPair jwtKeyPair) {
        this.jwtKeyPair = jwtKeyPair;
        this.objectMapper = new ObjectMapper();
    }

    public String generateJwt(CreateJwtRequest createJwtRequest) {
        String clientId = "your_client_id";

        // Generate JWT token using private key
        String jwtToken = JwtHelper.generateJWTToken(clientId, jwtKeyPair.getPrivate());
        logger.info("jwt token : {}", jwtToken);

        // Decode JWT token using public key
        return jwtToken;
    }

    public JwtResponse decodeJwt(String jwtToken) throws JsonProcessingException {
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwtKeyPair.getPublic(), null);
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(jwtToken);
        logger.info("Decoded JWT:");
        logger.info("Client ID: {}", decodedJWT.getClaim("clientId").asString());
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwtHeader(objectMapper.readValue(decodedJWT.getHeader(), JwtHeader.class));
        jwtResponse.setJwtPayload(objectMapper.readValue(decodedJWT.getPayload(), JwtPayload.class));
        return jwtResponse;
    }
}
