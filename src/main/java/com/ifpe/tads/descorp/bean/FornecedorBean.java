/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.fornecedor.Fornecedor;
import com.ifpe.tads.descorp.servico.FornecedorServico;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.Valid;

/**
 *
 * @author tiagolimadev
 */
@SessionScoped
@ManagedBean(name = "fornecedorBean")
public class FornecedorBean {
    
    @Valid
    private Fornecedor fornecedor;
    
    @EJB
    private FornecedorServico fornecedorServico;
    private List<Fornecedor> listaFornecedores;
    private int linhas;
    
    @PostConstruct
    public void init() {
        listarFornecedores();
        this.linhas = 10;
    }
    
    public FornecedorBean() {
        this.fornecedor = new Fornecedor();
    }
    
    public void initFornecedor() {
        this.fornecedor = new Fornecedor();
    }
    
    public void cadastrarFornecedor() {
        fornecedorServico.salvar(this.fornecedor);
        listarFornecedores();
    }
    
    public void listarFornecedores() {
        listaFornecedores = fornecedorServico.listar();
    }
    
    public void removerFornecedor() {
        fornecedorServico.remover(this.fornecedor);
        listarFornecedores();
    }
    
    public void atualizarFornecedor() {
        fornecedorServico.atualizar(this.fornecedor);
        listarFornecedores();
    }
    
    public void selecionarFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }
    
    
}
