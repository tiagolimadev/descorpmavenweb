/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    @NotBlank
    private String login;
    
    @NotBlank
    private String senha;
    private FacesContext facesContext;
    
    public LoginBean() {
    }
    
    public String logar(){
        String retorno = "";
        
        facesContext = FacesContext.getCurrentInstance();
        
        try {
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            request.login(login, senha);
            facesContext.getExternalContext().getSession(true);
            retorno = "sucesso";
        } catch (ServletException ex) {
            setLogin(null);
            adicionarMensagem("Login e/ou senha incorretos.");
        }
        
        return retorno;
    }

    public void adicionarMensagem(String mensagem){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null);
        facesContext.addMessage(null, message);
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
