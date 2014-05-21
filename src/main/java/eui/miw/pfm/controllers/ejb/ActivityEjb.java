/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ActivityEntity;

/**
 *
 * @author Jose Mª Villar
 */
public class ActivityEjb {

    public ActivityEntity obtainActivity(final Integer id) {
        return AbstractDAOFactory.getFactory().getActivityDAO().read(id);
    }
}
