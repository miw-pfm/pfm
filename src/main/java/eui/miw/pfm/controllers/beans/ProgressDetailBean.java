/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.DisciplineEjb;
import eui.miw.pfm.controllers.ejb.ProgressDetailEjb;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProgressDetailEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Fred Pena
 */
@ViewScoped
@Named
public class ProgressDetailBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ProgressDetailBean.class.getName());//NOPMD

    private int iterationSelect;
    private int disciplineSelect;
    private int useCaseSelect;
    private int percentCompleted;
    private int enabled = 1;

    private transient ProgressDetailEntity progressDetail;

    private transient List<DisciplineEntity> lDisciplines;

    @ManagedProperty(value = "#{iterationBean}")
    private final transient IterationBean iterationBean = new IterationBean();
//    
    @ManagedProperty(value = "#{iterationBean}")
    private final transient UseCaseBean useCaseBean = new UseCaseBean();

    @PostConstruct
    public void init() {

        this.lDisciplines = new DisciplineEjb().findAll();
        this.progressDetail = new ProgressDetailEntity();
    }

    public void save() {
        this.progressDetail.setDiscipline(getDisciplineEntity());
        this.progressDetail.setIteration(getIterationEntity());
        this.progressDetail.setPercent(this.percentCompleted);
        this.progressDetail.setUseCase(getUseCaseEntity());

        System.out.println("Progress Detail: " + progressDetail);
//        if (this.progressDetail.getId() == null) {
//            new ProgressDetailEjb().create(this.progressDetail);
//        } else {
//            new ProgressDetailEjb().update(this.progressDetail);
//        }
    }

    private IterationEntity getIterationEntity() {
        for (IterationEntity iteration : this.getIterations()) {
            if (iteration.getId().equals(this.iterationSelect)) {
                return iteration;
            }
        }
        return null;
    }

    private UseCaseEntity getUseCaseEntity() {
        for (UseCaseEntity useCase : this.getUseCases()) {
            if (useCase.getId().equals(this.useCaseSelect)) {
                return useCase;
            }
        }
        return null;
    }

    private DisciplineEntity getDisciplineEntity() {
        for (DisciplineEntity discipline : this.getDisciplines()) {
            if (discipline.getId().equals(this.disciplineSelect)) {
                return discipline;
            }
        }
        return null;
    }

    public void findProgressDetail() {
        this.progressDetail = new ProgressDetailEjb().findProgressDetail(getIterationEntity(), getUseCaseEntity(), getDisciplineEntity());
        System.out.println("Progress Detail: " + progressDetail);

    }

    public List<IterationEntity> getIterations() {
        return iterationBean.getAllIterations();
    }

    public List<DisciplineEntity> getDisciplines() {
        return lDisciplines;
    }

    public List<UseCaseEntity> getUseCases() {
        return useCaseBean.getUseCases();
    }

    public int getIterationSelect() {
        return iterationSelect;
    }

    public void setIterationSelect(final int iterationSelect) {
        this.iterationSelect = iterationSelect;
    }

    public int getDisciplineSelect() {
        return disciplineSelect;
    }

    public void setDisciplineSelect(final int disciplineSelect) {
        this.disciplineSelect = disciplineSelect;
    }

    public int getUseCaseSelect() {
        return useCaseSelect;
    }

    public void setUseCaseSelect(final int useCaseSelect) {
        this.useCaseSelect = useCaseSelect;
    }

    public int getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(final int percentCompleted) {
        this.percentCompleted = percentCompleted;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(final int enabled) {
        this.enabled = enabled;
    }

}
