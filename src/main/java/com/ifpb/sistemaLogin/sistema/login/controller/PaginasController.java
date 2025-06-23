package com.ifpb.sistemaLogin.sistema.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaginasController {
    @GetMapping("/")
    public String show(){
        return "redirect:login";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login.html";
    }

    @GetMapping("/cadastro")
    public String showCadastro(){
        return "cadastro.html";
    }

    @GetMapping("/home")
    public String showHome(){
        return "home.html";
    }
}
