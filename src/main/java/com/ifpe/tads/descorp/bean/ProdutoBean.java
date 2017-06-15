/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.produto.Produto;
import com.ifpe.tads.descorp.servico.ProdutoServico;
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
@ManagedBean(name = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {
    
    @EJB
    private ProdutoServico produtoServico;
    
    @Valid
    private Produto produto;
    private List<Produto> listaProdutos;
    private int linhas;
    
    @PostConstruct
    public void init() {
        listarProdutos();
        this.linhas = 10;
    }

    public ProdutoBean() {
        this.produto = new Produto();
    }
    
    public void initProduto() {
        this.produto = new Produto();
    }
    
    public void cadastrarProduto() {
        produtoServico.salvar(this.produto);
        listarProdutos();
    }
    
    public void listarProdutos() {
        this.listaProdutos = produtoServico.getProdutos();
    }
    
    public void removerProduto() {
        produtoServico.remover(produto);
        listarProdutos();
    }
    
    public void atualizarProduto() {
        produtoServico.atualizar(produto);
        listarProdutos();
    }
    
    public void selecionarProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.listaProdutos = produtos;
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }
    
    
}
