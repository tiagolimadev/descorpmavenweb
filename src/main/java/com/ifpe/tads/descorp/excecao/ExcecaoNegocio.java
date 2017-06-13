/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.excecao;

/**
 *
 * @author Eduardo
 */
public class ExcecaoNegocio extends Exception {
    
    private String mensagem;

    public ExcecaoNegocio(String mensagem) {
        this.mensagem = mensagem;
    }
    
    @Override
    public String getMessage() {
        return mensagem;
    }
    
}
