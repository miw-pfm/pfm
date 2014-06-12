package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.DisciplineEjb;
import eui.miw.pfm.controllers.ejb.IterationEjb;
import eui.miw.pfm.controllers.ejb.ProgressDetailEjb;
import eui.miw.pfm.controllers.ejb.UseCaseEjb;
import eui.miw.pfm.models.entities.DisciplineEntity;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProgressDetailEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Clemencio Morales
 * @author Jose Angel de los Santos
 * @author Roberto Amor
 */
@RequestScoped
@Named
public class ProgressAbstractBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private transient final List<DisciplineEntity> discipline = new DisciplineEjb().findAll(); //NOPMD

    public ProgressAbstractBean() {
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

    public List<IterationEntity> getIterations() {
        return new IterationEjb().getAllIterations(this.project);
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
                case ProgressAbstractBean.SPECIFICATION:
                    this.setPercentSpecification(this.getPercentSpecification() + progressDetEntity.getPercent());
                    break;

                case ProgressAbstractBean.DESIGN:
                    this.setPercentDesign(this.getPercentDesign() + progressDetEntity.getPercent());
                    break;

                case ProgressAbstractBean.IMPLEMENTATION:
                    this.setPercentImplementation(this.getPercentImplementation() + progressDetEntity.getPercent());
                    break;

                case ProgressAbstractBean.TEST:
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

    // @author Jose Mª Villar
    public void viewProgressAbstractGraphic() {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("draggable", false);
        options.put("resizable", true);
        options.put("contentHeight", 350);
        options.put("contentWidth", 850);

        RequestContext.getCurrentInstance().openDialog("progressAbstractGraphic", options, null);
    }

    // @author Roberto Amor
    public Integer obtainPercentsOfIdentification(final IterationEntity iteration) {
        return new ProgressDetailEjb().obtainPercentsOfIdentification(iteration, this.project);
    }

    public Integer obtainPercentOfDiscipline(final IterationEntity iteration, final int cod_discipline) {
        return progressDetailEjb.getSumTotalProgressDetail(this.project, iteration, this.discipline.get(cod_discipline));
    }
    
    public String discipline_header(final int cod_discipline)
    {
        String header_discipline="";
        switch (cod_discipline) {
            case 0:  header_discipline="Specification"; 
                     break;
            case 1:  header_discipline="Design"; 
                     break;
            case 2:  header_discipline="Implementation"; 
                     break;
            case 3:  header_discipline="Test"; 
                     break; 
            case 4:  header_discipline="Acceptance"; 
                     break; 
            default:
                    header_discipline="Error";
                break;
                    
        }
        return header_discipline;
    }
}
