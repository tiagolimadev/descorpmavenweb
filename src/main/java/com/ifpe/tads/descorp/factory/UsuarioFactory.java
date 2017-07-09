/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.factory;

import com.ifpe.tads.descorp.model.usuario.Administrador;
import com.ifpe.tads.descorp.model.usuario.Cliente;
import com.ifpe.tads.descorp.model.usuario.Entregador;
import com.ifpe.tads.descorp.model.usuario.Operador;
import com.ifpe.tads.descorp.model.usuario.TipoUsuario;
import com.ifpe.tads.descorp.model.usuario.Usuario;

/**
 *
 * @author Tiago Lima
 */
public class UsuarioFactory {
    
    public Usuario getUsuario(String tipo) {
        if (TipoUsuario.ADMINISTRADOR.toString().equals(tipo)) {
            return new Administrador();
        } else if (TipoUsuario.CLIENTE.toString().equals(tipo)) {
            return new Cliente();
        } else if (TipoUsuario.OPERADOR.toString().equals(tipo)) {
            return new Operador();
        } else if (TipoUsuario.ENTREGADOR.toString().equals(tipo)) {
            return new Entregador();
        } else {
            return null;
        }
    }
}
