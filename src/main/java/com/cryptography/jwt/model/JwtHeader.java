package com.cryptography.jwt.model;

public class JwtHeader {

    private String type;
    private String alg;
    private String kId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getkId() {
        return kId;
    }

    public void setkId(String kId) {
        this.kId = kId;
    }
}
