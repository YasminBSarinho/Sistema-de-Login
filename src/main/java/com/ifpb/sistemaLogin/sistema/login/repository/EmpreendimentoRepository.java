package com.ifpb.sistemaLogin.sistema.login.repository;

import com.ifpb.sistemaLogin.sistema.login.model.entities.bd.Empreendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, UUID> {
}
