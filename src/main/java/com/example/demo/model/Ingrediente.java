package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "TB_INGREDIENTE")
@Entity
public class Ingrediente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private boolean ativo;

    @ManyToMany(mappedBy = "ingredientes")
    private List<Pastel> pasteis;

    public Ingrediente(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = true;
    }

    public Ingrediente(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Ingrediente(Long id, String nome, String descricao, boolean ativo, List<Pastel> pasteis) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
        this.pasteis = pasteis;
    }

    public Ingrediente() {
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<Pastel> getPasteis() {
        return pasteis;
    }

    public void setPasteis(List<Pastel> pasteis) {
        this.pasteis = pasteis;
    }
}
