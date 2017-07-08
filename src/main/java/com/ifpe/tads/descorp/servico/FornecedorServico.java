/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.servico;

import com.ifpe.tads.descorp.model.fornecedor.Fornecedor;
import java.util.List;
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
 * @author tiagolimadev
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FornecedorServico {
    
    @PersistenceContext(name = "descorp", type = TRANSACTION)
    protected EntityManager em;
    
    public void salvar(Fornecedor fornecedor) {
        em.persist(fornecedor);
    }
    
    public List<Fornecedor> listar() {
        TypedQuery<Fornecedor> query = em.createNamedQuery("Fornecedor.ListarTodos", Fornecedor.class);
        return query.getResultList();
    }
    
    public void atualizar(Fornecedor fornecedor) {
        em.merge(fornecedor);
    }
    
    public void remover(Fornecedor fornecedor) {
        if (!em.contains(fornecedor)) {
            fornecedor = em.merge(fornecedor);
        }
        em.remove(fornecedor);
    }
}
