package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.IterationEjb;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.util.SessionMap;
import eui.miw.pfm.util.TypeIteration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import org.apache.log4j.Logger;

/**
 *
 * @author Clemencio Morales Lucas
 * @author Manuel Alvarez
 */
@RequestScoped
@Named
public class IterationBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(IterationBean.class.getName());

    private ProjectEntity project;

    private static final String FORM_NAME = "formProjectPlan";
    private static final String NEXT_VIEW = "projectPlan";

    private List<IterationEntity> listInception = new ArrayList<IterationEntity>();
    private List<IterationEntity> listElaboration = new ArrayList<IterationEntity>();
    private List<IterationEntity> listConstruction = new ArrayList<IterationEntity>();
    private List<IterationEntity> listTransition = new ArrayList<IterationEntity>();

    private int inception;
    private int elaboration;
    private int construction;
    private int transition;

    public void obtainCantPhases(final ComponentSystemEvent evt) {
        project = new ProjectEntity();

        try {
            this.project = ((ProjectEntity) new SessionMap().get("project"));
        } catch (Exception e) {
            LOGGER.info("No session exist");
        }

        final IterationEjb iterationEjb = new IterationEjb();

        listInception = iterationEjb.getIterationsOfOnePhase(TypeIteration.INCEPTION);
        listElaboration = iterationEjb.getIterationsOfOnePhase(TypeIteration.ELABORATION);
        listConstruction = iterationEjb.getIterationsOfOnePhase(TypeIteration.CONSTRUCTION);
        listTransition = iterationEjb.getIterationsOfOnePhase(TypeIteration.TRANSITION);

        this.inception = listInception.size();
        this.elaboration = listElaboration.size();
        this.construction = listConstruction.size();
        this.transition = listTransition.size();
    }

    public String update() {
        final IterationEjb iterationEjb = new IterationEjb();
        int inc = listInception.size();
        int elab = listElaboration.size();
        int cons = listConstruction.size();
        int trans = listTransition.size();

        IterationEntity iterEntity;

        if (this.inception > inc) {
            while (this.inception > inc) {
                inc++;
                iterEntity = new IterationEntity();
                iterEntity.setTypeIteration(TypeIteration.INCEPTION);
                iterEntity.setIterValue(inc);
                iterEntity.setProject(project);

                iterationEjb.create(iterEntity);
            }
        } else if (inc > this.inception) {

        }

        if (this.elaboration > elab) {

        } else if (elab > this.elaboration) {

        }

        if (this.construction > cons) {

        } else if (cons > this.construction) {

        }

        if (this.transition > trans) {

        } else if (trans > this.transition) {

        }
        System.out.println("-----------------------------------------End of method-----------------------------------------");
        return null;
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

//    public IterationBean() {
//        super();
//        iterationEntity = new IterationEntity();
//    }
//    public String create() {
//        assert this.iterationEntity != null;
//        LOGGER.info(this.iterationEntity.toString());
//        final IterationEjb iterationEjb = new IterationEjb();
//        iterationEjb.create(this.iterationEntity);
//
//        if (ExceptionCatch.getInstance().isException()) {
//            FacesContext.getCurrentInstance().addMessage(IterationBean.FORM_NAME, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Creating Iteration Entity", ""));
//            ExceptionCatch.getInstance().setException(false);
//        } else {
//            FacesContext.getCurrentInstance().addMessage(IterationBean.FORM_NAME, new FacesMessage(FacesMessage.SEVERITY_INFO, "Iteration Entity Created", ""));
//        }
//
//        return IterationBean.NEXT_VIEW;
//    }
//
//    public String delete(final IterationEntity iteration) {
//        assert this.iterationEntity != null;
//        LOGGER.info(this.iterationEntity.toString());
//        final IterationEjb iterationEjb = new IterationEjb();
//        iterationEjb.delete(iteration);
//        return IterationBean.NEXT_VIEW;
//    }
//    public String editIteration(final IterationEntity iteration) {
//        this.iterationEntity = iteration;
//        LOGGER.info("Edit: " + this.iterationEntity.toString());
//
//        return IterationBean.NEXT_VIEW;
//    }
//
//    public List<IterationEntity> getIteration() {
//        final IterationEjb iterationEjb = new IterationEjb();
//        return iterationEjb.getIterations();
//    }
    //    public IterationEntity getIterationEntity() {
//        return iterationEntity;
//    }
//
//    public void setIterationEntity(final IterationEntity iterationEntity) {
//        this.iterationEntity = iterationEntity;
//    }
    //    public String update() {
//        assert this.iterationEntity != null;
//
//        final IterationEjb iterationEjb = new IterationEjb();
//
//        LOGGER.info("Update: " + this.iterationEntity.toString());
//
//        iterationEjb.update(this.iterationEntity);
//
//        if (ExceptionCatch.getInstance().isException()) {
//            FacesContext.getCurrentInstance().addMessage(IterationBean.FORM_NAME, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Updating Iteration Entity", ""));
//            ExceptionCatch.getInstance().setException(false);
//        } else {
//            FacesContext.getCurrentInstance().addMessage(IterationBean.FORM_NAME, new FacesMessage(FacesMessage.SEVERITY_INFO, "Iteration Entity Updated", ""));
//        }
//
//        return IterationBean.NEXT_VIEW;
//    }
}
