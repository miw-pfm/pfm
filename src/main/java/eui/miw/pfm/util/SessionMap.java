/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Fred Peña
 */
public class SessionMap implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> map = context.getSessionMap();

    public SessionMap() {//NOPMD

    }

    public void add(final String key, final Object value) {
        this.map.put(key, value);
    }

    public Object get(final String key) {
        return map.get(key);
    }

    public void clean() {        
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SessionMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
