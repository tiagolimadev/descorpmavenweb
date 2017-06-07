/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

    @NotBlank
    private String login;
    
    @NotBlank
    private String senha;
    
    public LoginBean() {
    }
    
    public String logar(){
        return "";
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
