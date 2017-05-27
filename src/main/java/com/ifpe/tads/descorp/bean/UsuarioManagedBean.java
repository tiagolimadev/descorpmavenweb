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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
}
