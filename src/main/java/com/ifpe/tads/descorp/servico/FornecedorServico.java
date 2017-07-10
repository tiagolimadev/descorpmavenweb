/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.servico;

import com.ifpe.tads.descorp.acesso.Papel;
import static com.ifpe.tads.descorp.acesso.Papel.ADMINISTRADOR;
import static com.ifpe.tads.descorp.acesso.Papel.CLIENTE;
import static com.ifpe.tads.descorp.acesso.Papel.OPERADOR;
import com.ifpe.tads.descorp.model.fornecedor.Fornecedor;
import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJBAccessException;
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
import javax.persistence.TypedQuery;

/**
 *
 * @author tiagolimadev
 */
@Stateless
@LocalBean
@DeclareRoles({ADMINISTRADOR,CLIENTE,OPERADOR})
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FornecedorServico {
    
    @Resource
    private SessionContext sessao;
    
    @PersistenceContext(name = "descorp", type = TRANSACTION)
    protected EntityManager em;
    
    public void salvar(Fornecedor fornecedor) {
        if (sessao.isCallerInRole(Papel.OPERADOR)) {
            em.persist(fornecedor);
        } else {
            throw new EJBAccessException();
        }
    }
    
    public List<Fornecedor> listar() {
        if (sessao.isCallerInRole(Papel.OPERADOR)) {
            TypedQuery<Fornecedor> query = em.createNamedQuery("Fornecedor.ListarTodos", Fornecedor.class);
            return query.getResultList();
        } else {
            throw new EJBAccessException();
        }
    }
    
    public void atualizar(Fornecedor fornecedor) {
        if (sessao.isCallerInRole(Papel.OPERADOR)) {
            em.merge(fornecedor);
        } else {
            throw new EJBAccessException();
        }
    }
    
    public void remover(Fornecedor fornecedor) {
        if (sessao.isCallerInRole(Papel.ADMINISTRADOR)) {
            if (!em.contains(fornecedor)) {
                fornecedor = em.merge(fornecedor);
            }
            em.remove(fornecedor);
        } else {
            throw new EJBAccessException();
        }
    }
    
}
