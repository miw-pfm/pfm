/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ActivityEjb;
import eui.miw.pfm.models.entities.ActivityEntity;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Jean Mubaied
 */

@RequestScoped
@Named
public class ActivityBean {
    
    private final ActivityEntity activity;
    
     public ActivityBean() {
        super();
        activity = new ActivityEntity();    
    } 
     
     public void getActivities() {
        final ActivityEjb activityEjb;
        activityEjb = new ActivityEjb();
        final List<ActivityEntity> activities;
        activities = activityEjb.obtainActivities();   
    }
    
}
