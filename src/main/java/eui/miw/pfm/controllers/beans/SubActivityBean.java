/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.SubActivityEjb;
import eui.miw.pfm.models.entities.ActivityEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Jean Mubaied
 * @author Jose Mª Villar
 */
@RequestScoped
@Named
public class SubActivityBean {  

    public List<SubActivityEntity> getSubActivities() {
        SubActivityEjb activityEjb;
        activityEjb = new SubActivityEjb();        
        return activityEjb.obtainAllSubActivities();
    }
    
    public List<SubActivityEntity> getSubActivities(final ActivityEntity activity) {
        SubActivityEjb activityEjb;
        activityEjb = new SubActivityEjb();                
        return activityEjb.obtainSubActivities(activity);
    }
}
