/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "logoutBean")
@ViewScoped
public class LogoutBean {
    
    public String logout() throws ServletException {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) fc.getExternalContext().getSession(false);
        
        if (sessao != null) {
            sessao.invalidate();
        }
        
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();        
        request.logout();
        
        return "login";
    }
    
}
