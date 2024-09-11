package br.org.unisenaipr.comercial.subgrupo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.org.unisenaipr.comercial.grupo.entity.Grupo;
import br.org.unisenaipr.comercial.grupo.service.GrupoService;
import br.org.unisenaipr.comercial.produto.entity.Produto;
import br.org.unisenaipr.comercial.subgrupo.entity.SubGrupo;
import br.org.unisenaipr.comercial.subgrupo.service.SubGrupoService;

@Controller
@RequestMapping("/subgrupo")
public class SubGrupoController {

    @Autowired
    private SubGrupoService subGrupoService;

    @Autowired
    private GrupoService grupoService;

    @RequestMapping("/subGrupoIndex")
    public String subGrupoIndex(Model theModel) {
        List<SubGrupo> list = subGrupoService.findAll();
        theModel.addAttribute("subgrupo", list);
        return "subgrupo/list";
    }

    @RequestMapping("/subgrupoSave")
    public String subGrupoSave(Model theModel) {
        List<Grupo> grupos = grupoService.findAll();
        SubGrupo subgrupo = new SubGrupo();
        theModel.addAttribute("grupos", grupos);
        theModel.addAttribute("subgrupo", subgrupo);
        return "subgrupo/cadastro";
    }

    @PostMapping("/save-subgrupo")
    public String saveSubGrupo(@ModelAttribute("subgrupo") SubGrupo subgrupo) {
        subGrupoService.saveSubGrupo(subgrupo);
        return "redirect:/subgrupo/subGrupoIndex";
    }

    @GetMapping("/subgrupoUpdate/{id}")
    public String subGrupoUpdate(@PathVariable("id") long id, Model theModel) {
        List<Grupo> grupos = grupoService.findAll();
        SubGrupo subgrupo = subGrupoService.findId(id);
        theModel.addAttribute("grupos", grupos);
        theModel.addAttribute("subgrupo", subgrupo);
        return "subgrupo/alterar";
    }

    @PostMapping("/alterar-subgrupo/{id}")
    public String updateSubGrupo(@PathVariable("id") long id, @ModelAttribute SubGrupo subGrupoAtualizado) {
        SubGrupo subGrupoExistente = subGrupoService.findId(id);

        if (subGrupoExistente != null) {

            subGrupoExistente.setNomeSubGrupo(subGrupoAtualizado.getNomeSubGrupo());

            List<Produto> produtosExistentes = subGrupoExistente.getProdutos();
            if (produtosExistentes == null) {
                produtosExistentes = new ArrayList<>();
                subGrupoExistente.setProdutos(produtosExistentes);
            }

            List<Produto> produtosAtualizados = subGrupoAtualizado.getProdutos();
            if (produtosAtualizados == null) {
                produtosAtualizados = new ArrayList<>();
            }

            for (Produto produtoAtualizado : produtosAtualizados) {
                if (produtoAtualizado.getId() == null) {

                    produtoAtualizado.setSubGrupo(subGrupoExistente);
                    produtosExistentes.add(produtoAtualizado);
                } else {
                    boolean found = false;
                    for (Produto produtoExistente : produtosExistentes) {
                        if (produtoExistente.getId().equals(produtoAtualizado.getId())) {
                            produtoExistente.setNomeProduto(produtoAtualizado.getNomeProduto());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {

                        produtoAtualizado.setSubGrupo(subGrupoExistente);
                        produtosExistentes.add(produtoAtualizado);
                    }
                }
            }

            List<Produto> produtosParaRemover = new ArrayList<>();
            for (Produto produtoExistente : produtosExistentes) {
                boolean found = false;
                for (Produto produtoAtualizado : produtosAtualizados) {
                    if (produtoAtualizado.getId().equals(produtoExistente.getId())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    produtosParaRemover.add(produtoExistente);
                }
            }
            produtosExistentes.removeAll(produtosParaRemover);

            subGrupoService.saveSubGrupo(subGrupoExistente);
        }

        return "redirect:/subgrupo/subGrupoIndex";
    }


    @GetMapping("/deletar-subgrupo/{id}")
    public String deleteSubGrupo(@PathVariable("id") long id) {
        subGrupoService.deleteSubGrupoById(id);
        return "redirect:/subgrupo/subGrupoIndex";
    }
}
