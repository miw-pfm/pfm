package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ActivitiesEjb;
import eui.miw.pfm.controllers.ejb.WorkUnitEjb;
import eui.miw.pfm.controllers.ejb.WorkerEjb;
import eui.miw.pfm.controllers.ejb.IterationEjb;
import eui.miw.pfm.models.entities.ActivityEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import eui.miw.pfm.models.entities.WorkUnitEntity;
import eui.miw.pfm.models.entities.WorkerEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Jose Mª Villar
 * @author César Martínez
 */
@Named
@ViewScoped
public class WorkUnitBean extends Bean implements Serializable {

    private transient WorkUnitEntity workunit;
    private transient SubActivityEntity subActivity;
    private transient IterationEntity iteration;
    private WorkerEntity worker;
    private List<ActivityEntity> activities;
    private List<WorkUnitEntity> workunits = new ArrayList<>();
    private ProjectEntity project;
    private FacesMessage message;

    private Integer numHours;
    private final List<WorkUnitEntity> listWorkUnit = new ArrayList<>();

    private int numUnitsToAsign;
    private int numUnitsAvailable;
    private int numUnitsToRemove;

    public WorkUnitBean() {
        super();

        project = ((ProjectEntity) sessionMap.get("project"));
        workunit = new WorkUnitEntity();
        this.activities = new ActivitiesEjb().obtainAllActivities();

        if (!getAllIterations().isEmpty()) {
            iteration = getAllIterations().get(0);
        }
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

    public void settWorker(int id) {
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

    public void setWorkunits(final SubActivityEntity subActivity, final IterationEntity iteration) {
        this.workunits = new WorkUnitEjb().getAvailableWorkUnits(subActivity, iteration);
    }

    public int getNumUnitsToAsign() {
        return numUnitsToAsign;
    }

    public void setNumUnitsToAsign(int numUnitsToAsign) {
        this.numUnitsToAsign = numUnitsToAsign;
    }

    public int getNumUnitsToRemove() {
        return numUnitsToRemove;
    }

    public void setNumUnitsToRemove(int numUnitsToRemove) {
        this.numUnitsToRemove = numUnitsToRemove;
    }

    public int getNumUnitsAvailable() {
        return numUnitsAvailable;
    }

    public void setNumUnitsAvailable(int numUnitsAvailable) {
        this.numUnitsAvailable = numUnitsAvailable;
    }

    public void getWorkUnitsByIterAndActivity(final ActivityEntity activity, final IterationEntity iteration) {
        List<SubActivityEntity> subactivities;
        subactivities = new ActivitiesEjb().obtainSubActivities(activity);
        WorkUnitEjb workejb = new WorkUnitEjb();
        for (SubActivityEntity s : subactivities) {
            this.workunits.addAll(workejb.getWorkUnitsByIterAndSubActivity(s, iteration));
        }
    }

    public int getNumSubActivityUnits(final SubActivityEntity subActivity, final IterationEntity iteration, boolean available) {
        WorkUnitEjb workejb = new WorkUnitEjb();
        setIteration(iteration);
        int units;
        if (available) {
            units = workejb.getAvailableWorkUnits(subActivity, iteration).size();
        } else {
            units = workejb.getWorkUnitsByIterAndSubActivity(subActivity, iteration).size();
        }
        return units;
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

    public void selectedSubActivityAndWorker() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        int sub_activity_id = Integer.parseInt(params.get("sub_activity_id"));
        int worker_id = Integer.parseInt(params.get("worker_id"));
        settWorker(worker_id);
        setSubActivity(sub_activity_id);
        setWorkunits(subActivity, iteration);
        setNumUnitsToAsign(workunits.size());
    }

    public void addUnitsToWorker() {
        for (int i = 0; i < numUnitsToAsign; i++) {
            workunit = workunits.get(i);
            workunit.setWorker(worker);
            new WorkUnitEjb().update(workunit);
        }

        if (numUnitsToAsign > 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success",
                    "Units added");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Fail",
                    "No units available");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<SubActivityEntity> getWorkerSubActivities() {
        List<SubActivityEntity> workerSubActivities = new ArrayList<>();

        if (worker == null) {
            setWorker(new WorkerEntity());
        }

        for (WorkUnitEntity w : new WorkUnitEjb().getWorkerWorkUnits(worker)) {
            if (!workerSubActivities.contains(w.getSubactivity()) && w.getIteration().equals(iteration)) {
                workerSubActivities.add(w.getSubactivity());
            }
        }

        return workerSubActivities;
    }

    public int getWorkerNumSubActivityUnits(SubActivityEntity sub) {

        WorkUnitEjb workejb = new WorkUnitEjb();

        int units = workejb.getTotalWorkUnitsByWorker(sub, iteration, worker).size();
        return units;

    }

    public void removeUnitsToWorker(SubActivityEntity sub) {

        WorkUnitEjb workejb = new WorkUnitEjb();

        for (WorkUnitEntity w : workejb.getTotalWorkUnitsByWorker(sub, iteration, worker)) {
            w.setWorker(null);
            workejb.update(w);
        }
    }

    public List<IterationEntity> getAllIterations() {
        return new IterationEjb().getAllIterations(project);
    }

    public void handleIterationChange() {
        this.iteration = new IterationEjb().obtainIteration(this.iteration.getId());
    }

    public void editWorkUnits(SubActivityEntity sub) {
        setSubActivity(sub);
        setNumUnitsAvailable(getNumSubActivityUnits(sub, iteration, true));
        setNumUnitsToRemove(getWorkerNumSubActivityUnits(sub));
    }

    public void saveWorkerUnits() {

        setWorkunits(new WorkUnitEjb().getTotalWorkUnitsByWorker(subActivity, iteration, worker));
        for (int i = 0; i < numUnitsToRemove; i++) {
            workunit = workunits.get(i);
            workunit.setWorker(null);
            new WorkUnitEjb().update(workunit);
        }

        setWorkunits(new WorkUnitEjb().getAvailableWorkUnits(subActivity, iteration));
        for (int i = 0; i < numUnitsAvailable; i++) {
            workunit = workunits.get(i);
            workunit.setWorker(worker);
            new WorkUnitEjb().update(workunit);
        }

        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
