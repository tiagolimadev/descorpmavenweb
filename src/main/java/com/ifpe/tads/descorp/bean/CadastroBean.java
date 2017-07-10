/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.usuario.Cliente;
import com.ifpe.tads.descorp.model.usuario.TipoUsuario;
import com.ifpe.tads.descorp.servico.UsuarioServico;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "cadastroBean")
@RequestScoped
public class CadastroBean implements Serializable{

    @EJB
    private UsuarioServico usuarioServico;
    
    @Valid
    private Cliente cliente;
    
    @NotBlank
    private String confirmacaoSenha;
    
    public CadastroBean() {
        this.cliente = new Cliente();;
        this.cliente.setTipo(TipoUsuario.ADMINISTRADOR);
    
    }
    
    public String cadastrarCliente(){
        
        String retorno = "";
        
        if(cliente.getSenha().equals(confirmacaoSenha)){
            usuarioServico.salvar(cliente);
            retorno = "login";
        }else{
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Senhas não conferem.");
            FacesContext.getCurrentInstance().addMessage("", facesMessage);
        }
        
        return retorno;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }
    
}
