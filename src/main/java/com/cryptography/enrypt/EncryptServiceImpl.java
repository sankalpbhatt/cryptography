package com.cryptography.enrypt;

import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

}
