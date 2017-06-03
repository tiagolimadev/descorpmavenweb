/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.model.usuario.Usuario;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author tiagolima
 */
@Stateless
@LocalBean
public class UsuarioManagedBean {

    @PersistenceContext(name = "descorp", type = TRANSACTION)
    private EntityManager em;

    public boolean cadastrarUsuario(@NotNull @Valid Usuario usuario) {

        em.persist(usuario);
        
        return true;
    }
    
    public Usuario getUsuarioPorCpf(@NotEmpty String cpf){
        
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.PorCPF", Usuario.class);
        query.setParameter("cpf", cpf);
        
        return query.getSingleResult();
    }
    
    public void editarUsuario(@NotNull @Valid Usuario usuario){
        em.merge(usuario);
    }
    
    public void deletarUsuario(@NotNull Usuario usuario){
        em.remove(usuario);
    }
}
