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
import javax.faces.bean.ManagedProperty;
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

    private static final int PERCENT_INCEPTION = 10;
    private static final int PERCENT_ELABORATION = 30;
    private static final int PERCENT_CONSTRUCTION = 50;
    private static final int PERCENT_TRANSITION = 10;

    private static final int DAYSOFONEWEEK = 5;
    private static final int WEEKSRECOMMENDEDFORRUP = 3;

    @ManagedProperty(value = "#{cpb}")
    private final transient CalendarProjectBean cpb = new CalendarProjectBean();

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

    public void update(TypeIteration type, int valueNew, List<IterationEntity> list) {
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

        int sum = this.inception + this.elaboration + this.construction + this.transition;

        if (sum > this.project.getChosenNumIteration()) {
            FacesContext.getCurrentInstance().addMessage("formProjectPlan", new FacesMessage("Too many iterations"));
        } else if (sum < this.project.getChosenNumIteration()) {
            FacesContext.getCurrentInstance().addMessage("formProjectPlan", new FacesMessage("Iterations missing"));
        } else {
            update(TypeIteration.INCEPTION, this.inception, this.listInception);
            update(TypeIteration.ELABORATION, this.elaboration, this.listElaboration);
            update(TypeIteration.CONSTRUCTION, this.construction, this.listConstruction);
            update(TypeIteration.TRANSITION, this.transition, this.listTransition);

            FacesContext.getCurrentInstance().addMessage("formProjectPlan", new FacesMessage("Table Updated"));
        }
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

    public int getPERCENT_INCEPTION() {
        return PERCENT_INCEPTION;
    }

    public int getPERCENT_ELABORATION() {
        return PERCENT_ELABORATION;
    }

    public int getPERCENT_CONSTRUCTION() {
        return PERCENT_CONSTRUCTION;
    }

    public int getPERCENT_TRANSITION() {
        return PERCENT_TRANSITION;
    }

    public CalendarProjectBean getCpb() {
        return cpb;
    }

    public double calculateRecommendedDays(int percent) {
        return (this.cpb.getWorkingDays() * percent) / 100;
    }

    public double getRecommendedDays_Inception() {
        return this.calculateRecommendedDays(this.getPERCENT_INCEPTION());
    }

    public double getRecommendedDays_Elaboration() {
        return this.calculateRecommendedDays(this.getPERCENT_ELABORATION());
    }

    public double getRecommendedDays_Construction() {
        return this.calculateRecommendedDays(this.getPERCENT_CONSTRUCTION());
    }

    public double getRecommendedDays_Transition() {
        return this.calculateRecommendedDays(this.getPERCENT_TRANSITION());
    }

    public int getDAYSOFONEWEEK() {
        return DAYSOFONEWEEK;
    }

    public int getWEEKSRECOMMENDEDFORRUP() {
        return WEEKSRECOMMENDEDFORRUP;
    }

    public double getRecommendedWeeks_Inception() {
        return round(this.getRecommendedDays_Inception() / this.getDAYSOFONEWEEK());
    }

    public double getRecommendedWeeks_Elaboration() {
        return round(this.getRecommendedDays_Elaboration() / this.getDAYSOFONEWEEK());
    }

    public double getRecommendedWeeks_Construction() {
        return round(this.getRecommendedDays_Construction() / this.getDAYSOFONEWEEK());
    }

    public double getRecommendedWeeks_Transition() {
        return round(this.getRecommendedDays_Transition() / this.getDAYSOFONEWEEK());
    }

    public double getWorkingWeeks() {
        return round(this.cpb.getWorkingDays() / this.getDAYSOFONEWEEK());
    }

    public double getRecommendedIterations_Inception() {
        return round(this.getRecommendedWeeks_Inception() / this.getWEEKSRECOMMENDEDFORRUP());
    }

    public double getRecommendedIterations_Elaboration() {
        return round(this.getRecommendedWeeks_Elaboration() / this.getWEEKSRECOMMENDEDFORRUP());
    }

    public double getRecommendedIterations_Construction() {
        return round(this.getRecommendedWeeks_Construction() / this.getWEEKSRECOMMENDEDFORRUP());
    }

    public double getRecommendedIterations_Transition() {
        return round(this.getRecommendedWeeks_Transition() / this.getWEEKSRECOMMENDEDFORRUP());
    }

    public double round(double calc) {
        return Math.rint(calc * 100) / 100;
    }

    public double plannedPercent(int iter) {
        return ((double) iter / (double) this.project.getChosenNumIteration()) * 100;
    }

    public int getPlannedPercent_Inception() {
        return (int) this.plannedPercent(this.inception);
    }

    public int getPlannedPercent_Elaboration() {
        return (int) this.plannedPercent(this.elaboration);
    }

    public int getPlannedPercent_Construction() {
        return (int) this.plannedPercent(this.construction);
    }

    public int getPlannedPercent_Transition() {
        return (int) this.plannedPercent(this.transition);
    }

    public int plannedWeeks(int p) {
        return p * this.project.getWeekNumIteration();
    }

    public int getPlannedWeeks_Inception() {
        return this.plannedWeeks(this.inception);
    }

    public int getPlannedDays_Inception() {
        return this.getPlannedWeeks_Inception() * DAYSOFONEWEEK;
    }

    public int getPlannedWeeks_Elaboration() {
        return this.plannedWeeks(this.elaboration);
    }

    public int getPlannedDays_Elaboration() {
        return this.getPlannedWeeks_Elaboration() * DAYSOFONEWEEK;
    }

    public int getPlannedWeeks_Construction() {
        return this.plannedWeeks(this.construction);
    }

    public int getPlannedDays_Construction() {
        return this.getPlannedWeeks_Construction() * DAYSOFONEWEEK;
    }

    public int getPlannedWeeks_Transition() {
        return this.plannedWeeks(this.transition);
    }

    public int getPlannedDays_Transition() {
        return this.getPlannedWeeks_Transition() * DAYSOFONEWEEK;
    }

    @Override
    public String toString() {
        return "IterationBean{" + "project=" + project + ", listInception=" + listInception + ", listElaboration=" + listElaboration + ", listConstruction=" + listConstruction + ", listTransition=" + listTransition + ", inception=" + inception + ", elaboration=" + elaboration + ", construction=" + construction + ", transition=" + transition + ", iterEntity=" + iterEntity + '}';
    }
}
