package com.ifpb.sistemaLogin.sistema.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void enviarEmail(String para, String assunto, String texto) {

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom("projetologinbd@gmail.com");
        mensagem.setTo(para);
        mensagem.setSubject(assunto);
        mensagem.setText(texto);
        mailSender.send(mensagem);

    }
}
