/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ActivitiesEjb;
import eui.miw.pfm.controllers.ejb.WorkUnitEjb;
import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.ActivityEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.SubActivityEntity;
import eui.miw.pfm.models.entities.WorkUnitEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Jean Mubaied
 * @author Jose Mª Villar
 * @author Fred Pena
 */
@ViewScoped
@Named
public class ActivitiesBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD

    private static final int WORK_UNIT = 4;

    private ProjectEntity project;

    private List<SubActivityEntity> subActivities;
    private List<SelectItem> activitiesItem;
    private List<SelectItem> iterationsItem;

    private String selectionSubAct;
    private String selectionAct;
    private String selectionIter;
    private double workUnit;

    @ManagedProperty(value = "#{iterationBean}")
    private final transient IterationBean iterationBean = new IterationBean();

    @PostConstruct
    public void init() {
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }

        this.project = AbstractDAOFactory.getFactory().getProjectDAO().read(project.getId());

        activitiesItem = new ArrayList<>();
        List<SelectItem> lActivities = new ArrayList<>();

        for (ActivityEntity act : this.getActivities()) {
            SelectItemGroup itemGroup = new SelectItemGroup(act.getCode() + ".-" + act.getName());
            int i = 0;
            SelectItem[] lSubActivities = new SelectItem[act.getSubActivity().size()];
            for (SubActivityEntity sub : act.getSubActivity()) {
                lSubActivities[i++] = new SelectItem(sub.getCode() + ".-" + sub.getName(), sub.getCode() + ".-" + sub.getName());
            }
            itemGroup.setSelectItems(lSubActivities);
            lActivities.add(itemGroup);
        }

        activitiesItem.addAll(lActivities);
        iterationsItem = new ArrayList<>();

        List<SelectItem> lIterations = new ArrayList<>();
        for (IterationEntity iter : this.iterationBean.getAllIterations()) {
            lIterations.add(new SelectItem(iter.getTypeIteration() + ".-" + iter.getIterValue(), iter.getTypeIteration() + ".-" + iter.getIterValue()));
        }

        iterationsItem.addAll(lIterations);

        this.subActivities = new ActivitiesEjb().obtainAllSubActivities();
    }

    public void save() {
        if (selectionSubAct.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No Sub Activity Selected", ""));
            return;
        }
        if (selectionIter.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No Iteration Selected", ""));
            return;
        }

        WorkUnitEjb workUnitEjb = new WorkUnitEjb();
        SubActivityEntity subActivityEntity = getSubActivityEntity();
        IterationEntity iterationEntity = getIterationEntity();
        WorkUnitEntity workUnitEntity;

        List<WorkUnitEntity> lWorkUnit = workUnitEjb.getWorkUnitsByIterAndActivity(subActivityEntity, iterationEntity);

        int unit = (int) this.workUnit * WORK_UNIT;
        int size = lWorkUnit.size();
        if (unit < size) {//DELETE
            Collections.reverse(lWorkUnit);
            int delete = size - unit;
            for (int i = 0; i < delete; i++) {
                workUnitEjb.delete(lWorkUnit.get(i));
            }
        } else if (unit > size) {//CREATE   
            for (int i = size; i < unit; i++) {
                workUnitEntity = new WorkUnitEntity(iterationEntity, subActivityEntity);
                workUnitEjb.create(workUnitEntity);
            }
        }
        FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Work Unit Saved", ""));
    }

    public IterationEntity getIterationEntity() {
        String[] split = this.selectionIter.split(".-");

        for (IterationEntity iteration : this.iterationBean.getAllIterations()) {
            if (iteration.getTypeIteration().toString().equals(split[0]) && iteration.getIterValue() == Integer.parseInt(split[1])) {
                return iteration;
            }
        }
        return null;
    }

    private SubActivityEntity getSubActivityEntity() {
        for (SubActivityEntity subActivity : this.subActivities) {
            if (subActivity.getCode().equals(this.selectionSubAct.split(".-")[0])) {
                return subActivity;
            }
        }
        return null;
    }

    public int findWorkUnit(final SubActivityEntity subActivity, final IterationEntity iteration) {
        return new WorkUnitEjb().getNumTotalWorkUnits(subActivity, iteration) / WORK_UNIT;
    }

    public void subActivitiesByActivity() {
        for (ActivityEntity activity : this.getActivities()) {
            if (activity.getCode().equals(this.selectionAct.split(".-")[0])) {
                this.subActivities = new ActivitiesEjb().obtainSubActivities(activity);
                return;
            }
        }
        this.subActivities = new ActivitiesEjb().obtainAllSubActivities();
    }

    public IterationBean getIterationBean() {
        return iterationBean;
    }

    public List<SubActivityEntity> getSubActivities() {
        return subActivities;
    }

    public void setSubActivities(final List<SubActivityEntity> subActivities) {
        this.subActivities = subActivities;
    }

    public List<ActivityEntity> getActivities() {
        return new ActivitiesEjb().obtainAllActivities();
    }

    public List<SelectItem> getActivitiesItem() {
        return activitiesItem;
    }

    public void setActivitiesItem(final List<SelectItem> activitiesItem) {
        this.activitiesItem = activitiesItem;
    }

    public String getSelectionSubAct() {
        return selectionSubAct;
    }

    public void setSelectionSubAct(final String selectionSubAct) {
        this.selectionSubAct = selectionSubAct;
    }

    public String getSelectionIter() {
        return selectionIter;
    }

    public void setSelectionIter(final String selectionIter) {
        this.selectionIter = selectionIter;
    }

    public double getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(final double unitWork) {
        this.workUnit = unitWork;
    }

    public List<SelectItem> getIterationsItem() {
        return iterationsItem;
    }

    public void setIterationsItem(final List<SelectItem> iterationsItem) {
        this.iterationsItem = iterationsItem;
    }

    public String getSelectionAct() {
        return selectionAct;
    }

    public void setSelectionAct(final String selectionAct) {
        this.selectionAct = selectionAct;
    }
}
