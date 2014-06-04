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
 * @author William
 */
@ViewScoped
@Named
public class ProgressDetailBean extends Bean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ProgressDetailBean.class.getName());//NOPMD

    private String iterationSelect;
    private String disciplineSelect;
    private String useCaseSelect;
    private int percentCompleted;
    private boolean enabled;
    
    private transient ProgressDetailEntity progressDetail;
    
    private  List<DisciplineEntity> lDisciplines;
    
    @ManagedProperty(value = "#{iterationBean}")
    private final transient IterationBean iterationBean = new IterationBean();
    
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
        
        new ProgressDetailEjb().create(this.progressDetail);
    }
    
    private IterationEntity getIterationEntity() {
        final String[] strings = this.iterationSelect.split(".-");
        
        for (IterationEntity iteration : this.getIterations()) {
            if (iteration.getTypeIteration().toString().equals(strings[0]) && iteration.getIterValue() == Integer.parseInt(strings[1])) {
                return iteration;
            }
        }
        return null;
    }
    
    private UseCaseEntity getUseCaseEntity() {
        for (UseCaseEntity useCase : this.getlUseCases()) {
            if (useCase.getName().equals(this.useCaseSelect)) {
                return useCase;
            }
        }
        return null;
    }
    
    private DisciplineEntity getDisciplineEntity() {
        for (DisciplineEntity discipline : this.getlDisciplines()) {
            if (discipline.getName().equals(this.disciplineSelect)) {
                return discipline;
            }
        }
        return null;
    }
    
    public List<IterationEntity> getIterations() {
        return this.iterationBean.getAllIterations();
    }
    
    public List<DisciplineEntity> getlDisciplines() {
        return lDisciplines;
    }
    
    public List<UseCaseEntity> getlUseCases() {
        return useCaseBean.getUseCases();
    }
    
    public String getIterationSelect() {
        return iterationSelect;
    }
    
    public void setIterationSelect(final String iterationSelect) {
        this.iterationSelect = iterationSelect;
    }
    
    public String getDisciplineSelect() {
        return disciplineSelect;
    }
    
    public void setDisciplineSelect(final String disciplineSelect) {
        this.disciplineSelect = disciplineSelect;
    }
    
    public String getUseCaseSelect() {
        return useCaseSelect;
    }
    
    public void setUseCaseSelect(final String useCaseSelect) {
        this.useCaseSelect = useCaseSelect;
    }
    
    public int getPercentCompleted() {
        return percentCompleted;
    }
    
    public void setPercentCompleted(final int percentCompleted) {
        this.percentCompleted = percentCompleted;
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public ProgressDetailEntity getWorkUnitBy(final  IterationEntity iteration,final UseCaseEntity useCase,final DisciplineEntity discipline){
        final List<ProgressDetailEntity> lProgress = new ProgressDetailEjb().getByIterationUseCaseDiscipline(iteration, useCase, discipline);
        ProgressDetailEntity progress ;
        if(lProgress.isEmpty()){
            progress = new ProgressDetailEntity(null,useCase,iteration,discipline,0);
        }else {
            progress = lProgress.get(0);
        }
        return progress;
    }
}
