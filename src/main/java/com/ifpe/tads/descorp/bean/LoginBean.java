/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpe.tads.descorp.bean;

import com.ifpe.tads.descorp.acesso.Papel;
import com.ifpe.tads.descorp.model.usuario.Usuario;
import com.ifpe.tads.descorp.servico.UsuarioServico;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @EJB
    private UsuarioServico servico;

    public LoginBean() {
    }

    public String logar() {
        String retorno = "";

        facesContext = FacesContext.getCurrentInstance();

        try {
            HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
            request.login(login, senha);
            request.getSession(true);
            Usuario user = servico.getUsuariosPorEmail(login);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", user);

            if (!user.getGrupos().isEmpty()) {
                switch (user.getGrupos().get(0).getNome()) {
                    case Papel.ADMINISTRADOR:
                    case Papel.OPERADOR:
                        retorno = "home-adm";
                        break;
                    case Papel.ENTREGADOR:
                        break;
                    case Papel.CLIENTE:
                        retorno = "home-cliente";
                        break;
                }

            } else {
                invalidarSessao();
            }

        } catch (ServletException ex) {
            setLogin(null);
            adicionarMensagem("Login e/ou senha incorretos.");
        }

        return retorno;
    }

    private void invalidarSessao() throws ServletException{
        adicionarMensagem("Grupo não encontrado.");
        
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession sessao = (HttpSession) fc.getExternalContext().getSession(false);
        
        if (sessao != null) {
            sessao.invalidate();
        }
        
        HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();        
        request.logout();
        
    }
    
    private void adicionarMensagem(String mensagem) {
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
