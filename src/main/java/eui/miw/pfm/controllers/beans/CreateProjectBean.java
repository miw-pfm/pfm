/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eui.miw.pfm.controllers.beans;

import eui.miw.pfm.controllers.ejb.CreateProjectEjb;
import eui.miw.pfm.models.entities.ProjectEntity;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author César Martínez
 */
@RequestScoped
@Named
public class CreateProjectBean extends Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ProjectEntity projectEntity;

    /**
     * Creates a new instance of CreateProjectBean
     */
    public CreateProjectBean() {//NOPMD 
        this.projectEntity = new ProjectEntity();
    }

    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }

    public void setProjectEntity(final ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }

    public void createProject() { //NOPMD
        final CreateProjectEjb cPEjb = new CreateProjectEjb();
        assert this.projectEntity != null;
        assert cPEjb != null;
        cPEjb.createProject(this.projectEntity);

    }

}
