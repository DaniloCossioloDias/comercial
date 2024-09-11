package br.org.unisenaipr.comercial.subgrupo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.unisenaipr.comercial.subgrupo.entity.SubGrupo;

public interface SubGrupoRepository extends JpaRepository<SubGrupo, Long> {

	List<SubGrupo> findAll();
}
