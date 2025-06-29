package com.ifpb.sistemaLogin.sistema.login.model.entities.bd;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String logradouro;

    private String tipoLogradouro;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String numero;

    private String texto;

    @Column(nullable = false)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "fk_municipio")
    @JsonIgnore
    private Municipio municipio;

    @OneToMany(mappedBy = "endereco")
    @JsonIgnore
    private List<Empreendimento> empreendimentos;

    public Endereco(String logradouro,String tipo, String bairro, String numero, String texto, String cep, Municipio municipio) {
        this.logradouro = logradouro;
        this.tipoLogradouro = tipo;
        this.bairro = bairro;
        this.numero = numero;
        this.texto = texto;
        this.cep = cep;
        this.municipio = municipio;
    }
}
