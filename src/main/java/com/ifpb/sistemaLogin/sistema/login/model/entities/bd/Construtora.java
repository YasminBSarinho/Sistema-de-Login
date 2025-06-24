package com.ifpb.sistemaLogin.sistema.login.model.entities.bd;

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
public class Construtora implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column (nullable = false, length = 14)
    private String cnpj;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_empreendimento")
    private List<Empreendimento> empreendimentos;

    public Construtora(String name, String cnpj) {
        this.name = name;
        this.cnpj = cnpj;
    }
}
