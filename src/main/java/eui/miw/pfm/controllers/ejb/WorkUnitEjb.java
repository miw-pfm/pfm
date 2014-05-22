/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.WorkUnitEntity;

/**
 *
 * @author César Martínez
 */
public class WorkUnitEjb {
    public void update(final WorkUnitEntity workUnitEntity){
        AbstractDAOFactory.getFactory().getWorkUnitDAO().update(workUnitEntity);
    }
    
    public void create(final WorkUnitEntity workUnitEntity){
    
        AbstractDAOFactory.getFactory().getWorkUnitDAO().create(workUnitEntity);
        
    }
}
