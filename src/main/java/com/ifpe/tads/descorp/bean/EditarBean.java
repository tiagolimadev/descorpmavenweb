/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.usuario.Usuario;
import com.ifpe.tads.descorp.servico.ClienteServico;
import com.ifpe.tads.descorp.servico.UsuarioServico;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.Valid;

/**
 *
 * @author Eduardo
 */
@ViewScoped
@ManagedBean
public class EditarBean implements Serializable{
    
    @EJB
    UsuarioServico usuarioServico;
    
    @Valid
    Usuario usuario;
    
    public String editarUsuario(){
        
        usuarioServico.editar(usuario);
        
        return "home-cliente";
    }
    
    public String initEditarPerfil(){
        usuario = usuarioServico.getUsuarioPorId(1L);
        return "editar-perfil";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
