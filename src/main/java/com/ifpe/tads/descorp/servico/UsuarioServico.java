/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.servico;

import com.ifpe.tads.descorp.model.usuario.Usuario;
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
 * @author eduardo.amaral
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UsuarioServico {

    @PersistenceContext(name = "descorp", type = TRANSACTION)
    protected EntityManager em;
    
    public List<Usuario> getUsuarios() {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.ListarTodos", Usuario.class);
        return query.getResultList();
    }
    
    public List<Usuario> getUsuariosPorTipo(String tipo) {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.PorTipo", Usuario.class);
        query.setParameter("tipo", tipo);
        return query.getResultList();
    }
    
    public Usuario getUsuarioPorId(Long id) {
        return em.find(Usuario.class, id);
    }
    
    public void salvar(Usuario usuario) {
        em.persist(usuario);
    }
    
    public void atualizar(Usuario usuario) {
        em.merge(usuario);
    }
    
    public void remover(Usuario usuario) {
        if (!em.contains(usuario)) {
            usuario = em.merge(usuario);
        }
        em.remove(usuario);
    }
    
}
