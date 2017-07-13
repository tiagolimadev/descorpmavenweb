/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.servico;

import com.ifpe.tads.descorp.acesso.Grupo;
import com.ifpe.tads.descorp.acesso.Papel;
import static com.ifpe.tads.descorp.acesso.Papel.ADMINISTRADOR;
import static com.ifpe.tads.descorp.acesso.Papel.CLIENTE;
import static com.ifpe.tads.descorp.acesso.Papel.OPERADOR;
import com.ifpe.tads.descorp.model.usuario.Administrador;
import com.ifpe.tads.descorp.model.usuario.Cliente;
import com.ifpe.tads.descorp.model.usuario.Entregador;
import com.ifpe.tads.descorp.model.usuario.Operador;
import com.ifpe.tads.descorp.model.usuario.Usuario;
import java.util.ArrayList;
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
 * @author eduardo.amaral
 */
@Stateless
@LocalBean
@DeclareRoles({ADMINISTRADOR,CLIENTE,OPERADOR})
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UsuarioServico {
    
    @Resource
    private SessionContext sessao;
    
    @PersistenceContext(name = "descorp", type = TRANSACTION)
    protected EntityManager em;
    
    public List<Usuario> getUsuarios() {
        if (sessao.isCallerInRole(Papel.OPERADOR)) {
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.ListarTodos", Usuario.class);
            return query.getResultList();
        } else {
            throw new EJBAccessException();
        }
    }
    
    public List<Usuario> getUsuariosPorTipo(String tipo) {
        if (sessao.isCallerInRole(Papel.OPERADOR)) {
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.PorTipo", Usuario.class);
            query.setParameter("tipo", tipo);
            return query.getResultList();
        } else {
            throw new EJBAccessException();
        }
    }
    
    public Usuario getUsuariosPorEmail(String email) {
        TypedQuery<Usuario> query = em.createNamedQuery("Usuario.PorLogin", Usuario.class);
        query.setParameter("login", email);
        return query.getResultList().get(0);
    }
    
    public Usuario getUsuarioPorId(Long id) {
        return em.find(Usuario.class, id);
    }
    
    public void salvar(Usuario usuario) {
        incluirNoGrupo(usuario);
        em.persist(usuario);
    }
    
    private void incluirNoGrupo(Usuario usuario) {
        List<String> nomeGrupos = new ArrayList<>();
        List<Grupo> grupos = new ArrayList<>();
        boolean adm = false;
        
        if (usuario instanceof Cliente) {
            nomeGrupos.add(Papel.CLIENTE);
        }
        
        
        if (usuario instanceof Administrador) {
            adm = true;
            nomeGrupos.add(Papel.ADMINISTRADOR);
            nomeGrupos.add(Papel.OPERADOR);
        } else if (usuario instanceof Operador) {
            adm = true;
            nomeGrupos.add(Papel.OPERADOR);
        } else if (usuario instanceof Entregador) {
            adm = true;
            nomeGrupos.add(Papel.ENTREGADOR);
        }
        
        if(adm && !sessao.isCallerInRole(Papel.ADMINISTRADOR)){
            throw new EJBAccessException();
        }
        
        TypedQuery<Grupo> query = em.createNamedQuery("Grupo.PorNome", Grupo.class);
        
        for (String grupo : nomeGrupos) {
            query.setParameter("grupo_nome", grupo);
            Grupo novoGrupo = query.getResultList().get(0);
            grupos.add(novoGrupo);
        }
        
        usuario.setGrupos(grupos);
    }
    
    public void atualizar(Usuario usuario) {
        boolean adm = false;
        
        em.merge(usuario);
        
        if (!(usuario instanceof Cliente)) {
            adm = true;
        }
        
        if(adm && !sessao.isCallerInRole(Papel.ADMINISTRADOR)){
            throw new EJBAccessException("É necessário perfil de ADM para alterar esse usuário.");
        }
        
    }
    
    public void remover(Usuario usuario) {
        if (sessao.isCallerInRole(Papel.ADMINISTRADOR)) {
            if (!em.contains(usuario)) {
                usuario = em.merge(usuario);
            }
            em.remove(usuario);
        } else {
            throw new EJBAccessException();
        }
    }
    
}
