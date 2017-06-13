/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Eduardo
 */
public abstract class BasicBean {
    
    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem) {
        FacesMessage message = new FacesMessage(severity, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
