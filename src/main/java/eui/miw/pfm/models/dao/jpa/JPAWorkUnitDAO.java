/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.models.dao.jpa;

import eui.miw.pfm.models.dao.interfaces.WorkUnitDAO;
import eui.miw.pfm.models.entities.WorkUnitEntity;

/**
 *
 * @author Jose Mª Villar
 */
public class JPAWorkUnitDAO extends JPATransactionGenericDAO<WorkUnitEntity, Integer> implements WorkUnitDAO{

    public JPAWorkUnitDAO() {
        super(WorkUnitEntity.class);
    }    
}