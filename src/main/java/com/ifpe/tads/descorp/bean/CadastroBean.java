/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.usuario.Cliente;
import com.ifpe.tads.descorp.model.usuario.TipoUsuario;
import java.io.Serializable;
import static javax.annotation.Resource.AuthenticationType.CONTAINER;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "cadastroBean")
@RequestScoped
public class CadastroBean implements Serializable{

    @PersistenceContext(name = "descorp", type = TRANSACTION)
    private EntityManager em;
    
    @Valid
    private Cliente cliente;
    
    @NotBlank
    private String confirmacaoSenha;
    
    public CadastroBean() {
        this.cliente = new Cliente();;
        this.cliente.setTipo(TipoUsuario.CLIENTE);
    
    }
    
    public String cadastrarCliente(){
        
        if(cliente.getSenha().equals(confirmacaoSenha)){
            em.persist(cliente);
        }
        
        return "login";
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
