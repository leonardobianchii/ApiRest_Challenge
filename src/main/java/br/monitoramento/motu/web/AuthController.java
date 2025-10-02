package br.monitoramento.motu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    // PÁGINA DE LOGIN
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    // PÁGINA DE ACESSO NEGADO
    @GetMapping("/403")
    public String accessDenied() {
        return "errors/403";
    }
}
