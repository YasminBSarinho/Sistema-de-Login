package com.ifpb.sistemaLogin.sistema.login.model.entities.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ifpb.sistemaLogin.sistema.login.model.enums.Regiao;
import com.ifpb.sistemaLogin.sistema.login.model.enums.UF;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;



@Entity
@Getter
@Setter
@NoArgsConstructor
public class Municipio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private UF uf;

    @Enumerated(EnumType.STRING)
    private Regiao regiao;

    @OneToMany(mappedBy = "municipio")
    @JsonIgnore
    private List<Endereco> endereco;

    public Municipio(String nome, UF uf, Regiao regiao) {
        this.nome = nome;
        this.uf = uf;
        this.regiao = regiao;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", uf=" + uf +
                ", regiao=" + regiao +
                '}';
    }
}
