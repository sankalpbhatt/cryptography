package com.cryptography.enrypt;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encrypt")
public class EncryptController {

    private final EncryptService encryptService;

    public EncryptController(EncryptService encryptService){
        this.encryptService = encryptService;
    }

    @PostMapping
    public String encrypt(String phrase){
        return encryptService.encrypt(phrase);
    }
}
