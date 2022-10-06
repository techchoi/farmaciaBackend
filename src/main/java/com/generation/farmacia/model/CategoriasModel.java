package com.generation.farmacia.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_categorias")
public class CategoriasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String descricao;

    @OneToMany(mappedBy = "categoriasModel", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("categotias")
    private List<ProdutosModel> produtosModels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<ProdutosModel> getProdutosModels() {
        return produtosModels;
    }

    public void setProdutosModels(List<ProdutosModel> produtosModels) {
        this.produtosModels = produtosModels;
    }
}
