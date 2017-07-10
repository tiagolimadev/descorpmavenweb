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
import com.ifpe.tads.descorp.model.produto.Categoria;
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
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tiago Lima
 */
@Stateless
@LocalBean
@DeclareRoles({ADMINISTRADOR,CLIENTE,OPERADOR})
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CategoriaServico {

    @Resource
    private SessionContext sessao;

    @PersistenceContext(name = "descorp", type = PersistenceContextType.TRANSACTION)
    protected EntityManager em;

    public void salvar(Categoria categoria) {
        if(sessao.isCallerInRole(Papel.ADMINISTRADOR)){
            em.persist(categoria);
        }else{
            throw new EJBAccessException();
        }
    }

    public List<Categoria> listar() {
        if(sessao.isCallerInRole(Papel.OPERADOR)){
                TypedQuery<Categoria> query = em.createNamedQuery("Categoria.listarCategorias", Categoria.class);
                return query.getResultList();
        }else{
            throw new EJBAccessException();
        }
    }

    public void atualizar(Categoria categoria) {
        if (sessao.isCallerInRole(Papel.ADMINISTRADOR)) {
            em.merge(categoria);
        } else {
            throw new EJBAccessException();
        }
    }

    public void remover(Categoria categoria) {

        if (sessao.isCallerInRole(Papel.ADMINISTRADOR)) {

            if (!em.contains(categoria)) {
                categoria = em.merge(categoria);
            }
            em.remove(categoria);
        }else{
            throw new EJBAccessException();
        }
    }

}
