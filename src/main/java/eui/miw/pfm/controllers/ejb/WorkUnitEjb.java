/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.ejb;

import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import eui.miw.pfm.models.entities.WorkUnitEntity;

/**
 *
 * @author Jose Mª Villar
 */
public class WorkUnitEjb {

    private static final int WORK_UNIT = 4;

    public Integer calculatingWorkUnits(final Integer numHours) {
        return numHours * WORK_UNIT;
    }

    public void create(final WorkUnitEntity workUnitEntity) {
        AbstractDAOFactory.getFactory().getWorkerUnit().create(workUnitEntity);
    }

    public void delete(final WorkUnitEntity workUnitEntity) {
        AbstractDAOFactory.getFactory().getWorkerUnit().delete(workUnitEntity);
    }

    public int getNumTotalWorkUnits(final SubActivityEntity subActivity, final IterationEntity iteration) {
        final String psql = "SELECT wu FROM WorkUnitEntity wu WHERE wu.iteration = ?1 AND wu.subactivity = ?2";//NOPMD
        return AbstractDAOFactory.getFactory().getWorkerUnit().find(psql, iteration, subActivity).size();
    }
}
