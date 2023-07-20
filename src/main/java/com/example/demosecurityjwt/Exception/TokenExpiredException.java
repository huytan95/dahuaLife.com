package com.example.demosecurityjwt.Exception;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(String messenger) {
        super(messenger);
    }
}
