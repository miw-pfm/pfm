/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ActivitiesEjb;
import eui.miw.pfm.controllers.ejb.WorkUnitEjb;
import eui.miw.pfm.controllers.ejb.WorkerEjb;
import eui.miw.pfm.models.entities.ActivityEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import eui.miw.pfm.models.entities.WorkUnitEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Jose Mª Villar
 * @author César Martínez
 */
@Named
@RequestScoped
public class WorkUnitBean extends Bean implements Serializable {

    private transient WorkUnitEntity workunit;
    private transient SubActivityEntity subActivity;
    private transient IterationEntity iteration;
    private WorkerEntity worker;
    private List<ActivityEntity> activities;
    private List<WorkUnitEntity> workunits = new ArrayList<>();

    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD

    private Integer numHours;
    private final List<WorkUnitEntity> listWorkUnit = new ArrayList<>();

    public WorkUnitBean() {
        super();
        workunit = new WorkUnitEntity();
        this.activities = new ActivitiesEjb().obtainAllActivities();
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

    public WorkerEntity getWorker() {
        return worker;
    }

    public void setWorker(WorkerEntity worker) {
        this.worker = worker;
    }
    
    public void setWorker(int id) {                       
        setWorker(new WorkerEjb().getWorker(id));
    }

    public List<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityEntity> activities) {
        this.activities = activities;
    }
    
    public void setSubActivity(int subActId) {        
        setSubActivity(new ActivitiesEjb().obtainSubActivity(subActId));                        
    }

    public List<WorkUnitEntity> getWorkunits() {
        return workunits;
    }

    public void setWorkunits(List<WorkUnitEntity> workunits) {
        this.workunits = workunits;
    }

    public void getWorkUnitsByIterAndSubActivity(final ActivityEntity activity, final IterationEntity iteration) {
        List<SubActivityEntity> subactivities;
        subactivities = new ActivitiesEjb().obtainSubActivities(activity);
        WorkUnitEjb workejb = new WorkUnitEjb();
        for (SubActivityEntity s : subactivities) {
            this.workunits.addAll(workejb.getWorkUnitsByIterAndActivity(s, iteration));
        }
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

    public void setWorkUnitToWorker() {

        this.worker.getWorkUnits().add(workunit);
        final WorkerEjb workerEjb = new WorkerEjb();
        workerEjb.update(worker);

    }
    
    public void selectedSubActivityAndWorker()
    {
        Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        int sub_activity_id = Integer.parseInt(params.get("sub_activity_id"));
        int worker_id = Integer.parseInt(params.get("worker_id"));
        setWorker(worker_id);
        setSubActivity(sub_activity_id);
        //System.out.println("sub_activity_id "+sub_activity_id + " worker_id "+worker_id+" work "+worker.getName());        
       // RequestContext.getCurrentInstance().openDialog("assignUnits");
    }

    public void settWorker(WorkerEntity worker) {
        this.worker = worker;
    }

}
