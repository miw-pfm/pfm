/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.DisciplineEjb;
import eui.miw.pfm.controllers.ejb.TargetEjb;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.TargetEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author Jean Mubaied
 */
@ViewScoped
@Named
public class TargetBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private TargetEntity target;
    private transient ProjectEntity project;
    private DisciplineEntity discipline;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD
    private List<TargetEntity> targets;
    
    @ManagedProperty(value = "#{progresAbstactBean}")
    private ProgressAbstractBean progresAbstactBean = new ProgressAbstractBean();
    
    @ManagedProperty(value = "#{iterationBean}")
    private final transient IterationBean iterationBean = new IterationBean();

    public TargetBean() {
        super();
        this.target = new TargetEntity();
        this.discipline = new DisciplineEntity();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
        fiilTargets();
    }

    public TargetEntity getTarget() {
        return target;
    }

    public void setTarget(final TargetEntity target) {
        this.target = target;
    }

    public String create() {
        if (this.project == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No project selected", ""));
            return null;//NOPMD
        }

        LOGGER.info(this.target.toString());

        this.target.setProject(this.project);
        new TargetEjb().createTarget(target);
        return null;
    }

    public String delete() {
        if (null == this.target) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No target Selected", ""));
        } else {
            this.target.setProject(project);

            LOGGER.info(this.target.toString());

            new TargetEjb().delete(target);
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Target Deleted", ""));
        }
        return null;
    }

    public String update() {
        this.target.setProject(project);
        LOGGER.info(this.target.toString());
        new TargetEjb().update(target);
        return null;
    }

    public List<TargetEntity> getTargets() {
        return targets;
    }

    public void setTargets(final List<TargetEntity> targets) {
        this.targets = targets;
    }

    public void onCellEdit(final CellEditEvent event) {
        final Object oldValue = event.getOldValue();
        final Object newValue = event.getNewValue();
        TargetEntity targetUp;

        if (newValue != null && !newValue.equals(oldValue)) {
            targetUp = targets.get((Integer) event.getRowIndex());
            switch (event.getColumn().getHeaderText()) {
                case "I":
                    targetUp.setInception((Integer) newValue);
                    break;
                case "E":
                    targetUp.setElaboration((Integer) newValue);
                    break;
                case "C":
                    targetUp.setConstruction((Integer) newValue);
                    break;
                case "T":
                    targetUp.setTransition((Integer) newValue);
                    break;
                default:
            }
            new TargetEjb().update(targetUp);
            final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            this.targets.clear();
            fiilTargets();
        }
    }

    public void fiilTargets() {

        final TargetEjb targetEjb = new TargetEjb();
        final List<TargetEntity> ltargets = targetEjb.obtainProjectTargets(this.project);
        if (ltargets.size() == 5) {
            targets = ltargets;
        } else {
            final List<DisciplineEntity> disciplines = new DisciplineEjb().findAll();
            for (DisciplineEntity discip : disciplines) {
                targetEjb.createTarget(new TargetEntity(null, 0, 0, 0, 0, this.project, discip));//NOPMD
            }
            targets = targetEjb.obtainProjectTargets(project);
        }
    }
    
    public int obtainPercentOfPhase(final int phase){
        int percent;
        switch(phase){
            case 0 :
                percent = progresAbstactBean.obtainPercentsOfIdentification(iterationBean.getListInception().get(iterationBean.getListInception().size()-1));
                break;
            case 1 :
                percent = progresAbstactBean.obtainPercentsOfIdentification(iterationBean.getListElaboration().get(iterationBean.getListElaboration().size()-1));
                break;
            case 2 :
                percent = progresAbstactBean.obtainPercentsOfIdentification(iterationBean.getListConstruction().get(iterationBean.getListConstruction().size()-1));
                break;
            case 3 :
                percent = progresAbstactBean.obtainPercentsOfIdentification(iterationBean.getListInception().get(iterationBean.getListInception().size()-1));
                break;
            default:
                percent = 0 ;
        }
        return percent;
    }
    
}
