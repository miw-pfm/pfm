package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.IterationEjb;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.TypeIteration;
import java.io.Serializable;
import java.util.ArrayList;
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
    private List<IterationEntity> allIterations;

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
        iterEntity = iterationEjb.getIterations().get(0);
        project = new ProjectEntity();

        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.info("No session exist");
        }

        this.listInception = iterationEjb.getIterationsOfOnePhase(TypeIteration.INCEPTION, this.project);
        this.listElaboration = iterationEjb.getIterationsOfOnePhase(TypeIteration.ELABORATION, this.project);
        this.listConstruction = iterationEjb.getIterationsOfOnePhase(TypeIteration.CONSTRUCTION, this.project);
        this.listTransition = iterationEjb.getIterationsOfOnePhase(TypeIteration.TRANSITION, this.project);

        //this.listAllIterations = new List<IterationEntity>();
        this.allIterations = new ArrayList<IterationEntity>();//iterationEjb.getIterationsOfOnePhase(TypeIteration.INCEPTION);
        this.allIterations.addAll(this.listInception);
        this.allIterations.addAll(this.listElaboration);
        this.allIterations.addAll(this.listConstruction);
        this.allIterations.addAll(this.listTransition);

        this.inception = this.listInception.size();
        this.elaboration = this.listElaboration.size();
        this.construction = this.listConstruction.size();
        this.transition = this.listTransition.size();

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
            FacesContext.getCurrentInstance().addMessage("formProjectPlan", new FacesMessage("Too many iterations. The sum have to be " + this.project.getChosenNumIteration() + " and it is " + sum));
        } else if (sum < this.project.getChosenNumIteration()) {
            FacesContext.getCurrentInstance().addMessage("formProjectPlan", new FacesMessage("Iterations missing. The sum have to be " + this.project.getChosenNumIteration() + " and it is " + sum));
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

    public double calculateRecommendedDays(final int percent) {
        return (this.cpb.getWorkingDays() * percent) / 100;
    }

    public double getRecommendedDaysInception() {
        return this.calculateRecommendedDays(this.getPERCENT_INCEPTION());
    }

    public double getRecommendedDaysElaboration() {
        return this.calculateRecommendedDays(this.getPERCENT_ELABORATION());
    }

    public double getRecommendedDaysConstruction() {
        return this.calculateRecommendedDays(this.getPERCENT_CONSTRUCTION());
    }

    public double getRecommendedDaysTransition() {
        return this.calculateRecommendedDays(this.getPERCENT_TRANSITION());
    }

    public int getDAYSOFONEWEEK() {
        return DAYSOFONEWEEK;
    }

    public int getWEEKSRECOMMENDEDFORRUP() {
        return WEEKSRECOMMENDEDFORRUP;
    }

    public double getRecommendedWeeksInception() {
        return round(this.getRecommendedDaysInception() / this.getDAYSOFONEWEEK());
    }

    public double getRecommendedWeeksElaboration() {
        return round(this.getRecommendedDaysElaboration() / this.getDAYSOFONEWEEK());
    }

    public double getRecommendedWeeksConstruction() {
        return round(this.getRecommendedDaysConstruction() / this.getDAYSOFONEWEEK());
    }

    public double getRecommendedWeeksTransition() {
        return round(this.getRecommendedDaysTransition() / this.getDAYSOFONEWEEK());
    }

    public double getWorkingWeeks() {
        return round(this.cpb.getWorkingDays() / this.getDAYSOFONEWEEK());
    }

    public double getRecommendedIterationsInception() {
        return round(this.getRecommendedWeeksInception() / this.getWEEKSRECOMMENDEDFORRUP());
    }

    public double getRecommendedIterationsElaboration() {
        return round(this.getRecommendedWeeksElaboration() / this.getWEEKSRECOMMENDEDFORRUP());
    }

    public double getRecommendedIterationsConstruction() {
        return round(this.getRecommendedWeeksConstruction() / this.getWEEKSRECOMMENDEDFORRUP());
    }

    public double getRecommendedIterationsTransition() {
        return round(this.getRecommendedWeeksTransition() / this.getWEEKSRECOMMENDEDFORRUP());
    }

    public double round(final double calc) {
        return Math.rint(calc * 100) / 100;
    }

    public double plannedPercent(final int iter) {
        return ((double) iter / (double) this.project.getChosenNumIteration()) * 100;
    }

    public int getPlannedPercentInception() {
        return (int) this.plannedPercent(this.inception);
    }

    public int getPlannedPercentElaboration() {
        return (int) this.plannedPercent(this.elaboration);
    }

    public int getPlannedPercentConstruction() {
        return (int) this.plannedPercent(this.construction);
    }

    public int getPlannedPercentTransition() {
        return (int) this.plannedPercent(this.transition);
    }

    public int plannedWeeks(int p) {
        return p * this.project.getWeekNumIteration();
    }

    public int getPlannedWeeksInception() {
        return this.plannedWeeks(this.inception);
    }

    public int getPlannedDaysInception() {
        return this.getPlannedWeeksInception() * DAYSOFONEWEEK;
    }

    public int getPlannedWeeksElaboration() {
        return this.plannedWeeks(this.elaboration);
    }

    public int getPlannedDaysElaboration() {
        return this.getPlannedWeeksElaboration() * DAYSOFONEWEEK;
    }

    public int getPlannedWeeksConstruction() {
        return this.plannedWeeks(this.construction);
    }

    public int getPlannedDaysConstruction() {
        return this.getPlannedWeeksConstruction() * DAYSOFONEWEEK;
    }

    public int getPlannedWeeksTransition() {
        return this.plannedWeeks(this.transition);
    }

    public int getPlannedDaysTransition() {
        return this.getPlannedWeeksTransition() * DAYSOFONEWEEK;
    }

    public double getDesviationInception() {
        final double percent = (double) (((double) this.getPlannedPercentInception() - (double) this.getPERCENT_INCEPTION()) / (double) this.getPERCENT_INCEPTION());
        return this.round(percent * 100);
    }

    public double getDesviationElaboration() {
        final double percent = (double) (((double) this.getPlannedPercentElaboration() - (double) this.getPERCENT_ELABORATION()) / (double) this.getPERCENT_ELABORATION());
        return this.round(percent * 100);
    }

    public double getDesviationConstruction() {
        final double percent = (double) (((double) this.getPlannedPercentConstruction() - (double) this.getPERCENT_CONSTRUCTION()) / (double) this.getPERCENT_CONSTRUCTION());
        return this.round(percent * 100);
    }

    public double getDesviationTransition() {
        final double percent = (double) (((double) this.getPlannedPercentTransition() - (double) this.getPERCENT_TRANSITION()) / (double) this.getPERCENT_TRANSITION());
        return this.round(percent * 100);
    }

    public int getPlusPlannedDays() {
        return this.getPlannedDaysInception() + this.getPlannedDaysElaboration() + this.getPlannedDaysConstruction() + this.getPlannedDaysTransition();
    }

    public int getPlusPlannedWeeks() {
        return this.getPlannedWeeksInception() + this.getPlannedWeeksElaboration() + this.getPlannedWeeksConstruction() + this.getPlannedWeeksTransition();
    }

    public int getPlusIterations() {
        return this.inception + this.elaboration + this.construction + this.transition;
    }

    @Override
    public String toString() {
        return "IterationBean{" + "project=" + project + ", listInception=" + listInception + ", listElaboration=" + listElaboration + ", listConstruction=" + listConstruction + ", listTransition=" + listTransition + ", inception=" + inception + ", elaboration=" + elaboration + ", construction=" + construction + ", transition=" + transition + ", iterEntity=" + iterEntity + '}';
    }

    public void setAllIterations(List<IterationEntity> allIterations) {
        this.allIterations = allIterations;
    }

    public List<IterationEntity> getAllIterations() {
        return allIterations;
    }

    public void handleIterationChange() {
        this.iterEntity = new IterationEjb().obtainIteration(this.iterEntity.getId());
    }

}
