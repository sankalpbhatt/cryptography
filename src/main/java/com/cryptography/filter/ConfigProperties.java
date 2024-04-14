package com.cryptography.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "my")
@Component
public class ConfigProperties {

    String privateKey;
    String publicKey;
    String exampleJwt;
    List<String> aList;
    Map<String, String> aMap;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getExampleJwt() {
        return exampleJwt;
    }

    public void setExampleJwt(String exampleJwt) {
        this.exampleJwt = exampleJwt;
    }

    public List<String> getaList() {
        return aList;
    }

    public void setaList(List<String> aList) {
        this.aList = aList;
    }

    public Map<String, String> getaMap() {
        return aMap;
    }

    public void setaMap(Map<String, String> aMap) {
        this.aMap = aMap;
    }
}
