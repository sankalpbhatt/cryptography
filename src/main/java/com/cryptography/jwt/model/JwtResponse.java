package com.cryptography.jwt.model;

public class JwtResponse {

    private JwtHeader jwtHeader;
    private JwtPayload jwtPayload;

    public JwtHeader getJwtHeader() {
        return jwtHeader;
    }

    public void setJwtHeader(JwtHeader jwtHeader) {
        this.jwtHeader = jwtHeader;
    }

    public JwtPayload getJwtPayload() {
        return jwtPayload;
    }

    public void setJwtPayload(JwtPayload jwtPayload) {
        this.jwtPayload = jwtPayload;
    }
}
