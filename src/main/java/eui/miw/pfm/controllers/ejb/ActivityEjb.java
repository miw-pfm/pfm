/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ActivityEntity;
import java.util.List;

/**
 *
 * @author Jean Mubaied
 */
public class ActivityEjb {
    
    public List<ActivityEntity> obtainActivities() {
        return AbstractDAOFactory.getFactory().getActivityDAO().findAll();
    }
    
}
