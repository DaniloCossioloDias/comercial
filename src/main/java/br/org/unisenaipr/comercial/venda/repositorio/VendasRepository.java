package br.org.unisenaipr.comercial.venda.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.unisenaipr.comercial.venda.entity.Venda;

public interface VendasRepository extends JpaRepository<Venda, Long> {
	
	List<Venda> findAll();

}
