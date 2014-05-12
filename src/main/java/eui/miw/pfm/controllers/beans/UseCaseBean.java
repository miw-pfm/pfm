/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.ListUseCaseEjb;
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

    private UseCaseEntity usecase = new UseCaseEntity();
    private transient ProjectEntity project;
    private static final Logger LOGGER = Logger.getLogger(ConfProjectBean.class.getName());//NOPMD

    public UseCaseBean() {
        super();
        project = new ProjectEntity();
        usecase = new UseCaseEntity();

        try {
            this.project = ((ProjectEntity) sessionMap.get("project"));
        } catch (Exception e) {
            LOGGER.warning("No session exist");
        }
    }

    public UseCaseBean(final UseCaseEntity usecase) {
        super();
        this.usecase = usecase;
    }

    public UseCaseEntity getUsecase() {
        return usecase;
    }

    public void setUsecase(final UseCaseEntity usecase) {
        this.usecase = usecase;
        LOGGER.info(this.usecase.toString());
    }

    public String update() {
        final UseCaseEjb useCaseEjb = new UseCaseEjb();
        this.usecase.setProject(project);
        LOGGER.info(this.usecase.toString());
        useCaseEjb.update(this.usecase);
        FacesContext.getCurrentInstance().addMessage("form_update", new FacesMessage(FacesMessage.SEVERITY_INFO, "Use Case Updated", ""));
        return "useCasesList";
    }

    public String create() {
        if (this.project == null) {
            FacesContext.getCurrentInstance().addMessage("form_create", new FacesMessage(FacesMessage.SEVERITY_WARN, "No project selected", ""));
            return null;//NOPMD
        }
        this.usecase.setProject(this.project);
        final UseCaseEjb useCaseEjb = new UseCaseEjb();
        useCaseEjb.create(this.usecase);
        FacesContext.getCurrentInstance().addMessage("form_create", new FacesMessage(FacesMessage.SEVERITY_INFO, "Use Case Created", ""));
        this.usecase = null; // para vaciar los campos edl formulario
        return null;
    }

    public String delete(final UseCaseEntity useCaseEntity) {
        final UseCaseEjb useCaseEjb = new UseCaseEjb();
        this.usecase.setProject(project);
        this.usecase = useCaseEntity;
        LOGGER.info(this.usecase.toString());
        useCaseEjb.delete(usecase);
        FacesContext.getCurrentInstance().addMessage("form_list", new FacesMessage(FacesMessage.SEVERITY_INFO, "Use Case Deleted", ""));
        return null;
    }

    public List<UseCaseEntity> getUseCases() {
        final ListUseCaseEjb listejb = new ListUseCaseEjb();
        return listejb.obtainUseCase(this.project);
    }

    public String editUseCase(final UseCaseEntity useCaseEntity) {
        this.usecase = useCaseEntity;
        return "useCasesedit";
    }
}
