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
import javax.faces.bean.SessionScoped;
import javax.validation.Valid;

/**
 *
 * @author Tiago Lima
 */
@ManagedBean(name = "categoriaBean")
@SessionScoped
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
        this.categoria = new Categoria();
    }
    
    public void initCategoria() {
        this.categoria = new Categoria();
    }
    
    public void cadastrarCategoria() {
        categoriaServico.salvar(this.categoria);
        listarCategorias();
    }
    
    public void listarCategorias() {
        listaCategorias = categoriaServico.listar();
    }
    
    public void removerCategoria() {
        categoriaServico.remover(this.categoria);
        listarCategorias();
    }
    
    public void atualizarCategoria() {
        categoriaServico.atualizar(this.categoria);
        listarCategorias();
    }
    
    public void selecionarCategoria(Categoria categoria) {
        this.categoria = categoria;
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
