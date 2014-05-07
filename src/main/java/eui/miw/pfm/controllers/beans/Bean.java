/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.util.SessionMap;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Roberto Amor
 */
public class Bean {

//    private String errors;
    protected final SessionMap sessionMap;

    public Bean() {
        this.sessionMap = new SessionMap();
        if (notLogin()) {
            redirectionLogin();
        }
    }

    private boolean notLogin() {
        return sessionMap.get("UserLogIn") == null;
    }

    private void redirectionLogin() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(Bean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public String getErrors() {
//        return errors;
//    }
//
//    public void setErrors(final String errors) {
//        this.errors = errors;
//    }
//
//    public boolean hasErrors() {
//        return !this.errors.equals("");
//    }
//
//    public void addFieldError(final String fieldError) {
//        if (this.hasErrors()) {
//            this.errors += "; " + fieldError;
//        } else {
//            this.errors = fieldError;
//
//        }
//    }
}
