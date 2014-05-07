/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
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
        final ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        final String ctxPath = ((ServletContext) ctx.getContext()).getContextPath();
        try {
            ((HttpSession) ctx.getSession(false)).invalidate();
            ctx.redirect(ctxPath + "/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LogOutBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
