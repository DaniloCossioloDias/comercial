package br.org.unisenaipr.comercial.grupo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.unisenaipr.comercial.grupo.entity.Grupo;

@Repository("grupoRepository")
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	
	List<Grupo> findAll();

}
