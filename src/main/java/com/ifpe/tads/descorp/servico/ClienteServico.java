/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.servico;

import com.ifpe.tads.descorp.model.usuario.Cliente;
import com.ifpe.tads.descorp.model.venda.ItemVenda;
import com.ifpe.tads.descorp.model.venda.Venda;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.TypedQuery;

/**
 *
 * @author eduardo.amaral
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ClienteServico {
    
    @PersistenceContext(name = "descorp", type = TRANSACTION)
    protected EntityManager entityManager;
    
    public Cliente getHistorico(Cliente cliente){
        
        if(!entityManager.contains(cliente)){
            entityManager.merge(cliente);
        }
        
        cliente.setVendas(cliente.getVendas());
        
        TypedQuery<ItemVenda> queryItems = entityManager.createNamedQuery("ItemVenda.PorIdVenda", ItemVenda.class);
        for(Venda venda : cliente.getVendas()){
            queryItems.setParameter("idVenda", venda.getId());
            venda.setItensVenda(queryItems.getResultList());
        }
        
        return cliente;
    }

}
