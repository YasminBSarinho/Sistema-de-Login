package com.ifpb.sistemaLogin.sistema.login.controller;

import com.ifpb.sistemaLogin.sistema.login.model.entities.bd.Empreendimento;
import com.ifpb.sistemaLogin.sistema.login.service.EmpreendimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("home")
public class EmpreendimentosController {
    private EmpreendimentoService service;

    public EmpreendimentosController(EmpreendimentoService service) {
        this.service = service;
    }

    @GetMapping("/empreendimentos")
    public ResponseEntity<List<Empreendimento>> findAll(){
        List<Empreendimento> lista = service.findAll();
        return ResponseEntity.ok().body(lista);
    }
}
