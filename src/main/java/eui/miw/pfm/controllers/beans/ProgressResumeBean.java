package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.IterationEjb;
import eui.miw.pfm.controllers.ejb.ProgressDetailEjb;
import eui.miw.pfm.controllers.ejb.UseCaseEjb;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProgressDetailEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Clemencio Morales
 * @author Jose Angel de los Santos
 */
@RequestScoped
@Named
public class ProgressResumeBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

//    private static final Logger LOGGER = Logger.getLogger(ProgressResumeBean.class.getName());//NOPMD
    private ProgressDetailEntity progressDetailEntity;
    private ProgressDetailEjb progressDetailEjb;
    private ProjectEntity project;
    private IterationEntity iteration;

    private static final String IDENTIFICATION = "Identification";
    private static final String SPECIFICATION = "Specification";
    private static final String DESIGN = "Design";
    private static final String IMPLEMENTATION = "Implementation";
    private static final String TEST = "Test";

    private Integer percentIdentification;
    private Integer percentSpecification;
    private Integer percentDesign;
    private Integer percentImplementation;
    private Integer percentTest;

    public ProgressResumeBean() {
        super();

        this.setPercentIdentification(0);
        this.setPercentSpecification(0);
        this.setPercentDesign(0);
        this.setPercentImplementation(0);
        this.setPercentTest(0);

        progressDetailEntity = new ProgressDetailEntity();
        progressDetailEjb = new ProgressDetailEjb();

        this.project = ((ProjectEntity) sessionMap.get("project"));
    }

    public ProgressDetailEntity getProgressDetailEntity() {
        return progressDetailEntity;
    }

    public void setProgressDetailEntity(ProgressDetailEntity progressDetailEntity) {
        this.progressDetailEntity = progressDetailEntity;
    }

    public ProgressDetailEjb getProgressDetailEjb() {
        return progressDetailEjb;
    }

    public void setProgressDetailEjb(ProgressDetailEjb progressDetailEjb) {
        this.progressDetailEjb = progressDetailEjb;
    }

    public Integer getPercentIdentification() {
        return percentIdentification;
    }

    public void setPercentIdentification(Integer percentIdentification) {
        this.percentIdentification = percentIdentification;
    }

    public Integer getPercentSpecification() {
        return percentSpecification;
    }

    public void setPercentSpecification(Integer percentSpecification) {
        this.percentSpecification = percentSpecification;
    }

    public Integer getPercentDesign() {
        return percentDesign;
    }

    public void setPercentDesign(Integer percentDesign) {
        this.percentDesign = percentDesign;
    }

    public Integer getPercentImplementation() {
        return percentImplementation;
    }

    public void setPercentImplementation(Integer percentImplementation) {
        this.percentImplementation = percentImplementation;
    }

    public Integer getPercentTest() {
        return percentTest;
    }

    public void setPercentTest(Integer percentTest) {
        this.percentTest = percentTest;
    }

    public int getUseCaseCount() {
        return new UseCaseEjb().obtainUseCase(project).size();
    }

    public void obtainPercentsOfPhasePerIteration() {
        //por cada it y disc, find all (Por cada iteración y disciplina, sumar el porcentaje de los CDU.)
        //Coger ProgressDetailEntity e ir sumando los percent de cada cdu de cada discipline_id e iteration_id
        List<ProgressDetailEntity> progressDetails = this.getProgressDetailEjb().getDetails();

        for (ProgressDetailEntity progressDetEntity : progressDetails) {

            switch (progressDetEntity.getDiscipline().getName()) {
                case ProgressResumeBean.SPECIFICATION:
                    this.setPercentSpecification(this.getPercentSpecification() + progressDetEntity.getPercent());
                    break;

                case ProgressResumeBean.DESIGN:
                    this.setPercentDesign(this.getPercentDesign() + progressDetEntity.getPercent());
                    break;

                case ProgressResumeBean.IMPLEMENTATION:
                    this.setPercentImplementation(this.getPercentImplementation() + progressDetEntity.getPercent());
                    break;

                case ProgressResumeBean.TEST:
                    this.setPercentTest(this.getPercentTest() + progressDetEntity.getPercent());
                    break;
            }
        }
    }

    // PARA IDENTIFICATION-> CALCULARLA POR SEPARADO: buscar c.u identificados en la itereacion actual + los c.u que fueron identificados en iteraciones anteriores
    public void obtainPercentsOfIdentification() {
        List<ProgressDetailEntity> progressDetails = progressDetailEjb.getProgressDetailEntitiesByProject(project);

        List<IterationEntity> proIters = new IterationEjb().getIterations(project);

        List<IterationEntity> subIters = new IterationBean().listPreIterations(iteration);

        int total = 0;
        for (ProgressDetailEntity iter : progressDetails) {
            if (subIters.contains(iter.getIteration())) {
                total++;
            }
        }
        this.setPercentIdentification(total / progressDetails.size());
    }
}
