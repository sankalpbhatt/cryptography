package com.cryptography.hash;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/hash")
public class HashController {

    private final HashService hashService;


    public HashController(HashService hashService) {
        this.hashService = hashService;
    }

    @GetMapping("/{phrase}")
    public byte[] hashPhrase(@PathVariable String phrase) throws NoSuchAlgorithmException {
        return hashService.hashPhrase(phrase);
    }

}
