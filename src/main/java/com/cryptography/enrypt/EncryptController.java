package com.cryptography.enrypt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/encrypt")
public class EncryptController {

    private final EncryptService encryptService;

    private EncryptController(EncryptService encryptService){
        this.encryptService = encryptService;
    }

    @GetMapping("/{phrase}")
    private byte[] encrypt(@PathVariable String phrase){
        return encryptService.encrypt(phrase);
    }

    @GetMapping("/test-jwt")
    private void testJwt() throws NoSuchAlgorithmException, InvalidKeySpecException {
        encryptService.testJwt();
    }

}
