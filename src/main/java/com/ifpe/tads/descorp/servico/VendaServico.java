/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.servico;

import com.ifpe.tads.descorp.excecao.ExcecaoNegocio;
import com.ifpe.tads.descorp.model.produto.Produto;
import com.ifpe.tads.descorp.model.venda.ItemVenda;
import com.ifpe.tads.descorp.model.venda.Venda;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;

/**
 *
 * @author Eduardo
 */
@LocalBean
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class VendaServico {
    
    @Resource
    private SessionContext sessao;
    
    @PersistenceContext(name = "descorp", type = TRANSACTION)
    protected EntityManager entityManager;
    
    public void finalizarVenda(Venda venda) throws ExcecaoNegocio {
        
        for(ItemVenda item : venda.getItensVenda()){
            Produto produto = entityManager.find(Produto.class, item.getProduto().getId());
            if(item.getQuantidade() > produto.getQtdeDisponivel()){
                throw new ExcecaoNegocio("Produto "+ produto.getNome() +" indisponível.");
            }
        }
            
        entityManager.persist(venda);
        
        for(ItemVenda item : venda.getItensVenda()){
            Produto produto = item.getProduto();
            produto.setQtdeDisponivel(produto.getQtdeDisponivel() - item.getQuantidade());
            entityManager.merge(produto);
        }
        
    }
    
}
