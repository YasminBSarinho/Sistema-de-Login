package com.ifpb.sistemaLogin.sistema.login;

import com.ifpb.sistemaLogin.sistema.login.model.entities.Usuario;
import com.ifpb.sistemaLogin.sistema.login.service.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SistemaLoginApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(SistemaLoginApplication.class, args);

		// ABAIXO UM PEQUENO TESTE

		Usuario usuario = new Usuario(null, "Henrique", "MeuLogin", "MinhaSenha", "@Email.com");
		run.getBean(UsuarioService.class).save(usuario);
		System.out.println(usuario.getId());
	}

}
