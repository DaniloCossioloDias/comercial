package br.org.unisenaipr.comercial.infra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

	
@Controller
public class IndexController {

	
    @GetMapping("/")
    public String index() {
        return "login/register";
    }
    
    @GetMapping("/index-home")
    public String indexHome() {
        return "index";
    }
}
