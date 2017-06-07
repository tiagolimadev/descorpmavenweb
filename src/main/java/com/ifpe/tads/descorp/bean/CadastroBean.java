/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.usuario.Cliente;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Eduardo
 */
@Named(value = "cadastroBean")
@RequestScoped
public class CadastroBean implements Serializable{

    @PersistenceContext(name = "descorp", type = TRANSACTION)
    private EntityManager em;
    
    @NotNull @Valid
    private Cliente cliente;
    
    @NotBlank
    private String confirmacaoSenha;
    
    /**
     * Creates a new instance of CadastroBean
     */
    public CadastroBean() {
        inicializarCampos();
    }
    
    private void inicializarCampos(){
        this.cliente = new Cliente();
        this.confirmacaoSenha = "";
    }
    
    public String registrar(){
        
        if(cliente.getSenha().equals(confirmacaoSenha)){
            em.persist(cliente);
            inicializarCampos();
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
