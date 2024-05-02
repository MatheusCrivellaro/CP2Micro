package com.grupoa.pastelaria.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "TB_INGREDIENTE")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
