package com.cryptography.jwt.cofig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Configuration
public class JwtConfig {

    private static final Logger logger = LoggerFactory.getLogger(JwtConfig.class);

    @Value("${clientId}")
    private static final String clientId = "clientId";

    @Bean
    public static KeyPair getJwtKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, new SecureRandom(clientId.getBytes()));

        KeyPair kp = keyPairGenerator.generateKeyPair();
        byte[] privateKeyBytes = kp.getPrivate().getEncoded();
        byte[] publicKeyBytes = kp.getPublic().getEncoded();

        String privateKeyString = Base64.getEncoder().encodeToString(privateKeyBytes);
        logger.info(" privateKeyString : {}", privateKeyString);
        String publicKeyString = Base64.getEncoder().encodeToString(publicKeyBytes);
        logger.info(" publicKeyString : {}", publicKeyString);
        return kp;
    }
}
