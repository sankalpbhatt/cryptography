package com.cryptography.enrypt;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface EncryptService {

    byte[] encrypt(String phrase);

    void testJwt() throws NoSuchAlgorithmException, InvalidKeySpecException;
}
