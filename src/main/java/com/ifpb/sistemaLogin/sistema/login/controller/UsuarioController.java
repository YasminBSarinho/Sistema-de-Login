package com.ifpb.sistemaLogin.sistema.login.controller;

import com.ifpb.sistemaLogin.sistema.login.dto.LoginDTO;

import com.ifpb.sistemaLogin.sistema.login.model.entities.Usuario;
import com.ifpb.sistemaLogin.sistema.login.service.UsuarioService;
import com.ifpb.sistemaLogin.sistema.login.service.exception.LoginBlockedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> listaDeUsuarios = service.findAll();
        return ResponseEntity.ok().body(listaDeUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable UUID id){
        Usuario usuario = service.findById(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/cadastrar")
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        service.save(usuario);
        return usuario;
    }

    @PostMapping("/logar")
    public ResponseEntity<String> logar(@RequestBody LoginDTO loginDTO) {
        try {
            if (service.Autenticar(loginDTO)) {
                return ResponseEntity.ok("Autenticado com sucesso");
            } else {
                return ResponseEntity.status(401).body("Login ou senha incorretos");
            }
        }catch (LoginBlockedException e){
            return ResponseEntity.status(429).body(e.getMessage());
        }

    }
}