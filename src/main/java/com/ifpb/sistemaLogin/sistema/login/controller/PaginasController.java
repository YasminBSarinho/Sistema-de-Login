package com.ifpb.sistemaLogin.sistema.login.controller;

import com.ifpb.sistemaLogin.sistema.login.model.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {
    @Autowired
    @Qualifier("Session")
    private RedisTemplate<String, Session> templateSession;

    @GetMapping("/")
    public String show(){
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(){
        System.out.println(templateSession.keys("*").isEmpty());
        if(buscarSession()){
            System.out.println("oi lgin");
            return "redirect:/home";
        }
        return "login.html";
    }

    @GetMapping("/cadastro")
    public String showCadastro(){
        System.out.println(templateSession.keys("*").isEmpty());
        if(buscarSession()){
            System.out.println("oi");
            return "redirect:/home";
        }
        return "cadastro.html";
    }

    @GetMapping("/home")
    public String showHome(){
        System.out.println(templateSession.keys("*").isEmpty());
        if (!buscarSession()){
            return "redirect:/login";
        }
        return "home.html";
    }

    public boolean buscarSession(){
        return !templateSession.keys("session").isEmpty();
    }
}
