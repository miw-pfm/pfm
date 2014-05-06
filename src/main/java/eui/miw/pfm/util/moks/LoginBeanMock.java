/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util.moks;

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
        
        if (username != null && username.equals("admin") && password != null
                && password.equals("admin")) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
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
