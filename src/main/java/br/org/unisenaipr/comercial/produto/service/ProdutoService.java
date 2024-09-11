package br.org.unisenaipr.comercial.produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.unisenaipr.comercial.produto.entity.Produto;
import br.org.unisenaipr.comercial.produto.repositorio.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public void saveProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    @Transactional
    public void updateProduto(Produto produto) {
        if (produto.getId() != null && produtoRepository.existsById(produto.getId())) {
            produtoRepository.save(produto);
        } else {
            throw new IllegalArgumentException("Produto não encontrado para atualização.");
        }
    }

    @Transactional
    public void deleteProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Produto não encontrado para exclusão.");
        }
    }

    public Produto findById(Long id) {
        Optional<Produto> objProduto = produtoRepository.findById(id);
        if (objProduto.isPresent()) {
            return objProduto.get();
        } else {
            throw new IllegalArgumentException("Produto não encontrado.");
        }
    }
}
