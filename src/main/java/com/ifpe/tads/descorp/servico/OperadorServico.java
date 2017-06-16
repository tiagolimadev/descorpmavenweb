/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.servico;

import com.ifpe.tads.descorp.model.usuario.Operador;
import com.ifpe.tads.descorp.model.usuario.TipoUsuario;
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
 * @author Programação
 */
@LocalBean
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OperadorServico {
    
    @PersistenceContext(name = "descorp", type = TRANSACTION)
    protected EntityManager entityManager;
    
    public List<Operador> getOperadores(){
        TypedQuery<Operador> query = entityManager.createNamedQuery("Usuario.PorTipo", Operador.class);
        query.setParameter("tipo", TipoUsuario.OPERADOR);
        return query.getResultList();
    }
    
    public void salvar(Operador operador) {
       entityManager.persist(operador);
    }
    
    public void atualizar(Operador operador) {
        entityManager.merge(operador);
    }
    
    public void remover(Operador operador) {
        if (!entityManager.contains(operador)) {
            operador = entityManager.merge(operador);
        }
        entityManager.remove(operador);
    }
}
