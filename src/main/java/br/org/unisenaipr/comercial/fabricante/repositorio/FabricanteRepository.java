package br.org.unisenaipr.comercial.fabricante.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;

@Repository("fabricanteRepository")
public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {
	
	List<Fabricante> findAll();

}
