/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.servico;

import com.ifpe.tads.descorp.model.produto.Categoria;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tiago Lima
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CategoriaServico {
    
    @PersistenceContext(name = "descorp", type = PersistenceContextType.TRANSACTION)
    protected EntityManager em;
    
    public void salvar(Categoria categoria) {
        em.persist(categoria);
    }
    
    public List<Categoria> listar() {
        TypedQuery<Categoria> query = em.createNamedQuery("Categoria.listarCategorias", Categoria.class);
        return query.getResultList();
    }
    
    public void atualizar(Categoria categoria) {
        em.merge(categoria);
    }
    
    public void remover(Categoria categoria) {
        if (!em.contains(categoria)) {
            categoria = em.merge(categoria);
        }
        em.remove(categoria);
    }
    
}
