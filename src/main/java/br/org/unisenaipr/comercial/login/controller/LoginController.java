package br.org.unisenaipr.comercial.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.org.unisenaipr.comercial.login.entity.Login;
import br.org.unisenaipr.comercial.login.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
    
	
	@RequestMapping("/access")
	public String loginIndex(Model theModel) {
		return "login/access";
	}
	
	@PostMapping("/authenticate")
    public String authenticate(
        @RequestParam("email") String email,
        @RequestParam("senha") String senha,
        RedirectAttributes redirectAttributes
    ) {
        Login login = loginService.findByEmailAndSenha(email, senha);

        if (login != null) {
            return "redirect:/index-home";
        } else {
            redirectAttributes.addFlashAttribute("error", "Credenciais inválidas");
            return "redirect:/login/access";
        }
    }
	
	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("login", new Login());
        return "login/register"; 
    }

    @PostMapping("/register")
    public String register(
        @RequestParam("nomeUser") String nomeUser,
        @RequestParam("emailUser") String emailUser,
        @RequestParam("senhaUser") String senhaUser,
        RedirectAttributes redirectAttributes
    ) {
        Login login = new Login(null, nomeUser, emailUser, senhaUser);
        loginService.save(login);
        redirectAttributes.addFlashAttribute("success", "Conta criada com sucesso");
        return "redirect:/login/access";
    }
	
}
