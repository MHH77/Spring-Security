package org.mhh.constance;

public interface SecurityConstance {
    // this is secret key that only server know
    public static final String JWT_KEY = "jwtSecret";
    // this the name of header that the token is set it and the send to response
    public static final String JWT_HEADER = "Authorization";
}
