/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.servico;

import com.ifpe.tads.descorp.acesso.Papel;
import static com.ifpe.tads.descorp.acesso.Papel.CLIENTE;
import com.ifpe.tads.descorp.model.usuario.Cliente;
import static com.ifpe.tads.descorp.model.usuario.TipoUsuario.ADMINISTRADOR;
import com.ifpe.tads.descorp.model.venda.Venda;
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
@DeclareRoles({CLIENTE})
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ClienteServico {

    @Resource
    private SessionContext sessao;

    @PersistenceContext(name = "descorp", type = TRANSACTION)
    protected EntityManager entityManager;

    public List<Venda> getHistorico(Cliente cliente) {

        if (sessao.isCallerInRole(Papel.CLIENTE)) {

            TypedQuery<Venda> queryVendas = entityManager.createNamedQuery("Venda.PorCliente", Venda.class);
            queryVendas.setParameter("clienteId", cliente.getId());

            return queryVendas.getResultList();

        } else {
            throw new EJBAccessException();
        }
    }

}
