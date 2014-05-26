/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.UseCaseEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import eui.miw.pfm.models.entities.UseCaseEntity;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Roberto Amor
 * @author Clemencio Morales
 * @author Manuel Rodríguez
 */
@Named
@RequestScoped
public class UseCaseBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;

    private UseCaseEntity usecase;
    private transient ProjectEntity project;
    private static final Logger LOGGER = Logger.getLogger(ProjectConfBean.class.getName());//NOPMD

    public UseCaseBean() {
        super();
        this.usecase = new UseCaseEntity();

        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
    }

    public UseCaseEntity getUsecase() {
        return usecase;
    }

    public void setUsecase(final UseCaseEntity usecase) {
        this.usecase = usecase;
    }

    public String update() {
        this.usecase.setProject(project);

        LOGGER.info(this.usecase.toString());

        if (new UseCaseEjb().update(this.usecase)) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Use Case Updated", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Use Cases name is already exists", ""));
            return null;
        }
        return "/riskplan/useCasesList";
    }

    public String create() {
        if (this.project == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No project selected", ""));
            return null;//NOPMD
        }

        this.usecase.setProject(this.project);

        LOGGER.info(this.usecase.toString());

        if (new UseCaseEjb().create(this.usecase)) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Use Case Created", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Use Cases name is already exists", ""));
            return null;
        }
        return "/riskplan/useCasesList";
    }

    public String delete() {
        if (this.usecase == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No Use Case Selected", ""));
        } else {
            this.usecase.setProject(project);

            LOGGER.info(this.usecase.toString());

            new UseCaseEjb().delete(usecase);
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_INFO, "Use Case Deleted", ""));
        }
        return null;
    }

    public List<UseCaseEntity> getUseCases() {
        return new UseCaseEjb().obtainUseCase(this.project);
    }

    public String editUseCase() {
        System.out.println("Risk: " + this.usecase);
        if (this.usecase == null) {
            FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_WARN, "No Use Case Selected", ""));
        } else {
            return "/riskplan/useCasesEdit";
        }
        return null;
    }
}
