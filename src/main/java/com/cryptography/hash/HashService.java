package com.cryptography.hash;

import java.security.NoSuchAlgorithmException;

public interface HashService {
    byte[] hashPhrase(String phrase) throws NoSuchAlgorithmException;
}
