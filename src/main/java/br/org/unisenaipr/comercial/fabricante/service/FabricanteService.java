package br.org.unisenaipr.comercial.fabricante.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.fabricante.repositorio.FabricanteRepository;

@Service
public class FabricanteService {

    private final FabricanteRepository fabricanteRepository;

    @Autowired
    public FabricanteService(FabricanteRepository fabricanteRepository) {
        this.fabricanteRepository = fabricanteRepository;
    }

    public void saveFabricante(Fabricante fabricante) {
        fabricanteRepository.save(fabricante);
    }

    public void updateFabricante(Fabricante fabricante) {
        if (fabricanteRepository.existsById(fabricante.getId())) {
            fabricanteRepository.save(fabricante);
        }
    }

    public void deleteFabricante(Fabricante fabricante) {
        fabricanteRepository.delete(fabricante);
    }

    public List<Fabricante> findAll() {
        return fabricanteRepository.findAll();
    }

    public Fabricante findId(long id) {
        Optional<Fabricante> objFabricante = fabricanteRepository.findById(id);
        return objFabricante.orElse(null);
    }
}
