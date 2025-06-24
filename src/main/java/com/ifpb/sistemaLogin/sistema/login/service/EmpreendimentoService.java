package com.ifpb.sistemaLogin.sistema.login.service;

import com.ifpb.sistemaLogin.sistema.login.model.entities.bd.Empreendimento;
import com.ifpb.sistemaLogin.sistema.login.repository.EmpreendimentoRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpreendimentoService {

    private EmpreendimentoRepository empreendimentoRepository;

    public EmpreendimentoService(EmpreendimentoRepository empreendimentoRepository) {
        this.empreendimentoRepository = empreendimentoRepository;
    }
    @Cacheable("empreendimentos")
    public List<Empreendimento> findAll(){
        return empreendimentoRepository.findAll();
    }
}
