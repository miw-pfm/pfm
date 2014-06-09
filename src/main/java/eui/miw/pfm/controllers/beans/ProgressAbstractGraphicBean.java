/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.DisciplineEjb;
import eui.miw.pfm.controllers.ejb.IterationEjb;
import eui.miw.pfm.controllers.ejb.ProgressDetailEjb;
import eui.miw.pfm.controllers.ejb.UseCaseEjb;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Jean Mubaied
 * @author Jose Mª Villar
 */
@Named
@ViewScoped
public class ProgressAbstractGraphicBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private CartesianChartModel linearModel;    
    private Map<String, LineChartSeries> series = new HashMap<>();

    private transient ProjectEntity project;
    private transient final List<DisciplineEntity> discipline = new DisciplineEjb().findAll(); //NOPMD

    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD

    @PostConstruct
    public void init() {
        this.series.put("Identification", new LineChartSeries("Identification"));

        for (DisciplineEntity disciplineEntity : this.discipline) {
            if (!disciplineEntity.getName().equals("Acceptance")) {
                this.series.put(disciplineEntity.getName(), new LineChartSeries(disciplineEntity.getName()));
            }
        }

        createLinearModel();
    }
    
    public ProgressAbstractGraphicBean() {
        super();
        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
    }

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }

    public void setLinearModel(final CartesianChartModel linearModel) {
        this.linearModel = linearModel;
    }

    private void createLinearModel() {
        final List<IterationEntity> iterations = new IterationEjb().getAllIterations(this.project);
        final ProgressDetailEjb progressDetailEjb = new ProgressDetailEjb(); //NOPMD

        for (IterationEntity iterationEntity : iterations) {

            this.series.get("Identification").set(iterationEntity.getCodeIteration(), this.obtainPercentsOfIdentification(iterationEntity));

            for (DisciplineEntity disciplineEntity : this.discipline) {
                if (!disciplineEntity.getName().equals("Acceptance")) {
                    this.series.get(disciplineEntity.getName()).set(iterationEntity.getCodeIteration(), progressDetailEjb.getSumTotalProgressDetail(this.project, iterationEntity, disciplineEntity));
                }
            }
        }

        this.linearModel = new CartesianChartModel();

        this.linearModel.addSeries(this.series.get("Identification"));

        for (DisciplineEntity disciplineEntity : this.discipline) {
            if (!disciplineEntity.getName().equals("Acceptance")) {
                this.linearModel.addSeries(this.series.get(disciplineEntity.getName()));
            }
        }
    }

    // @author Jose Mª Villar    
    public Integer obtainPercentsOfIdentification(final IterationEntity iteration) {
        final List<UseCaseEntity> useCaseEntitys = new UseCaseEjb().obtainUseCaseChecked(project);
        final List<IterationEntity> preIterations = new IterationBean().listPreIterations(iteration); //NOPMD

        float numProgressIdent = 0; //NOPMD
        for (UseCaseEntity useCaseEntity : useCaseEntitys) {
            if (preIterations.contains(useCaseEntity.getIteration())) {
                numProgressIdent++; //NOPMD
            }
        }

        return (int) ((numProgressIdent / new UseCaseEjb().getEnabledUseCases(project)) * 100);
    }
}
