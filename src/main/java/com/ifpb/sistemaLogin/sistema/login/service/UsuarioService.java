package com.ifpb.sistemaLogin.sistema.login.service;

import com.ifpb.sistemaLogin.sistema.login.model.entities.Usuario;
import com.ifpb.sistemaLogin.sistema.login.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario findById(UUID id){
        Optional<Usuario> optionalUsuario = repository.findById(id);
        //Aqui galera o optional return o usuario se existir, se não (orElse) null
        return optionalUsuario.orElse(null);
    }
    public void save(Usuario usuario){
        repository.save(usuario);
    }
}
