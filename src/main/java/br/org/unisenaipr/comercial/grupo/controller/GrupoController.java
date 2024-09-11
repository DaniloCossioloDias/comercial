package br.org.unisenaipr.comercial.grupo.controller;

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
import br.org.unisenaipr.comercial.subgrupo.entity.SubGrupo;

@Controller
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @RequestMapping("/grupoIndex")
    public String grupoIndex(Model theModel) {
        List<Grupo> list = grupoService.findAll();
        theModel.addAttribute("grupo", list);
        return "grupo/list";
    }

    @RequestMapping("/grupoSave")
    public String grupoSave(Model theModel) {
        Grupo grupo = new Grupo();
        theModel.addAttribute("grupo", grupo);
        return "grupo/cadastro";
    }

    @PostMapping("/save-grupo")
    public String saveGrupo(@ModelAttribute("grupo") Grupo grupo) {
        grupoService.saveGrupo(grupo);
        return "redirect:/grupo/grupoIndex";
    }

    @GetMapping("/grupoUpdate/{id}")
    public String grupoUpdate(@PathVariable("id") long id, Model theModel) {
        Grupo grupo = grupoService.findId(id);
        theModel.addAttribute("grupo", grupo);
        return "grupo/alterar";
    }

    @PostMapping("/alterar-grupo/{id}")
    public String updateGrupo(@PathVariable("id") long id, @ModelAttribute Grupo grupoAtualizado) {
        Grupo grupoExistente = grupoService.findId(id);

        if (grupoExistente != null) {

            grupoExistente.setNomeGrupo(grupoAtualizado.getNomeGrupo());

            List<SubGrupo> subGruposExistentes = grupoExistente.getSubGrupos();
            if (subGruposExistentes == null) {
                subGruposExistentes = new ArrayList<>();
                grupoExistente.setSubGrupos(subGruposExistentes);
            }

            List<SubGrupo> subGruposAtualizados = grupoAtualizado.getSubGrupos();
            if (subGruposAtualizados == null) {
                subGruposAtualizados = new ArrayList<>();
            }

            for (SubGrupo subGrupoAtualizado : subGruposAtualizados) {
                if (subGrupoAtualizado.getId() == null) {

                    subGrupoAtualizado.setGrupo(grupoExistente);
                    subGruposExistentes.add(subGrupoAtualizado);
                } else {
                    boolean found = false;
                    for (SubGrupo subGrupoExistente : subGruposExistentes) {
                        if (subGrupoExistente.getId().equals(subGrupoAtualizado.getId())) {
                            subGrupoExistente.setNomeSubGrupo(subGrupoAtualizado.getNomeSubGrupo());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {

                        subGrupoAtualizado.setGrupo(grupoExistente);
                        subGruposExistentes.add(subGrupoAtualizado);
                    }
                }
            }

            List<SubGrupo> subGruposParaRemover = new ArrayList<>();
            for (SubGrupo subGrupoExistente : subGruposExistentes) {
                boolean found = false;
                for (SubGrupo subGrupoAtualizado : subGruposAtualizados) {
                    if (subGrupoAtualizado.getId().equals(subGrupoExistente.getId())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    subGruposParaRemover.add(subGrupoExistente);
                }
            }
            subGruposExistentes.removeAll(subGruposParaRemover);

            grupoService.saveGrupo(grupoExistente);
        }

        return "redirect:/grupo/grupoIndex";
    }



    @GetMapping("/deletar-grupo/{id}")
    public String deleteGrupo(@PathVariable("id") long id) {
        grupoService.deleteGrupoById(id);
        return "redirect:/grupo/grupoIndex";
    }
}
