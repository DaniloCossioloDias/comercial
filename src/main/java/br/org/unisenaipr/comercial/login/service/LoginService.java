package br.org.unisenaipr.comercial.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.unisenaipr.comercial.login.entity.Login;
import br.org.unisenaipr.comercial.login.repositorio.LoginRepository;

@Service
public class LoginService {
	
	private LoginRepository loginRepository;
	
	
	
	
	@Autowired
	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	public List<Login> findAll() {
		return loginRepository.findAll();
	}
	
	public Login findByEmailAndSenha(String email, String senha) {
        return loginRepository.findByEmailUserAndSenhaUser(email, senha);
    }
	
	public void save(Login login) {
        loginRepository.save(login);
    }
}
