/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.DisciplineEjb;
import eui.miw.pfm.controllers.ejb.ProgressDetailEjb;
import eui.miw.pfm.controllers.ejb.UseCaseEjb;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProgressDetailEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
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
 * @author Manuel Álvarez
 * @author William
 */
@ViewScoped
@Named
public class ProgressDetailBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ProgressDetailBean.class.getName());//NOPMD

    private int idIterSelect;
    private int disciplineSelect;
    private int useCaseSelect;
    private int percentCompleted;
    private int enabled = 1;
    private boolean identUC = false;

    private IterationEntity iterationSelected;

    private transient ProgressDetailEntity progressDetail;

    private transient List<DisciplineEntity> lDisciplines;

    @ManagedProperty(value = "#{iterationBean}")
    private transient IterationBean iterationBean;

    @ManagedProperty(value = "#{useCaseBean}")
    private final transient UseCaseBean useCaseBean = new UseCaseBean();

    private List<UseCaseEntity> checkedUseCases;

    private transient ProjectEntity project;

    @PostConstruct
    public void init() {
        iterationBean = new IterationBean();
        this.lDisciplines = new DisciplineEjb().findAll();
        this.iterationSelected = iterationBean.getAllIterations().get(0);
        this.idIterSelect = this.iterationSelected.getId();
        findProgressDetail(true);
        fillCheckedUseCase();
    }

    public ProgressDetailBean() {
        super();

        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
    }

    public void save() {
        final UseCaseEntity useCase = getUseCaseEntity();
        if (useCase.isEnabled() != (this.enabled != 0)) {
            useCase.setEnabled(this.enabled != 0);
            new UseCaseEjb().update(useCase);
        }

        findProgressDetail(false);
        if (this.progressDetail == null) {
            this.progressDetail = new ProgressDetailEntity();
        }
        this.progressDetail.setDiscipline(getDisciplineEntity());
        this.progressDetail.setIteration(this.iterationSelected);
        this.progressDetail.setPercent(this.percentCompleted);
        this.progressDetail.setUseCase(useCase);

        if (this.progressDetail.getId() == null) {
            new ProgressDetailEjb().create(this.progressDetail);
        } else {
            new ProgressDetailEjb().update(this.progressDetail);
        }
    }

    public void fillCheckedUseCase() {
        checkedUseCases = new ArrayList<>();
        List<IterationEntity> listPostIter = iterationBean.listPostIterations(iterationSelected);

        for (UseCaseEntity ucs : new UseCaseEjb().obtainUseCaseChecked(this.project)) {
            if ((!listPostIter.contains(ucs.getIteration())) || (ucs.getIteration().equals(this.iterationSelected))) {
                this.checkedUseCases.add(ucs);
            }
        }
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

    public void findProgressDetail(final boolean flag) {
        final UseCaseEntity useCase = getUseCaseEntity();
        this.progressDetail = new ProgressDetailEjb().findProgressDetail(this.iterationSelected, useCase, getDisciplineEntity());
        if (flag && this.progressDetail != null) {
            this.percentCompleted = (int) this.progressDetail.getPercent();
            this.enabled = useCase.isEnabled() ? 1 : 0;
        }
        this.fillCheckedUseCase();
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

    public int getIdIterSelect() {
        return idIterSelect;
    }

    public void setIdIterSelect(final int idIterSelect) {
        this.idIterSelect = idIterSelect;
        for (IterationEntity iteration : this.getIterations()) {
            if (iteration.getId().equals(this.idIterSelect)) {
                this.iterationSelected = iteration;
            }
        }
    }

    public IterationEntity getIterationSelected() {
        return iterationSelected;
    }

    public void setIterationSelected(final IterationEntity iterationSelected) {
        this.iterationSelected = iterationSelected;
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

    public List<UseCaseEntity> getCheckedUseCases() {
        return checkedUseCases;
    }

    public void setCheckedUseCases(final List<UseCaseEntity> checkedUseCases) {
        this.checkedUseCases = checkedUseCases;
    }

    public ProgressDetailEntity getWorkUnitBy(final UseCaseEntity useCase, final DisciplineEntity discipline) {
        final List<ProgressDetailEntity> lProgress = new ProgressDetailEjb().getByIterationUseCaseDiscipline(this.iterationSelected, useCase, discipline);
        ProgressDetailEntity progress;
        if (lProgress.isEmpty()) {
            progress = new ProgressDetailEntity(null, useCase, this.iterationSelected, discipline, 0);
        } else {
            progress = lProgress.get(0);
        }
        return progress;
    }

    public boolean getIdentUC(final UseCaseEntity ucc) {
        if (this.checkedUseCases.contains(ucc)) {
            this.setIdentUC(true);
        } else {
            this.setIdentUC(false);
        }
        return identUC;
    }

    public void setIdentUC(boolean identUC) {
        this.identUC = identUC;
    }

    public void checkIdentUC(final UseCaseEntity ucc) {
        if (this.getIdentUC(ucc)) {
            ucc.setIteration(null);
            checkedUseCases.remove(ucc);
        } else {
            ucc.setIteration(this.iterationSelected);
            checkedUseCases.add(ucc);
        }
        new UseCaseEjb().update(ucc);
    }
}