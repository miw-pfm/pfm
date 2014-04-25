/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.util;

import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author yiyi
 */
public class SessionMap {
    
    private final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();//NOPMD
    private final Map<String, Object> map = context.getSessionMap();//NOPMD

    public SessionMap() {//NOPMD

    }
    
    public void add(final String key, final Object value) {
        this.map.put(key, value);
    }
    
    public Object get(final String key) {
        return map.get(key);
    }
    
}
