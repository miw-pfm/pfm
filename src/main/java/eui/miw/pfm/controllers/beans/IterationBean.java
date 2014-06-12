package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.IterationEjb;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.ConverterDecimal;
import eui.miw.pfm.util.TypeIteration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @added code Hector William
 * @refacored Hector William
 * @refacored Fred Peña
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

    private static final double[] PERCENT = {10.0, 30.0, 50.0, 10.0};

    private static final int DAYSOFONEWEEK = 5;

    @ManagedProperty(value = "#{cpb}")
    private final transient CalendarProjectBean cpb = new CalendarProjectBean();

    private IterationEntity iterEntity;
    private final IterationEjb iterationEjb;

    public IterationBean() {
        super();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.info("No session exist");
        }

        this.iterationEjb = new IterationEjb();

        this.listInception = this.iterationEjb.getIterationsOfOnePhase(TypeIteration.INCEPTION, this.project);
        this.listElaboration = this.iterationEjb.getIterationsOfOnePhase(TypeIteration.ELABORATION, this.project);
        this.listConstruction = this.iterationEjb.getIterationsOfOnePhase(TypeIteration.CONSTRUCTION, this.project);
        this.listTransition = this.iterationEjb.getIterationsOfOnePhase(TypeIteration.TRANSITION, this.project);

        this.allIterations = new ArrayList<>();//iterationEjb.getIterationsOfOnePhase(TypeIteration.INCEPTION);        
        this.allIterations.addAll(this.listInception);
        this.allIterations.addAll(this.listElaboration);
        this.allIterations.addAll(this.listConstruction);
        this.allIterations.addAll(this.listTransition);

        if (!this.allIterations.isEmpty()) {
            iterEntity = this.allIterations.get(0);
        } else {
            iterEntity = new IterationEntity();
        }

        this.inception = this.listInception.size();
        this.elaboration = this.listElaboration.size();
        this.construction = this.listConstruction.size();
        this.transition = this.listTransition.size();

    }

    public void update(final TypeIteration type, final int valueNew, final List<IterationEntity> list) {
        int valueOld = list.size();

        if (valueNew > valueOld) {

            while (valueNew > valueOld) {
                valueOld++;
                iterEntity = new IterationEntity();
                iterEntity.setTypeIteration(type);
                iterEntity.setIterValue(valueOld);
                iterEntity.setProject(project);

                this.iterationEjb.create(iterEntity);
            }
        } else if (valueOld > valueNew) {
            while (valueOld > valueNew) {
                Collections.reverse(list);
                for (IterationEntity auxIter : list) {
                    if ((auxIter.getTypeIteration() == type) && (auxIter.getIterValue() == valueOld) && (valueOld > valueNew)) {
                        this.iterationEjb.delete(auxIter);
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

    public void setInception(final int inception) {
        this.inception = inception;
    }

    public int getElaboration() {
        return elaboration;
    }

    public void setElaboration(final int elaboration) {
        this.elaboration = elaboration;
    }

    public int getConstruction() {
        return construction;
    }

    public void setConstruction(final int construction) {
        this.construction = construction;
    }

    public int getTransition() {
        return transition;
    }

    public void setTransition(final int transition) {
        this.transition = transition;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(final ProjectEntity project) {
        this.project = project;
    }

    public List<IterationEntity> getListInception() {
        return listInception;
    }

    public void setListInception(final List<IterationEntity> listInception) {
        this.listInception = listInception;
    }

    public List<IterationEntity> getListElaboration() {
        return listElaboration;
    }

    public void setListElaboration(final List<IterationEntity> listElaboration) {
        this.listElaboration = listElaboration;
    }

    public List<IterationEntity> getListConstruction() {
        return listConstruction;
    }

    public void setListConstruction(final List<IterationEntity> listConstruction) {
        this.listConstruction = listConstruction;
    }

    public List<IterationEntity> getListTransition() {
        return listTransition;
    }

    public void setListTransition(final List<IterationEntity> listTransition) {
        this.listTransition = listTransition;
    }

    public IterationEntity getIterEntity() {
        return iterEntity;
    }

    public void setIterEntity(final IterationEntity iterEntity) {
        this.iterEntity = iterEntity;
    }

    public CalendarProjectBean getCpb() {
        return cpb;
    }

    public double getPercentage(final int index) {
        return ConverterDecimal.roundOneDecimals(PERCENT[index]);
    }

    public double getRecommendedDays(final int index) {
        return ConverterDecimal.roundOneDecimals(((this.cpb.getWorkingDays() * getPercentage(index)) / 100));
    }

    public double getRecommendedWeeks(final int index) {
        return ConverterDecimal.roundOneDecimals(this.getRecommendedDays(index) / DAYSOFONEWEEK);
    }

    public double getWorkingWeeks() {
        return roundOneDecimals(this.cpb.getWorkingDays() / DAYSOFONEWEEK);
    }

    public double getRecommendedIterations(final int index) {
        return roundOneDecimals(this.getRecommendedWeeks(index) / this.getProject().getWeekNumIteration());
    }

    private double roundOneDecimals(final double calc) {
        return ConverterDecimal.roundOneDecimals(((calc * 100) / 100));
    }

    public double getPlannedPercent(final int index) {
        int size;
        switch (index) {
            case 0:
                size = this.inception;
                break;
            case 1:
                size = this.elaboration;
                break;
            case 2:
                size = this.construction;
                break;
            case 3:
                size = this.transition;
                break;
            default:
                size = -1;
        }
        return ConverterDecimal.roundOneDecimals((((double) size) / ((double) this.project.getChosenNumIteration())) * 100);
    }

    private double planned(final int index) {
        int size;
        switch (index) {
            case 0:
                size = this.inception;
                break;
            case 1:
                size = this.elaboration;
                break;
            case 2:
                size = this.construction;
                break;
            case 3:
                size = this.transition;
                break;
            default:
                size = -1;
        }
        return ((double) size * this.project.getWeekNumIteration());
    }

    public double getPlannedWeeks(final int index) {
        return ConverterDecimal.roundOneDecimals(this.planned(index));
    }

    public double getPlannedDays(final int index) {
        return ConverterDecimal.roundOneDecimals(this.planned(index) * DAYSOFONEWEEK);
    }

    public double getDesviation(final int index) {
        final double percent = (double) (((double) getPlannedPercent(index) - (double) getPercentage(index)) / (double) getPercentage(index));
        return this.roundOneDecimals(percent * 100);
    }

    public double getPlusPlannedDays() {
        return ConverterDecimal.roundOneDecimals(this.getPlannedDays(0) + this.getPlannedDays(1) + this.getPlannedDays(2) + this.getPlannedDays(3));
    }

    public double getPlusPlannedWeeks() {
        return ConverterDecimal.roundOneDecimals(this.getPlannedWeeks(0) + this.getPlannedWeeks(1) + this.getPlannedWeeks(2) + this.getPlannedWeeks(3));
    }

    public int getPlusIterations() {
        return this.inception + this.elaboration + this.construction + this.transition;
    }

    @Override
    public String toString() {
        return "IterationBean{" + "project=" + project + ", listInception=" + listInception + ", listElaboration=" + listElaboration + ", listConstruction=" + listConstruction + ", listTransition=" + listTransition + ", phases[0]=" + inception + ", phases[1]=" + elaboration + ", phases[2]=" + construction + ", phases[3]=" + transition + ", iterEntity=" + iterEntity + '}';
    }

    public void setAllIterations(final List<IterationEntity> allIterations) {
        this.allIterations = allIterations;
    }

    public List<IterationEntity> getAllIterations() {
        return allIterations;
    }

    public void handleIterationChange() {
        this.iterEntity = this.iterationEjb.obtainIteration(this.iterEntity.getId());
    }

    public List<IterationEntity> listPostIterations(IterationEntity iterAct) {
        List<IterationEntity> listPost = new ArrayList<>();

        switch (iterAct.getTypeIteration()) {
            case INCEPTION:
                listPost = this.addPostIterationsPerValue(listPost, listInception, iterAct);
                listPost.addAll(listElaboration);
                listPost.addAll(listConstruction);
                listPost.addAll(listTransition);
                break;
            case ELABORATION:
                listPost = this.addPostIterationsPerValue(listPost, listElaboration, iterAct);
                listPost.addAll(listConstruction);
                listPost.addAll(listTransition);
                break;
            case CONSTRUCTION:
                listPost = this.addPostIterationsPerValue(listPost, listConstruction, iterAct);
                listPost.addAll(listTransition);
                break;
            case TRANSITION:
                listPost = this.addPostIterationsPerValue(listPost, listTransition, iterAct);
                break;
        }

        return listPost;
    }

    public List<IterationEntity> addPostIterationsPerValue(List<IterationEntity> listPost, List<IterationEntity> listType, IterationEntity iterAct) {
        for (IterationEntity ie : listType) {
            if ((ie.getIterValue() == iterAct.getIterValue()) || (ie.getIterValue() > iterAct.getIterValue())) {
                listPost.add(ie);
            }
        }

        return listPost;
    }

    // Refactorizado por Jose Mª Villar
    public List<IterationEntity> listPreIterations(IterationEntity iter) {

        List<IterationEntity> listPre = new ArrayList<>();
        List<IterationEntity> listPost = listPostIterations(iter);

        for (IterationEntity it : allIterations) {
            if (!listPost.contains(it)) {
                listPre.add(it);
            }
        }

        if (!listPre.contains(iter)) {
            listPre.add(iter);
        }

        return listPre;
    }

}
