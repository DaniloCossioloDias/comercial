package br.org.unisenaipr.comercial.venda.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.unisenaipr.comercial.produto.entity.Produto;
import br.org.unisenaipr.comercial.produto.service.ProdutoService;
import br.org.unisenaipr.comercial.venda.entity.Venda;
import br.org.unisenaipr.comercial.venda.service.VendasService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendasService vendasService;

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping("/vendasIndex")
    public String produtoIndex(Model theModel) {        
        List<Venda> list = vendasService.findAll();
        theModel.addAttribute("vendas", list);
        return "vendas/list";
    }
    
    @RequestMapping("/vendasSave")
    public String vendasSave(Model theModel) {        
        List<Produto> produtos = produtoService.findAll();  
        Venda venda = new Venda();
        venda.setDataVenda(new Date());
        theModel.addAttribute("produtos", produtos);
        theModel.addAttribute("venda", venda);
        theModel.addAttribute("vendaprodutos", new ArrayList<Produto>());
        return "vendas/cadastro";
    }    

    @PostMapping("/incluirVendas")
    public String incluirVendas(@ModelAttribute("venda") Venda vendaProduto, HttpSession session, Model theModel) {        
        List<Produto> produtos = produtoService.findAll();
        theModel.addAttribute("produtos", produtos);

        Produto p = produtoService.findById(vendaProduto.getProdutos().get(0).getId());
        if (p == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        double soma = vendaProduto.getQtd() * p.getPrecoVenda();
        p.setQtdVenda(vendaProduto.getQtd());
        p.setVlrVenda(soma);

        // Atualiza a lista de produtos na sessão
        List<Produto> prod = (List<Produto>) session.getAttribute("vendaprodutos");
        if (prod == null) {
            prod = new ArrayList<>();
        }
        prod.add(p);
        session.setAttribute("vendaprodutos", prod);

        // Atualiza o valor total na sessão
        Double vlrTotal = (Double) session.getAttribute("vlrTotal");
        if (vlrTotal == null) {
            vlrTotal = soma;
        } else {
            vlrTotal += soma;
        }
        session.setAttribute("vlrTotal", vlrTotal);

        theModel.addAttribute("venda", new Venda()); // Resetar o objeto venda
        theModel.addAttribute("vlrTotal", vlrTotal);
        theModel.addAttribute("vendaprodutos", prod);
        
        return "vendas/cadastro";
    }

    @PostMapping("/save-vendas")
    public String saveVenda(HttpSession session) {                
        List<Produto> produtos = (List<Produto>) session.getAttribute("vendaprodutos");
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum produto foi adicionado à venda.");
        }

        Venda venda = new Venda();
        venda.setQtd(produtos.size());
        venda.setDataVenda(new Date());
        double vlrTotal = 0.00D;

        for (Produto p : produtos) {
            vlrTotal += p.getVlrVenda();
        }
        venda.setVlrTotal(vlrTotal);
        venda.setProdutos(produtos);
        
        vendasService.saveVenda(venda);

        // Limpa a sessão após salvar a venda
        session.removeAttribute("vendaprodutos");
        session.removeAttribute("vlrTotal");

        return "redirect:/vendas/vendasIndex";
    }   
}
