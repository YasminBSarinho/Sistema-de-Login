package com.ifpb.sistemaLogin.sistema.login.controller;

import com.ifpb.sistemaLogin.sistema.login.model.entities.Usuario;
import com.ifpb.sistemaLogin.sistema.login.service.UsuarioService;
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

    /*
    Aqui pensei em usarmos o ResponseEntity. Como o nome propõe
    é um entidade de resposta. Ela responde a requisição GET que é feita
    Ela vai responder jogando um Codigo 200 OK e no body
    do json vai ir junto objeto Usuario. Ainda requer umas validações
    (por exemplo se não achar o usuário).
    Vocês podem usar o postman pra acessar: localhost:8080/usuarios/todos
    Ou o próprio navegador também tem o swagger se não me engano.
     */
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

}