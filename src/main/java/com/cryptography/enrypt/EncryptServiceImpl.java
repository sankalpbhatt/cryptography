package com.cryptography.enrypt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Service
public class EncryptServiceImpl implements EncryptService{

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";

    private SecretKey key;
    private Cipher encryptionCipher;

    @Override
    public byte[] encrypt(String phrase) {
        init();
        byte[] dataInBytes = phrase.getBytes();
        populateEncryptionCypher();
        initialiseEncryptionCypher();
        byte[] encryptedBytes = getEncryptedBytes(dataInBytes);
        return encode(encryptedBytes).getBytes();
    }

    @Override
    public void testJwt() throws NoSuchAlgorithmException {
        String clientId = "your_client_id";

        // Generate key pair
        KeyPair keyPair = generateKeyPair(clientId);

        // Generate JWT token using private key
        String jwtToken = generateJWTToken(clientId, keyPair.getPrivate());
        System.out.println("jwt token : "+ jwtToken);

        // Decode JWT token using public key
        decodeJWTToken(jwtToken, keyPair.getPublic());
    }

    private void initialiseEncryptionCypher() {
        try {
            encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateEncryptionCypher() {
        try {
            encryptionCipher = Cipher.getInstance(TRANSFORMATION);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getEncryptedBytes(byte[] dataInBytes) {
        byte[] encryptedBytes;
        try {
            encryptedBytes = encryptionCipher.doFinal(dataInBytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
        return encryptedBytes;
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public void init() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        int KEY_SIZE = 128;
        keyGenerator.init(KEY_SIZE);
        key = keyGenerator.generateKey();
    }

    public static KeyPair generateKeyPair(String clientId) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, new SecureRandom(clientId.getBytes()));

        KeyPair kp = keyPairGenerator.generateKeyPair();
        byte[] privateKeyBytes = kp.getPrivate().getEncoded();
        byte[] publicKeyBytes = kp.getPublic().getEncoded();

        String privateKeyString = Base64.getEncoder().encodeToString(privateKeyBytes);
        System.out.println(" privateKeyString : "+privateKeyString);
        String publicKeyString = Base64.getEncoder().encodeToString(publicKeyBytes);
        System.out.println(" publicKeyString : "+publicKeyString);
        return kp;
    }

    public static String generateJWTToken(String clientId, PrivateKey privateKey) {
        Algorithm algorithm = Algorithm.RSA256(null, (RSAPrivateKey) privateKey);
        return JWT.create()
                .withClaim("clientId", clientId)
                .sign(algorithm);
    }

    public static void decodeJWTToken(String jwtToken, PublicKey publicKey) {
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) publicKey, null);
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .build()
                .verify(jwtToken);
        System.out.println("Decoded JWT:");
        System.out.println("Client ID: " + decodedJWT.getClaim("clientId").asString());
    }
}
