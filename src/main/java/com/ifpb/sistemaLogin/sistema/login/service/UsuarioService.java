package com.ifpb.sistemaLogin.sistema.login.service;

import com.ifpb.sistemaLogin.sistema.login.dto.LoginDTO;
import com.ifpb.sistemaLogin.sistema.login.model.entities.Session;
import com.ifpb.sistemaLogin.sistema.login.model.entities.Usuario;
import com.ifpb.sistemaLogin.sistema.login.repository.UsuarioRepository;
import com.ifpb.sistemaLogin.sistema.login.service.exception.LoginBlockedException;
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
    private EmailService emailService;

    @Autowired
    @Qualifier("attempts")
    private RedisTemplate<String, Integer> templateAttemps;
    @Autowired
    @Qualifier("Session")
    private RedisTemplate<String, Session> templateSession;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario findById(UUID id){
        Optional<Usuario> optionalUsuario = repository.findById(id);
        return optionalUsuario.orElse(null);
    }

    public Usuario save(Usuario usuario){
        Optional<Usuario> existente = repository.findByLogin(usuario.getLogin());
        if (existente.isEmpty()) {
            return repository.save(usuario);
        }
        return null;
    }

    public boolean Autenticar(LoginDTO loginDTO) throws LoginBlockedException {
        if (isBloqueado(loginDTO)) {
            throw  new LoginBlockedException("O usuário fez 3 tentativas, bloqueado por 1 minutos");
        }

        Optional<Usuario> usuarioLogado = repository.findByLogin(loginDTO.getLogin());

        if (usuarioLogado.isPresent()) {
            return this.isPasswordValid(loginDTO, usuarioLogado.get());
        }

        return false;
    }
    private Boolean isPasswordValid(LoginDTO loginDTO, Usuario usuario){
        String senhaDigita = loginDTO.getSenha();
        String keyTentativas = getKeyTentativas(loginDTO);
        if (senhaDigita.equals(usuario.getSenha())) {
            templateAttemps.delete(templateAttemps.keys(keyTentativas));
            templateSession.opsForValue().set("session", new Session(usuario));
            templateSession.expire("session", Duration.ofSeconds(20));
            return true ;
        }

        Long quantidade = templateAttemps.opsForValue().increment(keyTentativas);
        if(quantidade >= 3){
            bloquear(loginDTO);
        }
        return false;
    }

    private boolean isBloqueado(LoginDTO loginDTO){
        String keyTentativas = getKeyTentativas(loginDTO);
        Integer tentativas = templateAttemps.opsForValue().get(keyTentativas);
        return tentativas != null && tentativas == 3;
    }

    private void bloquear (LoginDTO loginDTO){
        templateAttemps.expire(getKeyTentativas(loginDTO),Duration.ofSeconds(100));
        String assunto = "TENTATIVA DE LOGIN FORÇADO";
        String texto = "Houve uma tenativa de login forçado na sua conta";
        Optional<Usuario> usuario = usuarioRepository.findByLogin(loginDTO.getLogin());
        if(usuario.isPresent()){
            emailService.enviarEmail(usuario.get().getEmail(), assunto,texto);
        }
    }
    private String getKeyTentativas(LoginDTO loginDTO){
        return  "tentativas-" + loginDTO.getLogin();
    }

}
