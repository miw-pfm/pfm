/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ActivityEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import java.util.List;

/**
 *
 * @author Jean Mubaied
 * @author Jose Mª Villar
 */
public class ActivitiesEjb {

    public List<SubActivityEntity> obtainAllSubActivities() {
        return AbstractDAOFactory.getFactory().getSubActivityDAO().findAll();
    }

    public List<SubActivityEntity> obtainSubActivities(final ActivityEntity activity) {
        return activity.getSubActivity();
    }

    public List<ActivityEntity> obtainAllActivities() {
        System.out.println("Entra:"+AbstractDAOFactory.getFactory().getActivityDAO().findAll().toString());
        return AbstractDAOFactory.getFactory().getActivityDAO().findAll();
    }
    public static void main(String[] args) {
        ActivitiesEjb ejb = new ActivitiesEjb();
        ejb.obtainAllActivities();
    }

}
