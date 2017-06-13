/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.produto.Categoria;
import com.ifpe.tads.descorp.servico.CategoriaServico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.Valid;

/**
 *
 * @author Tiago Lima
 */
@ManagedBean(name = "categoriaBean")
@RequestScoped
public class CategoriaBean implements Serializable {
    
    @EJB
    private CategoriaServico categoriaServico;
    
    @Valid
    private Categoria categoria;
    
    private List<Categoria> listaCategorias;
    
    private int linhas;
    
    @PostConstruct
    public void init() {
        listarCategorias();
        this.linhas = 10;
    }

    public CategoriaBean() {
        this.categoriaServico = new CategoriaServico();
        this.categoria = new Categoria();
    }
    
    public void cadastrarCategoria() {
        categoriaServico.salvar(categoria);
    }
    
    public void listarCategorias() {
        listaCategorias = categoriaServico.listar();
    }
    
    public void removerCategoria(Categoria categoria) {
        categoriaServico.remover(categoria);
    }
    
    public String editarCategoria(Categoria categoria) {
        setCategoria(categoria);
        return "editarCategoria";
    }
    
    public void atualizarCategoria(Categoria categoria) {
        categoriaServico.atualizar(categoria);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }
    
    
}
