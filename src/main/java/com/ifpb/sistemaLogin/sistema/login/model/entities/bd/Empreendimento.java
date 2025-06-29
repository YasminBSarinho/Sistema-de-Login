package com.ifpb.sistemaLogin.sistema.login.model.entities.bd;

import com.ifpb.sistemaLogin.sistema.login.model.enums.Modalidade;
import com.ifpb.sistemaLogin.sistema.login.model.enums.Situacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Empreendimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;
    @Enumerated(EnumType.STRING)
    private Situacao situacao;
    private LocalDate dataAssinatura;
    @Column(nullable = false,length = 8)
    private int codigoOperacao;
    private BigDecimal valorDesembolsado;
    private BigDecimal valorTotalContratado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_endereco")
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_dadosUH")
    private DadosUH dadosUH;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_construtora")
    private Construtora construtora;

    public Empreendimento(String nome, Situacao situacao, LocalDate dataAssinatura, int codigoOperacao, BigDecimal valorDesembolsado, BigDecimal valorTotalContratado, Endereco endereco, Modalidade modalidade, DadosUH dadosUH, Construtora construtora) {
        this.nome = nome;
        this.situacao = situacao;
        this.dataAssinatura = dataAssinatura;
        this.codigoOperacao = codigoOperacao;
        this.valorDesembolsado = valorDesembolsado;
        this.valorTotalContratado = valorTotalContratado;
        this.endereco = endereco;
        this.modalidade = modalidade;
        this.dadosUH = dadosUH;
        this.construtora = construtora;
    }
}
