package com.ifpb.sistemaLogin.sistema.login.service;

import com.ifpb.sistemaLogin.sistema.login.dto.LoginDTO;
import com.ifpb.sistemaLogin.sistema.login.model.entities.Usuario;
import com.ifpb.sistemaLogin.sistema.login.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private UsuarioRepository repository;
    @Autowired
    @Qualifier("attempts")
    private RedisTemplate<String, Integer> templateAttemps;
    @Autowired
    @Qualifier("blocks")
    private RedisTemplate<String, String> templateBlocks;

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

    public Usuario save(Usuario usuario){
        repository.save(usuario);
        return usuario;
    }

    public boolean Autenticar(LoginDTO loginDTO) {


        Optional<Usuario> usuarioLogado = repository.findByLogin(loginDTO.getLogin());
        String keyBlock = "block-" + loginDTO.getLogin();
        if (usuarioLogado.isPresent()) {

            if (templateBlocks.opsForValue().get(keyBlock) == null) {
                if (usuarioLogado.get().getSenha().equals(loginDTO.getSenha())) {
                    return true;
                }
                String chave = "tentativas-" + loginDTO.getLogin();
                templateAttemps.opsForValue().increment(chave);
                Integer tentativas = templateAttemps.opsForValue().get(chave);
                if (tentativas != null && tentativas == 3) {
                    templateBlocks.opsForValue().set(keyBlock, "blocked");
                    templateBlocks.expire(keyBlock,Duration.ofSeconds(120));
                    templateAttemps.expire(chave, Duration.ofSeconds(120));
                }

            } else {
                System.out.println("Tá bloqueado paizão");
            }
        }
        return false;
    }


}
