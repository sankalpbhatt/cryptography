package com.cryptography.jwt.helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;

public class JwtHelper {

    private JwtHelper(){}

    public static String generateJWTToken(String clientId, PrivateKey privateKey) {
        Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) privateKey);
        return JWT.create()
                .withClaim("clientId", clientId)
                .sign(algorithm);
    }
}
