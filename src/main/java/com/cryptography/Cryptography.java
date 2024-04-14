package com.cryptography;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Cryptography {

    public static void main(String [] args){
        SpringApplication.run(Cryptography.class, args);
    }
}
