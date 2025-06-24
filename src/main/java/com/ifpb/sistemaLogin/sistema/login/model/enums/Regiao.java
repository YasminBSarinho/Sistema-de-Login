package com.ifpb.sistemaLogin.sistema.login.model.enums;

public enum Regiao {
    SUL("Sul"),
    CENTRO_OESTE("Centro-Oeste"),
    NORDESTE("Nordeste"),
    NORTE("Norte"),
    SUDESTE("Sudeste");

    private final String nome;

    private Regiao(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return nome;
    }
}
