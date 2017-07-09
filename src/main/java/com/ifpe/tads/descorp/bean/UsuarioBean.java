/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.factory.UsuarioFactory;
import com.ifpe.tads.descorp.model.usuario.TipoUsuario;
import com.ifpe.tads.descorp.model.usuario.Usuario;
import com.ifpe.tads.descorp.servico.UsuarioServico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.Valid;

/**
 *
 * @author Tiago Lima
 */
@ManagedBean(name = "usuarioBean")
@LocalBean
@ViewScoped
public class UsuarioBean implements Serializable {
    
    @EJB
    private UsuarioServico usuarioServico;
    
    @Valid
    private Usuario usuario;
    private UsuarioFactory factory;
    private List<Usuario> listaUsuarios;
    private String tipoUsuarioSelecionado;
    private TipoUsuario[] tiposUsuario;
    private int linhas;
    
    @PostConstruct
    public void init() {
        listarUsuarios();
        this.linhas = 10;
        this.tiposUsuario = TipoUsuario.values();
        this.factory = new UsuarioFactory();
    }
    
//    public UsuarioBean() {}
    
    public void initUsuario() {
        usuario = factory.getUsuario(this.tipoUsuarioSelecionado);
    }
    
    public void selecionarUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void listarUsuarios() {
        this.listaUsuarios = usuarioServico.getUsuarios();
    }
    
    public void cadastrarUsuario() {
        usuarioServico.salvar(this.usuario);
        listarUsuarios();
    }
    
    public void removerUsuario() {
        usuarioServico.remover(this.usuario);
        listarUsuarios();
    }
    
    public void atualizarUsuario() {
        usuarioServico.atualizar(this.usuario);
        listarUsuarios();
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setTiposUsuario(TipoUsuario[] tiposUsuario) {
        this.tiposUsuario = tiposUsuario;
    }

    public TipoUsuario[] getTiposUsuario() {
        return tiposUsuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public String getTipoUsuarioSelecionado() {
        return tipoUsuarioSelecionado;
    }

    public void setTipoUsuarioSelecionado(String tipoUsuarioSelecionado) {
        this.tipoUsuarioSelecionado = tipoUsuarioSelecionado;
    }

    public int getLinhas() {
        return linhas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }
    
}
