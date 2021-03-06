/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.usuario.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "administradorBean")
@SessionScoped
public class AdministradorBean implements Serializable {

    @EJB
    private UsuarioBean servico;
    
    private List<Usuario> listaUsuarios;
    private Usuario usuarioSelecionado;
    
    @PostConstruct
    public void init(){
        atualizarLista();
    }
    
    public AdministradorBean() {
        
    }

    private void atualizarLista(){
        listaUsuarios = servico.getListaUsuarios();
    }
    
    public void initVisualizarUsuario(Usuario usuario){
        this.usuarioSelecionado = usuario;
    }
    
    public void adicionarUsuario(){
        System.out.println("com.ifpe.tads.descorp.bean.AdministradorBean.adicionarUsuario()");
    }
    
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
    
}
