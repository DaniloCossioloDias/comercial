package br.org.unisenaipr.comercial.login.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.unisenaipr.comercial.login.entity.Login;

@Repository("loginRepository")
public interface LoginRepository extends JpaRepository<Login, Long>{
	
	List<Login> findAll();
	
	Login findByEmailUserAndSenhaUser(String emailUser, String senhaUser);
}
