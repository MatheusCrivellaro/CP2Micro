package com.example.demo.controller;

import com.example.demo.model.Massa;
import com.example.demo.repository.MassaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/massa")
public class MassaController {

    @Autowired
    private MassaRepository repository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", repository.findAllByAtivoTrue());
        return "listaMassa";
    }

    @GetMapping("/busca")
    public String filtraPorNome(Model model, @RequestParam String nome) {
        if (nome != null && !nome.isEmpty())
            model.addAttribute("lista", repository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome));
        else
            model.addAttribute("lista", repository.findAllByAtivoTrue());
        return "listaMassa";
    }

    @PostMapping
    public String save(Massa massa) {
        repository.save(massa);
        return "redirect:/massa";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastroMassa";
    }

    @PostMapping("/deletar")
    public String deletar(@RequestParam Long id) {
        var entity = repository.findById(id).orElseThrow();
        entity.setAtivo(false);
        repository.save(entity);
        return "redirect:/massa";
    }

    @PostMapping("/atualizar")
    public String atualizar(@RequestParam int id, Model model) {
        model.addAttribute("id", id);
        return "atualizarMassa";
    }

    @GetMapping("/visualizar")
    public String visualizar(@RequestParam int id, Model model) {
        model.addAttribute("massa", repository.findById((long) id).orElseThrow());
        return "visualizarMassa";
    }

    @PostMapping("/update")
    public String update(@RequestParam int id, @RequestParam String nome, @RequestParam String descricao) {
        var massa = repository.findById((long) id).orElseThrow();
        if (nome == null || nome.isEmpty())
            nome = massa.getNome();
        if (descricao == null || descricao.isEmpty())
            descricao = massa.getDescricao();
        repository.save(new Massa((long) id, nome, descricao,true, massa.getPasteis()));
        return "redirect:/massa";
    }
}
