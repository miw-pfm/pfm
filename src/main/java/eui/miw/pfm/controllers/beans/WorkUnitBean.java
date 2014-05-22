/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.WorkUnitEjb;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import eui.miw.pfm.models.entities.WorkUnitEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Jose Mª Villar
 */

@Named
@RequestScoped
public class WorkUnitBean extends Bean implements Serializable {

    private transient WorkUnitEntity workunit;
    private transient SubActivityEntity subActivity;
    private transient IterationEntity iteration;    

    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD
    
    private Integer numHours;
    private final List<WorkUnitEntity> listWorkUnit = new ArrayList<>();
    
    public WorkUnitBean() {
        super();
        workunit = new WorkUnitEntity();        
    }

    public WorkUnitBean(final WorkUnitEntity workunit) {
        super();
        this.workunit = workunit;
    }

    public WorkUnitEntity getWorkunit() {
        return workunit;
    }

    public void setWorkunit(final WorkUnitEntity workunit) {
        this.workunit = workunit;
    }

    public SubActivityEntity getSubActivity() {
        return subActivity;
    }

    public void setSubActivity(final SubActivityEntity subActivity) {
        this.subActivity = subActivity;
    }

    public IterationEntity getIteration() {
        return iteration;
    }

    public void setIteration(final IterationEntity iteration) {
        this.iteration = iteration;
    }

    public Integer getNumHours() {
        return numHours;
    }

    public void setNumHours(final Integer numHours) {
        this.numHours = numHours;
    }
        
    public void storeHours() {
        final WorkUnitEjb workUnitEjb = new WorkUnitEjb();
        
        for (int wu = 0; wu < workUnitEjb.calculatingWorkUnits(this.getNumHours()); wu++) {
            this.listWorkUnit.add(new WorkUnitEntity(this.getIteration(), this.getSubActivity()));
        }

        for (WorkUnitEntity workUnitEntity : this.listWorkUnit) {
            workUnitEjb.create(workUnitEntity);
        }        
    }
    
}
