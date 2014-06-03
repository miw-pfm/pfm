/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.UseCaseEjb;
import eui.miw.pfm.models.dao.AbstractDAOFactory;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import eui.miw.pfm.util.moks.entities.DisciplineEntityMock;
import java.io.Serializable;
import java.util.ArrayList;
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

    private String iterationSelect;
    private String disciplineSelect;
    private String useCaseSelect;
    private double percentCompleted;
    private boolean enabled;

    private transient ProjectEntity project;

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
    }

    public void save() {

    }

    private IterationEntity getIterationEntity() {
        final String[] spli = this.iterationSelect.split(".-");

        for (IterationEntity iteration : this.iterationBean.getAllIterations()) {
            if (iteration.getTypeIteration().toString().equals(spli[0]) && iteration.getIterValue() == Integer.parseInt(spli[1])) {
                return iteration;
            }
        }
        return null;
    }

    private UseCaseEntity getUseCaseEntity() {
        final List<UseCaseEntity> caseEntitys = new UseCaseEjb().obtainUseCase(this.project);
        for (UseCaseEntity useCase : caseEntitys) {
            if (useCase.getName().equals(this.useCaseSelect)) {
                return useCase;
            }
        }
        return null;
    }

    private DisciplineEntityMock getDisciplineEntity() {
        for (DisciplineEntityMock discipline : new ArrayList<DisciplineEntityMock>()) {
            if (discipline.getName().equals(this.disciplineSelect)) {
                return discipline;
            }
        }
        return null;
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

    public double getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(final double percentCompleted) {
        this.percentCompleted = percentCompleted;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

}
