package com.grupoa.pastelaria.controller;

import com.grupoa.pastelaria.domain.model.Pastel;
import com.grupoa.pastelaria.repository.IngredienteRepository;
import com.grupoa.pastelaria.repository.MassaRepository;
import com.grupoa.pastelaria.repository.PastelRepository;
import com.grupoa.pastelaria.service.PastelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/pastel")
public class PastelController {

    @Autowired
    private PastelService service;

    @Autowired
    private PastelRepository repository;

    @Autowired
    private MassaRepository massaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", repository.findAllByAtivoTrue());

        model.addAttribute("listaIngrediente", ingredienteRepository.findAllByAtivoTrue());
        model.addAttribute("listaMassa", massaRepository.findAllByAtivoTrue());
        return "listaPastel";
    }

    @GetMapping("/busca")
    public String filtraPorNome(Model model, @RequestParam String nome) {
        if (nome != null && !nome.isEmpty())
            model.addAttribute("lista", repository.findByNomeContainingIgnoreCaseAndAtivoTrue(nome));
        else
            model.addAttribute("lista", repository.findAllByAtivoTrue());

        model.addAttribute("listaIngrediente", ingredienteRepository.findAllByAtivoTrue());
        model.addAttribute("listaMassa", massaRepository.findAllByAtivoTrue());
        return "listaPastel";
    }

    @GetMapping("/buscaMassa")
    public String filtraPorMassa(Model model, @RequestParam String massa) {
        model.addAttribute("lista", service.filtraPorMassa(massa));
        model.addAttribute("listaIngrediente", ingredienteRepository.findAllByAtivoTrue());
        model.addAttribute("listaMassa", massaRepository.findAllByAtivoTrue());
        return "listaPastel";
    }

    @GetMapping("/buscaIngrediente")
    public String filtraPorIngrediente(Model model, @RequestParam List<String> ingredientes) {
        if (ingredientes != null && !ingredientes.isEmpty()) {
            ingredientes.removeIf(String::isEmpty);
            model.addAttribute("lista", service.filtrarPorIngredientes(ingredientes));
        }else
            model.addAttribute("lista", repository.findAllByAtivoTrue());
        model.addAttribute("listaIngrediente", ingredienteRepository.findAllByAtivoTrue());
        model.addAttribute("listaMassa", massaRepository.findAllByAtivoTrue());
        return "listaPastel";
    }

    @PostMapping
    public String save(@RequestParam String nome, @RequestParam String descricao, @RequestParam int quantidade, @RequestParam("ingredientes") List<String> ingredientesSelecionados, @RequestParam("massa") String massa) {
        service.save(nome, descricao, quantidade, ingredientesSelecionados, massa);
        return "redirect:/pastel";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        model.addAttribute("listaIngrediente", ingredienteRepository.findAllByAtivoTrue());
        model.addAttribute("listaMassa", massaRepository.findAllByAtivoTrue());
        return "cadastroPastel";
    }

    @PostMapping("/deletar")
    public String deletar(@RequestParam Long id) {
        var entity = repository.findById(id).orElseThrow();
        entity.setAtivo(false);
        repository.save(entity);
        return "redirect:/pastel";
    }

    @PostMapping("/atualizar")
    public String atualizar(@RequestParam int id, Model model) {
        model.addAttribute("id", id);
        return "atualizarPastel";
    }

    @GetMapping("/visualizar")
    public String visualizar(@RequestParam int id, Model model) {
        model.addAttribute("pastel", repository.findById((long) id).orElseThrow());
        model.addAttribute("massa", repository.findById((long) id).orElseThrow().getMassa());
        return "visualizarPastel";
    }

    @PostMapping("/update")
    public String update(@RequestParam int id, @RequestParam String nome, @RequestParam String descricao, @RequestParam int quantidade) {
        var pastel = repository.findById((long) id).orElseThrow();
        if (nome == null || nome.isEmpty())
            nome = pastel.getNome();
        if (descricao == null || descricao.isEmpty())
            descricao = pastel.getDescricao();
        repository.save(new Pastel((long) id, nome, descricao, quantidade, true,pastel.getMassa(), pastel.getIngredientes()));
        return "redirect:/pastel";
    }

}
