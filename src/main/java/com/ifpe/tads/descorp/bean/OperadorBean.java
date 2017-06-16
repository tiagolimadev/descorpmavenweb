/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.usuario.Operador;
import com.ifpe.tads.descorp.servico.OperadorServico;
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
@ManagedBean(name = "operadorBean")
@SessionScoped
public class OperadorBean implements Serializable {
    
    @EJB
    private OperadorServico operadorServico;
    
    @Valid
    private Operador operador;
    private List<Operador> listaOperadores;
    private int linhas;
    
    @PostConstruct
    public void init() {
        listarOperadores();
        this.linhas = 10;
    }

    public OperadorBean() {
        this.operador = new Operador();
    }
    
    public void initOperador() {
        this.operador = new Operador();
    }
    
    public void selecionarOperador(Operador operador) {
        this.operador = operador;
    }
    
    public void listarOperadores() {
        this.listaOperadores = operadorServico.getOperadores();
    }
    
    public void cadastrarOperador() {
        operadorServico.salvar(this.operador);
        listarOperadores();
    }
    
    public void removerOperador() {
        operadorServico.remover(this.operador);
        listarOperadores();
    }
    
    public void atualizarOperador() {
        operadorServico.atualizar(this.operador);
        listarOperadores();
    }

    public Operador getOperador() {
        return operador;
    }

    public void setOperador(Operador operador) {
        this.operador = operador;
    }

    public List<Operador> getListaOperadores() {
        return listaOperadores;
    }

    public void setListaOperadores(List<Operador> listaOperadores) {
        this.listaOperadores = listaOperadores;
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }
    
    
}
