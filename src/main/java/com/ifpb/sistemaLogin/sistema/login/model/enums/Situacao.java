package com.ifpb.sistemaLogin.sistema.login.model.enums;

public enum Situacao {
    CONCLUIDO("CONCLUÍDO"),
    CANCELADO("DISTRATADO/CANCELADO"),
    NAO_CONCLUIDO("NÃO CONCLUÍDO");


    private String desc;

    private Situacao(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
