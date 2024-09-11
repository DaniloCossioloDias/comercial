package br.org.unisenaipr.comercial.subgrupo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.unisenaipr.comercial.subgrupo.entity.SubGrupo;
import br.org.unisenaipr.comercial.subgrupo.repositorio.SubGrupoRepository;

@Service
public class SubGrupoService {

    private SubGrupoRepository subGrupoRepository;

    @Autowired
    public SubGrupoService(SubGrupoRepository subGrupoRepository) {
        this.subGrupoRepository = subGrupoRepository;
    }

    public List<SubGrupo> findAll() {
        return subGrupoRepository.findAll();
    }

    public void saveSubGrupo(SubGrupo subGrupo) {
        subGrupoRepository.save(subGrupo);
    }

    public void updateSubGrupo(SubGrupo subGrupo) {
        subGrupoRepository.save(subGrupo);
    }

    public void deleteSubGrupoById(Long id) {
        subGrupoRepository.deleteById(id); 
    }

    public SubGrupo findId(long id) {
        Optional<SubGrupo> objSubGrupo = subGrupoRepository.findById(id);
        return objSubGrupo.orElse(null); 
    }
}
