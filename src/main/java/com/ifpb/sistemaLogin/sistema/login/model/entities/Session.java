package com.ifpb.sistemaLogin.sistema.login.model.entities;

import jakarta.persistence.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@RedisHash(value="Session",timeToLive = 30L)
public class Session implements Serializable {
    @Id
    private String id;
    private Usuario usuario;

    public Session() {
    }

    public Session(Usuario usuario) {
        this.usuario = usuario;
    }

    public Session(String id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
