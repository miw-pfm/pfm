package eui.miw.pfm.controllers.beans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Clemencio Morales Lucas
 */

@RequestScoped
@Named
public class CreateWorkerBean extends Bean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String name;
    
    public String createWorker(){
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
