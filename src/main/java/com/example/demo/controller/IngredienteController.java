package com.grupoa.pastelaria.controller;

import com.grupoa.pastelaria.domain.model.Ingrediente;
import com.grupoa.pastelaria.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ingrediente")
public class IngredienteController {

    @Autowired
    private IngredienteRepository repository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", repository.findAllByAtivoTrue());
        return "listaIngrediente";
    }

    @GetMapping("/busca")
    public String filtraPorNome(Model model, @RequestParam String nome) {
        if (nome != null && !nome.isEmpty())
            model.addAttribute("lista", repository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome));
        else
            model.addAttribute("lista", repository.findAllByAtivoTrue());
        return "listaIngrediente";
    }

    @PostMapping
    public String salvar(Ingrediente ingrediente) {
        ingrediente.setAtivo(true);
        repository.save(ingrediente);
        return "redirect:/ingrediente";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastroIngrediente";
    }

    @PostMapping("/deletar")
    public String deletar(@RequestParam Long id) {
        var entity = repository.findById(id).orElseThrow();
        entity.setAtivo(false);
        repository.save(entity);
        return "redirect:/ingrediente";
    }

    @PostMapping("/atualizar")
    public String atualizar(@RequestParam int id, Model model) {
        model.addAttribute("id", id);
        return "atualizarIngrediente";
    }

    @GetMapping("/visualizar")
    public String visualizar(@RequestParam int id, Model model) {
        model.addAttribute("ingrediente", repository.findById((long) id).orElseThrow());
        return "visualizarIngrediente";
    }

    @PostMapping("/update")
    public String update(@RequestParam int id, @RequestParam String nome, @RequestParam String descricao) {
        var ingrediente = repository.findById((long) id).orElseThrow();
        if (nome == null || nome.isEmpty())
            nome = ingrediente.getNome();
        if (descricao == null || descricao.isEmpty())
            descricao = ingrediente.getDescricao();
        repository.save(new Ingrediente((long) id, nome, descricao, true, ingrediente.getPasteis()));
        return "redirect:/ingrediente";
    }
}
