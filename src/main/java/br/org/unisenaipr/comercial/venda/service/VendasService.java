package br.org.unisenaipr.comercial.venda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.unisenaipr.comercial.venda.entity.Venda;
import br.org.unisenaipr.comercial.venda.repositorio.VendasRepository;

@Service
public class VendasService {
	
	private VendasRepository vendasRepository;

	@Autowired
	public VendasService(VendasRepository vendasRepository) {
		this.vendasRepository = vendasRepository;
	}
	
	public List<Venda> findAll() {
		return vendasRepository.findAll();
	}

	public void saveVenda(Venda venda) {
		vendasRepository.save(venda);
	}

	public void updateVenda(Venda venda) {
		vendasRepository.save(venda);
	}
	
	public void deleteVenda(Venda venda) {
		vendasRepository.delete(venda);
	}

	public Venda findId(long id) {
		
		Optional<Venda> objVendas = vendasRepository.findById(id);
		Venda venda = objVendas.get();
		
		return venda;
	}
}
