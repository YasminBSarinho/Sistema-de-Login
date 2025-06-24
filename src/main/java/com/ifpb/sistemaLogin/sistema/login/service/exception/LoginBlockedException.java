package com.ifpb.sistemaLogin.sistema.login.service.exception;

public class LoginBlockedException extends RuntimeException {
    public LoginBlockedException(String message) {
        super(message);
    }
}
