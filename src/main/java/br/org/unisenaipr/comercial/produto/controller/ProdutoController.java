package br.org.unisenaipr.comercial.produto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.unisenaipr.comercial.fabricante.entity.Fabricante;
import br.org.unisenaipr.comercial.fabricante.service.FabricanteService;
import br.org.unisenaipr.comercial.produto.entity.Produto;
import br.org.unisenaipr.comercial.produto.service.ProdutoService;
import br.org.unisenaipr.comercial.subgrupo.entity.SubGrupo;
import br.org.unisenaipr.comercial.subgrupo.service.SubGrupoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private SubGrupoService subGrupoService;
	
	@Autowired
	private FabricanteService fabricanteService;	
	
	@Autowired
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@RequestMapping("/produtoIndex")
	public String produtoIndex(Model theModel) {
		
		List<Produto> list = produtoService.findAll();
		
		theModel.addAttribute("produto", list);
		
		return "produto/list";
	}

	@RequestMapping("/produtoSave")
	public String produtoSave(Model theModel) {
		
        List<SubGrupo> subgrupos = subGrupoService.findAll();
        List<Fabricante> fabricantes = fabricanteService.findAll();  
        
        Produto produto = new Produto();
		
		theModel.addAttribute("subgrupos", subgrupos);
		theModel.addAttribute("fabricantes", fabricantes);
		theModel.addAttribute("produto", produto);
		
		return "produto/cadastro";
	}

	@PostMapping("/save-produto")
	public String saveProduto(@ModelAttribute("produto") Produto produto) {
		
		produtoService.saveProduto(produto);

		return "redirect:/produto/produtoIndex";
	}		
	
	@GetMapping("/produtoUpdate/{id}")
	public String produtoUpdate(@PathVariable("id") long id, Model theModel) {
		
        List<SubGrupo> subgrupos = subGrupoService.findAll();
        List<Fabricante> fabricantes = fabricanteService.findAll();  
        
        Produto produto = new Produto();
		
		theModel.addAttribute("subgrupos", subgrupos);
		theModel.addAttribute("fabricantes", fabricantes);
		theModel.addAttribute("produto", produto);
		
		return "subgrupo/alterar";
	}

	@PostMapping("/alterar-produto/{id}")
	public String updateProduto(@ModelAttribute Produto produto) {
		
		produtoService.updateProduto(produto);
		
		return "redirect:/produto/produtoIndex";
	}	

	@GetMapping("/deletar-produto/{id}")
	public String deleteProduto(@PathVariable("id") long id) {
		
		Produto produto = new Produto();
		
		produto.setId(id);
		
		produtoService.deleteProduto(id);
		
		return "redirect:/produto/produtoIndex";
	}	
	
}
