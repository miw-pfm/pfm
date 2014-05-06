/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util.moks;

import eui.miw.pfm.controllers.ejb.LoginEjb;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.util.SessionMap;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jvillarb
 */
@Named
@RequestScoped
public class LoginBeanMock implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private boolean loggedIn = false;
    private final SessionMap sessionMap;
    
    public LoginBeanMock() {
        super();
        this.sessionMap = new SessionMap();
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void login(final ActionEvent actionEvent) {
        final RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message;
        final UserEntity userEntity;

        if (username != null && password != null) {
            LoginEjb loginEjb = new LoginEjb();
            userEntity = loginEjb.findUser(getUsername(), getPassword());

            if (userEntity != null) {
                loggedIn = true;
                this.sessionMap.add("UserLogIn", userEntity);                
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            } else {
                loggedIn = false;
                message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                        "Invalid credentials");
            }
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                    "Invalid credentials");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);

        if (loggedIn) {
            context.addCallbackParam("view", "list_project.xhtml");
        }
    }
}
