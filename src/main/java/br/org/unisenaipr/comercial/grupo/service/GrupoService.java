package br.org.unisenaipr.comercial.grupo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.org.unisenaipr.comercial.grupo.entity.Grupo;
import br.org.unisenaipr.comercial.grupo.repositorio.GrupoRepository;

@Service
public class GrupoService {

	private final GrupoRepository grupoRepository;
	
	public GrupoService(GrupoRepository grupoRepository) {
		this.grupoRepository = grupoRepository;
	}

	public List<Grupo> findAll() {
		return grupoRepository.findAll();
	}
	
	public void saveGrupo(Grupo grupo) {
		grupoRepository.save(grupo);
	}

	public void updateGrupo(Grupo grupo) {
	    Grupo existingGrupo = grupoRepository.findById(grupo.getId()).orElse(null);
	    if (existingGrupo != null) {
	        existingGrupo.setNomeGrupo(grupo.getNomeGrupo());
	        existingGrupo.setSubGrupos(grupo.getSubGrupos());
	        grupoRepository.save(existingGrupo);
	    }
	}
	
	public void deleteGrupoById(long id) {
	    Grupo grupo = grupoRepository.findById(id).orElse(null);
	    if (grupo != null) {
	        grupo.getSubGrupos().clear();
	        grupoRepository.delete(grupo);
	    }
	}

	public Grupo findId(long id) {
		Optional<Grupo> objGrupo = grupoRepository.findById(id);
		return objGrupo.orElse(null);  
	}
}
