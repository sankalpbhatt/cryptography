package com.cryptography.hash;


import com.cryptography.filter.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class ConfigController {

    @Autowired
    ConfigProperties configProperties;

    @GetMapping
    public ConfigProperties getConfig(){
        return configProperties;
    }

}
