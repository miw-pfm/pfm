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
import javax.faces.context.FacesContext;

/**
 *
 * @author Roberto Amor
 * @author Fred Pena
 */
public class Bean {

//    private String errors;
    protected transient final SessionMap sessionMap;

    public Bean() {
        this.sessionMap = new SessionMap();
        if (notLogin()) {
            redirectionLogin();
        }
    }

    private boolean notLogin() {
        return sessionMap.get("userlogin") == null;
    }

    private void redirectionLogin() {
        try {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/pfm/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(SessionMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IllegalStateException e) {
            Logger.getLogger(Bean.class.getName()).info("Redirection Login");
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
