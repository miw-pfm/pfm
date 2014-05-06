/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.LoginEjb;
import eui.miw.pfm.models.entities.UserEntity;
import eui.miw.pfm.util.SessionMap;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Fred Peña
 */
@RequestScoped
@Named
public class LogintBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private final SessionMap sessionMap;

    private static final Logger LOGGER = Logger.getLogger(LogintBean.class.getName());

    public LogintBean() {
        super();
        this.sessionMap = new SessionMap();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login(ActionEvent actionEvent) { //NOPMD
        final FacesContext context = FacesContext.getCurrentInstance();
        String view = null;
        final UserEntity userEntity;

        if (getUsername() != null && getPassword() != null) {
            LoginEjb loginEjb = new LoginEjb();
            userEntity = loginEjb.findUser(getUsername(), getPassword());
            if (userEntity != null) {
                this.sessionMap.add("UserLogIn", userEntity);
                LOGGER.warning("Valid credentials");
                context.addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", getUsername()));
                view = "";
            } else {
                LOGGER.warning("Invalid credentials");
                context.addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials"));
            }
        } else {
            LOGGER.warning("Invalid credentials");
            context.addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials"));
        }
        return view;
    }

}
