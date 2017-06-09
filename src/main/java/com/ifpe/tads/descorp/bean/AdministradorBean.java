/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.usuario.Usuario;
import com.ifpe.tads.descorp.servico.AdministradorServico;
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
    private AdministradorServico admServico;
    
    private List<Usuario> listaUsuarios;
    
    @PostConstruct
    public void init(){
        atualizarLista();
    }
    
    public AdministradorBean() {
        
    }

    private void atualizarLista(){
        listaUsuarios = admServico.listarUsuarios();
    }
    
    public void visualizarUsuario(){
        listaUsuarios = admServico.listarUsuarios();
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
    
}
