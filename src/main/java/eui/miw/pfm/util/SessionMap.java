/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

import java.io.Serializable;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Fred Peña
 */
public class SessionMap implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    private transient final Map<String, Object> map = context.getSessionMap();

    public SessionMap() {//NOPMD

    }

    public void put(final String key, final Object value) {
        this.map.put(key, value);
    }

    public Object get(final String key) {
        return map.get(key);
    }

}
