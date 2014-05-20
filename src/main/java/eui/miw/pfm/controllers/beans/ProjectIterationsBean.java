package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.IterationEjb;
import eui.miw.pfm.models.entities.IterationEntity;
import eui.miw.pfm.util.ExceptionCatch;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.apache.log4j.Logger;

/**
 *
 * @author Clemencio Morales Lucas
 */

@RequestScoped
@Named
public class ProjectIterationsBean extends Bean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ProjectIterationsBean.class.getName());
    
    private IterationEntity iterationEntity;
    
    private static final String FORM_NAME = "form";
    private static final String NEXT_VIEW = "projectPlan";
    
    
    public ProjectIterationsBean(){
        super();
        iterationEntity = new IterationEntity();
    }
    
    
    public IterationEntity getIterationEntity() {
        return iterationEntity;
    }

    public void setIterationEntity(final IterationEntity iterationEntity) {
        this.iterationEntity = iterationEntity;
    }
    
    public String update() {
        assert this.iterationEntity != null;
        
        final IterationEjb iterationEjb = new IterationEjb();

        LOGGER.info("Update: " + this.iterationEntity.toString());

        iterationEjb.update(this.iterationEntity);
        
        if (ExceptionCatch.getInstance().isException()) {
            FacesContext.getCurrentInstance().addMessage(ProjectIterationsBean.FORM_NAME, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Updating Iteration Entity", ""));
            ExceptionCatch.getInstance().setException(false);
        } else {
            FacesContext.getCurrentInstance().addMessage(ProjectIterationsBean.FORM_NAME, new FacesMessage(FacesMessage.SEVERITY_INFO, "Iteration Entity Updated", ""));
        }
        
        return ProjectIterationsBean.NEXT_VIEW;
    }

    public String create() {
        assert this.iterationEntity != null;
        LOGGER.info(this.iterationEntity.toString());
        final IterationEjb iterationEjb = new IterationEjb();
        iterationEjb.create(this.iterationEntity);

        if (ExceptionCatch.getInstance().isException()) {
            FacesContext.getCurrentInstance().addMessage(ProjectIterationsBean.FORM_NAME, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Creating Iteration Entity", ""));
            ExceptionCatch.getInstance().setException(false);
        } else {
            FacesContext.getCurrentInstance().addMessage(ProjectIterationsBean.FORM_NAME, new FacesMessage(FacesMessage.SEVERITY_INFO, "Iteration Entity Created", ""));
        }

        return ProjectIterationsBean.NEXT_VIEW;
    }

    public String delete(final IterationEntity iteration) {
        assert this.iterationEntity != null;
        LOGGER.info(this.iterationEntity.toString());
        final IterationEjb iterationEjb = new IterationEjb();
        iterationEjb.delete(iteration);
        return ProjectIterationsBean.NEXT_VIEW;
    }

    public String editIteration(final IterationEntity iteration) {
        this.iterationEntity = iteration;
        LOGGER.info("Edit: " + this.iterationEntity.toString());

        return ProjectIterationsBean.NEXT_VIEW;
    }

    public List<IterationEntity> getIteration() {
        final IterationEjb iterationEjb = new IterationEjb();
        return iterationEjb.getIterations();
    }
}