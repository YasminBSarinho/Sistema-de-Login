package com.ifpb.sistemaLogin.sistema.login.dto;

public class LoginDTO {
    private String login;
    private String senha;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
