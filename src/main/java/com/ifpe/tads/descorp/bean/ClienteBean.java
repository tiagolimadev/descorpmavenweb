/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.usuario.Cliente;
import com.ifpe.tads.descorp.model.venda.Venda;
import com.ifpe.tads.descorp.servico.ClienteServico;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.validation.Valid;

/**
 *
 * @author eduardo.amaral
 */
@SessionScoped
@ManagedBean(name = "clienteBean")
public class ClienteBean implements Serializable {
    
    @Valid
    private Cliente cliente;

    @EJB
    private ClienteServico clienteServico;
    
    private Venda vendaSelecionada;
    
    public ClienteBean() {}
    
    public String initHistorico(){
        String resultado = "";
        
        cliente = clienteServico.getHistorico(cliente);
        
        resultado = "historico-cliente";
        
        return resultado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda getVendaSelecionada() {
        return vendaSelecionada;
    }

    public void setVendaSelecionada(Venda vendaSelecionada) {
        this.vendaSelecionada = vendaSelecionada;
    }

}
