/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.util.SessionMap;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Fred Peña
 */
@RequestScoped
@Named
public class LogOutBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    public void logOut() {
        try {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(SessionMap.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IllegalStateException e) {
            Logger.getLogger(LogOutBean.class.getName()).info("Log Out");
        }
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
    }

}
