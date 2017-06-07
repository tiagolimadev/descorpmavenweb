/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Eduardo
 */
@Named("loginBean")
@RequestScoped
public class LoginBean implements Serializable {

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
