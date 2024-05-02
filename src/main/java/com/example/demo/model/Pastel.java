package com.grupoa.pastelaria.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "TB_PASTEL")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pastel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private int quantidade;
    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "pastel_massa")
    private Massa massa;

    @ManyToMany
    @JoinTable(
            name = "pastel_ingrediente",
            joinColumns = @JoinColumn(name = "pastel_id"),
            inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientes;

    public Pastel(String nome) {
        this.nome = nome;
    }

    public Pastel(String nome, String descricao, int quantidade, Massa massa, List<Ingrediente> ingredientes) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.massa = massa;
        this.ingredientes = ingredientes;
        this.ativo = true;
    }
}
