package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.IterationEjb;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.TypeIteration;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.log4j.Logger;

/**
 *
 * @author Manuel Alvarez
 * @author Clemencio Morales Lucas
 */
@RequestScoped
@Named
public class IterationBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(IterationBean.class.getName());

    private ProjectEntity project;

    private List<IterationEntity> listInception;
    private List<IterationEntity> listElaboration;
    private List<IterationEntity> listConstruction;
    private List<IterationEntity> listTransition;

    private int inception;
    private int elaboration;
    private int construction;
    private int transition;

    private IterationEntity iterEntity;

    public IterationBean() {
        super();
        final IterationEjb iterationEjb = new IterationEjb();

        project = new ProjectEntity();

        this.listInception = iterationEjb.getIterationsOfOnePhase(TypeIteration.INCEPTION);
        this.listElaboration = iterationEjb.getIterationsOfOnePhase(TypeIteration.ELABORATION);
        this.listConstruction = iterationEjb.getIterationsOfOnePhase(TypeIteration.CONSTRUCTION);
        this.listTransition = iterationEjb.getIterationsOfOnePhase(TypeIteration.TRANSITION);

        this.inception = this.listInception.size();
        this.elaboration = this.listElaboration.size();
        this.construction = this.listConstruction.size();
        this.transition = this.listTransition.size();

        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.info("No session exist");
        }
    }

    public void check(TypeIteration type, int valueNew, List<IterationEntity> list) {
        int valueOld = list.size();

        final IterationEjb iterationEjb = new IterationEjb();
        if (valueNew > valueOld) {

            while (valueNew > valueOld) {
                valueOld++;
                iterEntity = new IterationEntity();
                iterEntity.setTypeIteration(type);
                iterEntity.setIterValue(valueOld);
                iterEntity.setProject(project);

                iterationEjb.create(iterEntity);
            }
        } else if (valueOld > valueNew) {
            while (valueOld > valueNew) {
                Collections.reverse(list);
                for (IterationEntity auxIter : list) {
                    if ((auxIter.getTypeIteration() == type) && (auxIter.getIterValue() == valueOld) && (valueOld > valueNew)) {
                        iterationEjb.delete(auxIter);
                        valueOld--;
                    }
                }
            }
        }
    }

    public String updateIterationsForPhase() {
        String nextView = "projectPlan";

        check(TypeIteration.INCEPTION, this.inception, this.listInception);
        check(TypeIteration.ELABORATION, this.elaboration, this.listElaboration);
        check(TypeIteration.CONSTRUCTION, this.construction, this.listConstruction);
        check(TypeIteration.TRANSITION, this.transition, this.listTransition);

        FacesContext.getCurrentInstance().addMessage("formProjectPlan", new FacesMessage("Table Updated"));
        return nextView;
    }

    public int getInception() {
        return inception;
    }

    public void setInception(int inception) {
        this.inception = inception;
    }

    public int getElaboration() {
        return elaboration;
    }

    public void setElaboration(int elaboration) {
        this.elaboration = elaboration;
    }

    public int getConstruction() {
        return construction;
    }

    public void setConstruction(int construction) {
        this.construction = construction;
    }

    public int getTransition() {
        return transition;
    }

    public void setTransition(int transition) {
        this.transition = transition;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public List<IterationEntity> getListInception() {
        return listInception;
    }

    public void setListInception(List<IterationEntity> listInception) {
        this.listInception = listInception;
    }

    public List<IterationEntity> getListElaboration() {
        return listElaboration;
    }

    public void setListElaboration(List<IterationEntity> listElaboration) {
        this.listElaboration = listElaboration;
    }

    public List<IterationEntity> getListConstruction() {
        return listConstruction;
    }

    public void setListConstruction(List<IterationEntity> listConstruction) {
        this.listConstruction = listConstruction;
    }

    public List<IterationEntity> getListTransition() {
        return listTransition;
    }

    public void setListTransition(List<IterationEntity> listTransition) {
        this.listTransition = listTransition;
    }

    public IterationEntity getIterEntity() {
        return iterEntity;
    }

    public void setIterEntity(IterationEntity iterEntity) {
        this.iterEntity = iterEntity;
    }

    @Override
    public String toString() {
        return "IterationBean{" + "project=" + project + ", listInception=" + listInception + ", listElaboration=" + listElaboration + ", listConstruction=" + listConstruction + ", listTransition=" + listTransition + ", inception=" + inception + ", elaboration=" + elaboration + ", construction=" + construction + ", transition=" + transition + ", iterEntity=" + iterEntity + '}';
    }
}