package com.example.demo.service;

import com.example.demo.model.Ingrediente;
import com.example.demo.model.Massa;
import com.example.demo.model.Pastel;
import com.example.demo.repository.IngredienteRepository;
import com.example.demo.repository.MassaRepository;
import com.example.demo.repository.PastelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PastelService {

    @Autowired
    private PastelRepository repository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private MassaRepository massaRepository;

    public void save(String nome, String descricao, int quantidade, List<String> ingredientesSelecionados, String massa) {
        var pastel = new Pastel();
        List<Ingrediente> ingredientes = new ArrayList<>();
        for (String i: ingredientesSelecionados) {
            ingredientes.add(ingredienteRepository.findById(Long.parseLong(i)).orElseThrow());
        }
        pastel.setNome(nome);
        pastel.setDescricao(descricao);
        pastel.setIngredientes(ingredientes);
        pastel.setMassa(massaRepository.findByNomeAndAtivoTrue(massa).orElseThrow());
        pastel.setQuantidade(quantidade);
        pastel.setAtivo(true);
        repository.save(pastel);
    }

    public List<Pastel> filtraPorMassa(String massa) {
        List<Pastel> lista = new ArrayList<>();
        var listaMassas = massaRepository.findByNomeContainingIgnoreCaseAndAtivoTrue(massa);
        for (Massa m: listaMassas) {
            var i = repository.findAllByMassaAndAtivoTrue(m);
            lista.addAll(i);
        }
        return lista;
    }

    public List<Pastel> filtrarPorIngredientes(List<String> ingredientes) {
        List<Ingrediente> ingredienteList = new ArrayList<>();
        ingredientes.forEach(s ->
                ingredienteList.add(ingredienteRepository.findByIdAndAtivoTrue(Long.parseLong(s)).orElseThrow())
        );
        List<Pastel> lista = new ArrayList<>();
        for (Pastel p : repository.findAllByAtivoTrue()) {
            if (new HashSet<>(p.getIngredientes()).containsAll(ingredienteList)) {
                lista.add(p);
            }
        }
        return lista;
    }
}
