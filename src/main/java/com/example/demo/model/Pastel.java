package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "TB_PASTEL")
@Entity
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

    public Pastel(Long id, String nome, String descricao, int quantidade, boolean ativo, Massa massa, List<Ingrediente> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.ativo = ativo;
        this.massa = massa;
        this.ingredientes = ingredientes;
    }

    public Pastel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Massa getMassa() {
        return massa;
    }

    public void setMassa(Massa massa) {
        this.massa = massa;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
