package com.example.demo.mock;

import com.example.demo.model.Ingrediente;
import com.example.demo.model.Massa;
import com.example.demo.model.Pastel;
import com.example.demo.repository.IngredienteRepository;
import com.example.demo.repository.MassaRepository;
import com.example.demo.repository.PastelRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mock {

    @Autowired
    private MassaRepository massaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private PastelRepository repository;

    @PostConstruct
    public void init() {
        mock();
    }

    public void mock() {
        List<Ingrediente> listaIngrediente = List.of(
                new Ingrediente("Carne", "Carne moída temperada"),
                new Ingrediente("Queijo", "Queijo muçarela ralado"),
                new Ingrediente("Cebola", "Cebola picada"),
                new Ingrediente("Tomate", "Tomate cortado em cubos"),
                new Ingrediente("Frango", "Palmito em conserva"),
                new Ingrediente("Ovo", "Ovo cozido")
        );
        ingredienteRepository.saveAll(listaIngrediente);

        List<Massa> listaMassa = List.of(
                new Massa("Tradicional", "Massa fina e crocante, perfeita para pastéis fritos."),
                new Massa("Integral", "Massa integral, mais saudável e rica em fibras."),
                new Massa("BatataDoce", "Massa feita com batata doce, dando um toque adocicado ao pastel."),
                new Massa("Feira", "Massa mais grossa e resistente, ideal para pastéis grandes e bem recheados."),
                new Massa("Caseira", "Massa feita em casa, com ingredientes frescos e amor.")
        );
        massaRepository.saveAll(listaMassa);

        repository.save(new Pastel("Saboroso do Oriente", "Descrição genérica", 3, listaMassa.get(0), List.of(listaIngrediente.get(0), listaIngrediente.get(3), listaIngrediente.get(4))));
        repository.save(new Pastel("Queijo Supremo", "Descrição genérica", 2, listaMassa.get(1), List.of(listaIngrediente.get(1), listaIngrediente.get(4))));
        repository.save(new Pastel("Frutos do Mar", "Descrição genérica", 7, listaMassa.get(2), List.of(listaIngrediente.get(0), listaIngrediente.get(2))));
        repository.save(new Pastel("Chocolate e Banana", "Descrição genérica", 9, listaMassa.get(3), List.of(listaIngrediente.get(3), listaIngrediente.get(4))));
        repository.save(new Pastel("Arco-Íris de Legumes", "Descrição genérica", 1, listaMassa.get(4), List.of(listaIngrediente.get(1))));

        repository.save(new Pastel("Especial de carne", "Descrição genérica", 1, listaMassa.get(4), List.of(listaIngrediente.get(5),listaIngrediente.get(0))));
        repository.save(new Pastel("Especial de Queijo", "Descrição genérica", 9, listaMassa.get(4), List.of(listaIngrediente.get(5),listaIngrediente.get(1))));
        repository.save(new Pastel("Especial de Frango", "Descrição genérica", 77, listaMassa.get(4), List.of(listaIngrediente.get(5),listaIngrediente.get(4))));

    }
}
