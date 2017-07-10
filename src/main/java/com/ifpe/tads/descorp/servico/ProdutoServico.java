/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.servico;

import com.ifpe.tads.descorp.acesso.Papel;
import com.ifpe.tads.descorp.model.produto.Produto;
import java.util.List;
import javax.annotation.Resource;
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
 * @author Eduardo
 */
@LocalBean
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ProdutoServico {

    @Resource
    private SessionContext sessao;

    @PersistenceContext(name = "descorp", type = TRANSACTION)
    protected EntityManager entityManager;

    public List<Produto> getProdutos() {
        TypedQuery<Produto> query = entityManager.createNamedQuery("Produto.Todos", Produto.class);
        return query.getResultList();
    }

    public void salvar(Produto produto) {
        if (sessao.isCallerInRole(Papel.OPERADOR)) {
            entityManager.persist(produto);
        }else{
            throw new EJBAccessException();
        }
    }

    public void atualizar(Produto produto) {
        if (sessao.isCallerInRole(Papel.OPERADOR)) {
            entityManager.merge(produto);
        }else{
            throw new EJBAccessException();
        }
    }

    public void remover(Produto produto) {
        if (sessao.isCallerInRole(Papel.ADMINISTRADOR)) {
            if (!entityManager.contains(produto)) {
                produto = entityManager.merge(produto);
            }
            entityManager.remove(produto);
        }else{
            throw new EJBAccessException();
        }
    }

}
